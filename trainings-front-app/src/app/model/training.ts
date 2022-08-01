import { Category } from "./category";

export class Training {
    id: number;
    name: string;
    description: string;
    price: number;
    quantity:number;
    image:string;
    category: Category = new Category(0, "");
  
    constructor(id: number = 0, name: string, description: string, price: number, quantity:number = 1, image:string, category: Category) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.price = price;
      this.quantity = quantity;
      this.image = image;
      this.category =category;
    }
}
