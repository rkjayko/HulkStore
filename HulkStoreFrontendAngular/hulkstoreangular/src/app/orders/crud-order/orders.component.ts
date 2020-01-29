import { Component, OnInit } from '@angular/core';
import { Order } from '../models/order';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { OrderService } from '../services/order.service';
import { Product } from '../../products/models/Product';
import { ItemOrder } from '../models/ItemOrder';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { map, flatMap } from 'rxjs/operators';
import swal from 'sweetalert2';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html'
})
export class OrdersComponent implements OnInit {

  title = 'Nueva orden';
  order: Order = new Order();
  autocompleteControl = new FormControl();
  filterProducts: Observable<Product[]>;
  errors: string[];

  constructor(
    private orderService: OrderService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.filterProducts = this.autocompleteControl.valueChanges
    .pipe(
      map(value => typeof value === 'string' ? value : value.name),
      flatMap(value => value ? this._filter(value) : [])
    );
  }
  private _filter(value: string): Observable<Product[]> {
    const filterValue = value.toLowerCase();

    return this.orderService.filterProducts(filterValue);
  }

  showName(product?: Product): string | undefined {
    return product ? product.name : undefined;
  }

  selectProduct(event: MatAutocompleteSelectedEvent): void {
    const product = event.option.value as Product;

    if (this.existItem(product.id)) {
      this.increaseQuantity(product.id);
    } else {
      const newItem = new ItemOrder();
      newItem.product = product;
      this.order.items.push(newItem);
    }
    this.autocompleteControl.setValue('');
    event.option.focus();
    event.option.deselect();

  }


  updateQuantity(id: number, event: any): void {
    const quantity: number = event.target.value as number;
    if (quantity === 0) {
      return this.deleteItemOrder(id);
    }
    this.order.items = this.order.items.map((item: ItemOrder) => {
      if (id === item.product.id) {
        item.quantity = quantity;
      }
      return item;
    });
  }

  existItem(id: number): boolean {

    let exist = false;
    this.order.items.forEach((item: ItemOrder) => {
      if (id === item.product.id) {
        exist = true;
      }
    });
    return exist;
  }

  increaseQuantity(id: number): void {
    this.order.items = this.order.items.map((item: ItemOrder) => {
      if (id === item.product.id) {
        ++item.quantity;
      }
      return item;
    });
  }

  deleteItemOrder(id: number): void {
    this.order.items = this.order.items.filter((item: ItemOrder) => id !== item.product.id);
  }

  create(orderForm): void {
      if (this.order.items.length == 0) {
        this.autocompleteControl.setErrors({ 'invalid': true });
      }
      if (orderForm.form.valid && this.order.items.length > 0) {
      this.orderService.create(this.order).subscribe(order => {
        swal.fire(this.title, `Orden creada con éxito!`, 'success');
        this.router.navigate(['/productos']);
      },
      err => {
        this.errors = err.error.errors as string[];
        if (err.statusText == null) {
          swal.fire(
            'Código del error desde el backend',
            `${err.status}: ${err.error.errors}`,
            'error'
          );
        }
        swal.fire(
          'Código del error sin conexion',
          `${err.status}: ${err.statusText}`,
          'error'
        );
      }
      );
    }
  }
}
