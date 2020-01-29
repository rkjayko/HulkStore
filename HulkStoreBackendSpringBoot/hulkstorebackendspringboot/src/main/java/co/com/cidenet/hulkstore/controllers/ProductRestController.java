package co.com.cidenet.hulkstore.controllers;

import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.service.InterfaceProductService;
import co.com.cidenet.hulkstore.util.constans.Constans;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/*
Created by : Jaime Mejia
*/

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class ProductRestController {
    @Autowired
    private InterfaceProductService productService;
    static final Logger logger = Logger.getLogger(ProductRestController.class);

    /*LISTAR LOS PRODUCTOS */
    @GetMapping(value = "/productos")
    public ResponseEntity<List<Product>> index() {
        logger.info("inicia ejecución para mostrar los productos");
        List<Product> product;
        Map<String, Object> response = new HashMap<>();
        try {
           product =  productService.findAll();
        } catch (DataAccessException e) {
            response.put(Constans.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("se ha mostrado con exito la lista de productos") ;
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /*LISTAR EL PRODUCTO POR EL ID,SE VERIFICA SI ESTA EN LA BASE DE DATOS O SI TIENE ERROR LA CONSULTA*/
    @GetMapping("/productos/{id}")
    public ResponseEntity<Product> show(@PathVariable Long id) {
        logger.info("inicia ejecución para mostrar el producto seleccionado");
        Product product;
        Map<String, Object> response = new HashMap<>();
        try {
            product = productService.findOne(id);
        } catch (DataAccessException e) {
            response.put(Constans.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (product == null) {
            response.put(Constans.MESSAGE, "El ID del producto: ".concat(id.toString().concat(Constans.MSG_ERROR_NOT_EXIST)));
            logger.error(response);
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        logger.info("se ha mostrado con exito" + product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    /*CREACION DE PRODUCTOS, SE VERIFICA SI SON DATOS VALIDOS O SI HAY CONEXION A LA BASE, DE LO CONTRARIO GUARDA */
    @PostMapping(value = "/productos")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Product product, BindingResult result) {
        logger.info("inicia ejecución para crear el producto");
        Product newProduct;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put(Constans.ERROR, errors);
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            newProduct = productService.save(product);
        } catch (DataAccessException e) {
            response.put(Constans.MESSAGE,Constans.MSG_ERROR_DATABASE);
            response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Constans.SUCCESS, "El producto ha sido creado con éxito!");
        response.put("product", newProduct);
        logger.info(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*UPDATE DE PRODUCTO, SE VERIFICA SI NO EXISTE EL ID O SI HAY CONEXION A LA BASE O SI NO HAY STOCK, DE LO CONTRARIO ACTUALIZA */
    @PutMapping("/productos/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
        Product oldProduct = productService.findOne(id);
        Product productUpdated;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put(Constans.ERROR, errors);
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (oldProduct == null) {
            response.put(Constans.ERROR, "no se pudo editar, el ID del producto: "
                    .concat(id.toString().concat(Constans.MSG_ERROR_NOT_EXIST)));
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        try {
            if (oldProduct.getQuantity() + product.getQuantity() >= 0) {
                oldProduct.setName(product.getName());
                oldProduct.setPrice(product.getPrice());
                oldProduct.setQuantity(oldProduct.getQuantity() + product.getQuantity());
                oldProduct.setCategory(product.getCategory());
            } else {
                response.put(Constans.ERROR, "No hay suficiente stock en el inventario");
                logger.error(response);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            productUpdated = productService.save(oldProduct);
        } catch (DataAccessException e) {
            response.put(Constans.MESSAGE,Constans.MSG_ERROR_DATABASE);
            response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Constans.SUCCESS, "La cantidad ha sido actualizada con exito");
        response.put("producto", productUpdated);
        logger.info(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*ELIMINACION DE PRODUCTO */
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        logger.info("inicia ejecución para eliminar el producto");
        Map<String, Object> response = new HashMap<>();
        try {
            productService.deleteProduct(id);
        } catch (DataAccessException e) {
            response.put(Constans.ERROR, "Error al eliminar el producto de la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Constans.SUCCESS, "El producto ha sido eliminado con éxito!");
        logger.info(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
