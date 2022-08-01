import { Customer } from "./customer";
import { OrderItem } from "./orderItem";

export class Order {
    id: number;
    date: number;
    total: number;
    customer: Customer;
    number:number
    orderItems:Array<OrderItem>;

    constructor( id: number, date: number, total:number, customer: Customer, number:number,orderItems:Array<OrderItem>){
        this.id = id;
        this.date = date;
        this.total = total;
        this.customer = customer;
        this.number = number;
        this.orderItems = orderItems;
    }
}