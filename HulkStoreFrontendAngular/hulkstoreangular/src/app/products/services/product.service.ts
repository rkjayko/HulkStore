import { Injectable } from '@angular/core';
import { Product } from '../models/Product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class ProductService {
  private urlEndPoint = 'http://localhost:8080/api/productos';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http: HttpClient, private router: Router) {}

  getProducts(): Observable<Product[]> {
    return this.http.get(this.urlEndPoint).pipe(
      tap(response => {
        const products = response as Product[];
        products.forEach(product => {});
      }),
      map(response => {
        const products = response as Product[];
        return products.map(product => {
          product.name = product.name.toUpperCase();
          return product;
        });
      }),
      tap(response => {
        response.forEach(product => {});
      })
    );
  }

  create(product: Product): Observable<Product> {
    return this.http
      .post(this.urlEndPoint, product, { headers: this.httpHeaders })
      .pipe(
        map((response: any) => response.product as Product),
        catchError(e => {
          if (e.status === 400) {
            swal.fire(e.error.mensaje, e.error.error, 'error');
            return throwError(e);
          }
          swal.fire(e.error.mensaje, e.error.error, 'error');
          return throwError(e);
        })
      );
  }

  getProduct(id): Observable<Product> {
    return this.http.get<Product>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/productos']);
        swal.fire('Error al obtener', e.message, 'error');
        return throwError(e);
      })
    );
  }

  update(product: Product): Observable<any> {
    return this.http
      .put<any>(`${this.urlEndPoint}/${product.id}`, product, {
        headers: this.httpHeaders
      })
      .pipe(
        catchError(e => {
          if (e.status === 400) {
            swal.fire(e.error.mensaje, e.error.error, 'error');
            return throwError(e);
          }
          swal.fire(e.error.mensaje, e.error.error, 'error');
          return throwError(e);
        })
      );
  }

  delete(id: number): Observable<Product> {
    return this.http.delete<Product>(`${this.urlEndPoint}/${id}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}
