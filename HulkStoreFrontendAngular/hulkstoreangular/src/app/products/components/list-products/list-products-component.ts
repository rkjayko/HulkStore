import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/Product';
import { ProductService } from '../../services/product.service';
import { tap } from 'rxjs/operators';
import swal from 'sweetalert2';

@Component({
  selector: 'app-products',
  templateUrl: './list-products.component.html'
})
export class ListProductsComponent implements OnInit {
  products: Product[];

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.productService
      .getProducts()
      .pipe(
        tap(products => {
          products.forEach(product => {});
        })
      )
      .subscribe(products => (this.products = products));
  }

  delete(product: Product): void {
    swal.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar el producto ${product.name}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!'
    }).then((result) => {
      if (result.value) {
        this.productService.delete(product.id).subscribe(
          () => {
            this.products = this.products.filter(prt => prt !== product);
        swal.fire(
          'Producto Eliminado!',
          `El producto ${product.name} ha sido eliminado con éxito.`,
          'success'
          );
        }
      );
    }
  });
}
}
