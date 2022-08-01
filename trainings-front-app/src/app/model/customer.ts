export class Customer {
    lastname: string;
    firstname:string;
    address:string;
    phone:string;
    email:string;

    constructor(lastname: string, firstname: string, address: string, phone: string, email: string){
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
