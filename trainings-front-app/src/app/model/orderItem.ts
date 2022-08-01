import { Training } from './training';

export class OrderItem {
  quantity: number;
  price: number;
  training: Training;

  constructor(quantity: number, price: number, training: Training){
    this.quantity = quantity;
    this.price = price;
    this.training = training;
  }
}
