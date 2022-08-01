import { Customer } from "./customer";

export class User {
  id: number;
  username: string;
  password: string;
  email:string;
  enable:boolean;
  role: Array<string>;
  customers: Array<Customer>

  constructor(
    id: number,
    username: string,
    password: string,
    email:string,
    enable:boolean,
    role: Array<string>,
    customers: Array<Customer>
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.enable = enable;
    this.password = password;
    this.role = role;
    this.customers = [];
  }
}
