import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListProductsComponent } from './products/components/list-products/list-products-component';
import { CreateProductComponent } from './products/components/crud-product/crud-product.component';
import { PageNotFoundComponent } from './pagenotfound/pageNotFound.component';
import { OrdersComponent } from './orders/crud-order/orders.component';
import { DetailOrderComponent } from '../app/orders/details-orders/detail-order.component';

const routes: Routes = [
  { path: '', redirectTo: '/productos', pathMatch: 'full' },
  { path: 'productos', component: ListProductsComponent },
  { path: 'productos/form', component: CreateProductComponent },
  { path: 'productos/form/:id', component: CreateProductComponent },
  { path: 'facturas/form', component: OrdersComponent},
  { path: 'facturas', component: DetailOrderComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
