import { Component, OnInit } from '@angular/core';
import {CartService} from "../../services/cart.service";

@Component({
  selector: 'app-cart-status',
  templateUrl: './cart-status.component.html',
  styleUrls: ['./cart-status.component.css']
})
export class CartStatusComponent implements OnInit {
  totalPrice: number = 0.00;
  totalQuantity: number = 0;

  constructor(private cartSerice: CartService) { }

  ngOnInit(): void {
    this.updateCartStatus();
  }


  updateCartStatus() {
    this.cartSerice.totalPrice.subscribe(
      data => this.totalPrice = data
    );
    this.cartSerice.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );
  }
}
