import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Training } from 'src/app/model/training';
import { CartService } from 'src/app/services/cart.service';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cart: Training[] | undefined;
  total: number = 0;
  currentUser: User | undefined;
  error = null;

  constructor(
    private cartService: CartService,
    private router: Router,
    private authSerive: AuthentificationService
  ) {}

  ngOnInit(): void {
    this.cart = this.cartService.getCart();
    this.displayTotal();
  }

  delete(training: Training) {
    this.cartService.removeTraining(training.id);
    this.cart = this.cartService.getCart();
    this.displayTotal();
  }

  displayTotal() {
    this.total = this.cartService.getTotalAmount();
    return this.total;
  }

  newOrder() {
    if (this.authSerive.isLogged) {
      this.router.navigateByUrl('customer');
    } else {
      this.router.navigateByUrl('login');
    }
  }
}
