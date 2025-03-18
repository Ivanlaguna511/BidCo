import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { PRODUCTS } from '../../datos_estaticos/products';
import { ESTADISTICAS } from '../../datos_estaticos/user_estadisticas';

@Component({
  selector: 'app-my-bids',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    HeaderComponent,
    FilterComponent,
    ProductItemComponent,
    FooterComponent
  ],
  templateUrl: './my-bids.component.html',
  styleUrls: ['./my-bids.component.css']
})
export class MyBidsComponent {
    products = PRODUCTS;
    pujas = ESTADISTICAS.bidsIds

    ngOnInit() {
        this.products = PRODUCTS.filter(product => this.pujas.includes(product.id));
    }
}
