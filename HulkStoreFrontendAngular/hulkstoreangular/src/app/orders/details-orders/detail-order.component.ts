import { Component, OnInit } from '@angular/core';

import { OrderService } from '../services/order.service';
import { Order } from '../models/order';
import { ActivatedRoute } from '@angular/router';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-detail-order',
  templateUrl: './detail-order.component.html'
})
export class DetailOrderComponent implements OnInit {

  orders: Order[];
  title = 'Factura';

  constructor(private orderService: OrderService,
    private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.orderService.getOrders().pipe(
        tap(orders => {
          orders.forEach(order => {});
        })
      )
      .subscribe(orders => (this.orders = orders));
    });
  }
}
