import { Injectable } from '@angular/core';
import { Training } from 'src/app/model/training'; // import de la class
import { Customer } from '../model/customer';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cartTab: Training[] = [];
  cart: Map<number, Training>;

  constructor() {
    let cart = localStorage.getItem('cart');
    if (cart) {
      this.cart = new Map(JSON.parse(cart));
    } else {
      this.cart = new Map<number, Training>();
    }
  }

  //update cart localstorage on add and remove
  saveCart() {
    localStorage.setItem('cart', JSON.stringify([...this.cart]));
  }

  //update customer in localstorage
  saveCustomer(customer: Customer) {
    localStorage.setItem('customer', JSON.stringify(customer));
  }

  //add item to cart
  addTraining(training: Training) {
    this.cartTab.push(training);
    this.cart.set(training.id, training);
    this.saveCart();
  }

  //delete item from cart
  removeTraining(id: number) {
    let training = this.cart.get(id);

    if (training != null) {
      if (training.quantity == 1) {
        this.cart.delete(training.id);
      } else {
        training.quantity = training.quantity - 1;
      }
      this.saveCart();
    }
  }

  //return cart
  getCart() {
    return Array.from(this.cart.values());
  }

  getCartSize(){
    return this.cart.size;
  }

  //return total amount
  getTotalAmount() {
    let total = 0;
    for (let item of this.cart.values()) {
      total += item.price * item.quantity;
    }
    return total;
  }

  //return customer from localstorage
  getCustomer(): Customer {
    let customer = localStorage.getItem('customer');
    if (customer) {
      return JSON.parse(customer);
    }
    return new Customer('unknown', '', '', '', '');
  }

  clear() {
    this.cart.clear();
    localStorage.removeItem('cart');
  }
}
