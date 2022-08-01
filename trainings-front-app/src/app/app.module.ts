import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TrainingsComponent } from './components/trainings/trainings.component';
import { CartService } from './services/cart.service';
import { FormsModule } from '@angular/forms';
import { CartComponent } from './components/cart/cart.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CustomerComponent } from './components/customer/customer.component';
import { OrderComponent } from './components/order/order.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { TrainingComponent } from './components/training/training.component';
import { OrdersComponent } from './components/admin/orders/orders.component';
import { OrderDetailsComponent } from './components/admin/order-details/order-details.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainingsComponent,
    CartComponent,
    NotFoundComponent,
    CustomerComponent,
    OrderComponent,
    LoginComponent,
    AdminComponent,
    TrainingComponent,
    OrdersComponent,
    OrderDetailsComponent
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule, ReactiveFormsModule],
  providers: [CartService],
  bootstrap: [AppComponent],
})
export class AppModule {}
