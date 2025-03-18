import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { PRODUCTS } from '../../datos_estaticos/products';

@Component({
  selector: 'app-my-auctions',
  imports: [
    RouterLink,
    CommonModule,
    HeaderComponent,
    FilterComponent,
    ProductItemComponent,
    FooterComponent
  ],
  templateUrl: './my-auctions.component.html',
  styleUrl: './my-auctions.component.css'
})
export class MyAuctionsComponent {
    products = PRODUCTS;

    ngOnInit() {
        this.products = PRODUCTS.filter(product => product.creator_user === "UsuarioX");
    }
}
