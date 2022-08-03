import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { OrderItem } from 'src/app/model/orderItem';
import { ApiService } from 'src/app/services/api.service';
import { AuthentificationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css'],
})
export class OrderDetailsComponent implements OnInit {

  order: Order | undefined;
  error = null;
  orderItems: OrderItem[] | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private api: ApiService,
    private authService: AuthentificationService
  ) {}

  ngOnInit(): void {
    if (this.authService.isAdmin) {
      let orderId = this.route.snapshot.params['id'];

      if(orderId > 0){
        this.getOneOrder(orderId);
        this.getOrderItems(orderId);
      }
    } else {
      this.router.navigateByUrl('/login');
    }
  }

  getOneOrder(orderId:number){
    this.api.getOneOrder(orderId).subscribe({
      next: (data) => (this.order = data),
      error: (err) => (this.error = err.message),
    });
  }

  getOrderItems(orderId:number){
    this.api.getOrderItems(orderId).subscribe({
      next: (data) => (this.orderItems = data),
      error: (err) => (this.error = err.message),
    });
  }
}
