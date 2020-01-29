import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ListProductsComponent } from './products/components/list-products/list-products-component';
import { CreateProductComponent } from './products/components/crud-product/crud-product.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PageNotFoundComponent } from './pagenotfound/pageNotFound.component';
import { ProductService } from './products/services/product.service';
import { OrdersComponent } from './orders/crud-order/orders.component';
import { MatDatepickerModule } from '@angular/material';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { DetailOrderComponent } from '../app/orders/details-orders/detail-order.component';
import { OrderService } from './orders/services/order.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ListProductsComponent,
    CreateProductComponent,
    PageNotFoundComponent,
    OrdersComponent,
    DetailOrderComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule, MatDatepickerModule, MatMomentDateModule,
    ReactiveFormsModule, MatAutocompleteModule, MatInputModule, MatFormFieldModule
  ],
  providers: [ProductService,
    OrderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
