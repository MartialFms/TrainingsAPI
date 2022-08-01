import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from 'src/app/model/customer';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent implements OnInit {
  myForm: FormGroup;
  customer = this.cartService.getCustomer();
  constructor(
    private authService: AuthentificationService,
    public cartService: CartService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.myForm = this.formBuilder.group({
      lastname: [this.customer.lastname, Validators.required],
      firstname: [this.customer.firstname, Validators.required],
      address: [
        this.customer.address,
        [Validators.required, Validators.minLength(25)],
      ],
      phone: [
        this.customer.phone,
        [Validators.required, Validators.minLength(10)],
      ],
      email: [
        this.customer.email,
        [
          Validators.required,
          Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
        ],
      ],
    });
  }

  ngOnInit(): void {    if (!this.authService.isLogged) {
    this.router.navigateByUrl('/');
  }}

  onSaveCustomer(form: FormGroup) {
    if (form.valid) {
      this.customer = new Customer(
        form.value.lastname,
        form.value.firstname,
        form.value.address,
        form.value.phone,
        form.value.email
      );
      this.cartService.saveCustomer(this.customer);
      this.router.navigateByUrl('order');
    } else {
      console.log('error');
    }
  }
}
