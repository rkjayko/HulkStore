package co.com.cidenet.hulkstore.controllers;

import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.service.InterfaceOrderService;
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

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class OrderRestController {
    @Autowired
    private InterfaceOrderService orderService;
    static final Logger logger = Logger.getLogger(OrderRestController.class);

    /*ENCONTRAR FACTURAS POR ID*/
    @GetMapping("/facturas/{id}")
    public ResponseEntity<Order> show(@PathVariable Long id) {
        Order order;
        Map<String, Object> response = new HashMap<>();
        try {
            order = orderService.findOrderById(id);
        } catch (DataAccessException e) {
            response.put(Constans.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (order == null) {
            response.put(Constans.MESSAGE, "El ID de la orden: ".concat(id.toString().concat(Constans.MSG_ERROR_NOT_EXIST)));
            logger.error(response);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("se ha mostrado con exito" + order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /*ELIMINAR FACTURAS POR ID*/
    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            orderService.deleteOrderById(id);
        } catch (DataAccessException e) {
            response.put(Constans.ERROR, "Error al eliminar la factura de la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Constans.SUCCESS, "La factura ha sido eliminado con éxito!");
        logger.info(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*MOSTRAR FACTURAS POR NOMBRE*/
    @GetMapping("/facturas/filterProducts/{term}")
    public ResponseEntity<List<Product>> filterByProducts(@PathVariable String term) {
        logger.info("Se busca el producto por el nombre");
        List<Product> product;
        Map<String, Object> response = new HashMap<>();
        try {
            product = orderService.findProductByName(term);
        } catch (DataAccessException e) {
            response.put(Constans.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("se ha mostrado con exito el producto buscado");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /*CREAR FACTURAS*/
    @PostMapping(value = "/facturas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Order order, BindingResult result) {
        Order newOrder;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put(Constans.ERROR, errors);
        }
            try {
                newOrder = orderService.saveOrder(order);
            } catch (DataAccessException e) {
                response.put(Constans.MESSAGE,Constans.MSG_ERROR_DATABASE);
                response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
                logger.error(response);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.put(Constans.SUCCESS, "La factura ha sido creado con éxito!");
            response.put(Constans.SUCCESSFUL, newOrder);
            logger.info(response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*MOSTRAR FACTURAS*/
    @GetMapping(value = "/facturas")
    public ResponseEntity<List<Order>> index() {
        logger.info("inicia ejecución para mostrar las facturas");
        List<Order> order;
        Map<String, Object> response = new HashMap<>();
        try {
            order =  orderService.findAll();
        } catch (DataAccessException e) {
            response.put(Constans.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            response.put(Constans.ERROR, Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            logger.error(response);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("se ha mostrado con exito la lista de facturas") ;
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
