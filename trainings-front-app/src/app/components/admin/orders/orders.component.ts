import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { ApiService } from 'src/app/services/api.service';
import { AuthentificationService } from 'src/app/services/authentification.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent implements OnInit {
  isAdmin: boolean = false;
  listOrders: Order[] | undefined;
  error = null;

  constructor(
    private router: Router,
    private authService: AuthentificationService,
    private api: ApiService
  ) {}

  ngOnInit(): void {
    if (localStorage.getItem('userConnected') != null) {
      this.isAdmin = true;
    }

    this.getAllOrders();
    if (!this.authService.isAdmin) {
      this.router.navigateByUrl('/login');
    }
  }

  getAllOrders() {
    this.api.getAllOrders().subscribe({
      next: (data) => ((this.listOrders = data)),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  checkOrderDetails(id: number) {
    this.router.navigateByUrl('admin/commandes/details/' + id);
  }
}
