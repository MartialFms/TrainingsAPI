import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/model/customer';
import { Order } from 'src/app/model/order';
import { OrderItem } from 'src/app/model/orderItem';
import { User } from 'src/app/model/user';
import { ApiService } from 'src/app/services/api.service';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  dateOrder: Date = new Date();
  order: Order | undefined;
  customer: Customer | undefined;
  error = null;
  confirmationNumber: number = 0;
  orderItems: Array<OrderItem> = [];
  user: User | undefined;

  constructor(
    public cartService: CartService,
    private router: Router,
    private api: ApiService,
    private authService: AuthentificationService
  ) {}

  ngOnInit(): void {
    if (!this.authService.isLogged) {
      this.router.navigateByUrl('/');
    } else {
      this.user = this.authService.getUser();
    }
  }

  onUpdateCustomer() {
    this.router.navigateByUrl('customer');
  }

  onOrder() {
    this.customer = this.cartService.getCustomer(); // get customer info
    //save each order item
    this.cartService.getCart().forEach(i => {
      let item = new OrderItem(i.quantity, i.price, i);
      this.orderItems.push(item);
    });
 
    //create order
    this.order = new Order(
      0,
      this.dateOrder.getTime(),
      this.cartService.getTotalAmount(),
      this.customer,
      0,
      this.orderItems
    );
    this.addOrder(this.order); //add order + items + customer db
    this.cartService.clear();
    this.router.navigateByUrl('/order');

    // console.log(this.order);
    // this.user?.customers.push(this.customer);
    // console.log(this.user);
  }

  addOrder(order: Order) {
    this.api.addOrder(order).subscribe({
      next: (data) => (this.confirmationNumber = data.number),
      error: (err) => (this.error = err.message),
      complete: () =>
        confirm(
          'Votre commande n°' +
            this.confirmationNumber +
            ' a bien été confirmé, vous pouvez passer au paiement !'
        ),
    });
  }
}
