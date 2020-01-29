import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/Product';
import { ProductService } from '../../services/product.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './crud-product.component.html'
})
export class CreateProductComponent implements OnInit {
  private product: Product = new Product();
  title = 'Ingresar Producto';

  errors: string[];

  constructor(
    private productService: ProductService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.loadProduct();
  }

  public changeCategory(): void {
    if (this.product.categoryTemporal !== undefined) {
      this.product.category = this.product.categoryTemporal;
    }
  }

  loadProduct(): void {

    this.activatedRoute.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.productService
          .getProduct(id)
          .subscribe(product => (this.product = product));
      }
    });
  }

  create(): void {
    this.productService.create(this.product).subscribe(
      product => {
        this.router.navigate(['/productos']);
        swal.fire(
          'Nuevo Producto : ', `El producto ${this.product.name} ha sido creado con éxito`, 'success'
        );
      },
      err => {
        this.errors = err.error.errors as string[];
        swal.fire(
          'Se presento un error',
          `${err.error.ERROR}`,
          'error'
        );
      }
    );
  }

  update(): void {
    this.productService.update(this.product).subscribe(
      json => {
        this.router.navigate(['/productos']);
        swal.fire(
          'Producto Actualizado', `El producto ${this.product.name} ha sido actualizado con exito`, 'success'
        );
      },
      err => {
        this.errors = err.error.errors as string[];
        swal.fire(
          'Se presento un error',
          `${err.error.ERROR}`,
          'error'
        );
      }
    );
  }
}
