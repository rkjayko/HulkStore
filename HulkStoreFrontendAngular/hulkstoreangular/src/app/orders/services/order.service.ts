import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Order } from '../models/order';
import { Product } from '../../products/models/Product';
import { map, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})

export class OrderService {

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlEndPoint = 'http://localhost:8080/api/facturas';

  constructor(private http: HttpClient, private router: Router) {}

  getOrders(): Observable<Order[]> {
    return this.http.get(this.urlEndPoint).pipe(
      tap(response => {
        const orders = response as Order[];
        orders.forEach(order => {});
      }),
      map(response => {
        const orders = response as Order[];
        return orders.map(order => {
          order.id = order.id;
          return order;
        });
      }),
      tap(response => {
        response.forEach(order => {});
      })
    );
  }

  getOrder(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.urlEndPoint}/${id}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.urlEndPoint}/${id}`);
  }

  filterProducts(term: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.urlEndPoint}/filterProducts/${term}`);
  }

  create(order: Order): Observable<Order> {
    return this.http.post<Order>(this.urlEndPoint, order);
  }
}
