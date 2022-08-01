import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { TrainingsComponent } from './components/trainings/trainings.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CustomerComponent } from './components/customer/customer.component';
import { OrderComponent } from './components/order/order.component';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/admin/admin.component';
import { TrainingComponent } from './components/training/training.component';
import { AuthGuardService } from './services/auth-guard.service';
import { OrdersComponent } from './components/admin/orders/orders.component';
import { OrderDetailsComponent } from './components/admin/order-details/order-details.component';

const routes: Routes = [
  {
    path: 'trainings',
    component: TrainingsComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
  },
  {
    path: 'order',
    component: OrderComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'customer',
    component: CustomerComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'admin/commandes',
    component: OrdersComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'admin/commandes/details/:id',
    component: OrderDetailsComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: '',
    redirectTo: 'trainings',
    pathMatch: 'full',
  },
  {
    path: 'training/:id',
    component: TrainingComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: '404',
    component: NotFoundComponent,
  },
  {
    path: '**',
    redirectTo: '/404',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
