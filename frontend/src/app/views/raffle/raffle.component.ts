import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { PRODUCTS } from '../../datos_estaticos/products';
import { DATA_USER } from '../../datos_estaticos/user_estadisticas';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-raffle',
  imports: [
    RouterLink,
    CommonModule,
    HeaderComponent,
    FilterComponent,
    ProductItemComponent,
    FooterComponent
],
  templateUrl: './raffle.component.html',
  styleUrl: './raffle.component.css'
})
export class RaffleComponent {
    products = PRODUCTS;
    user = DATA_USER;
    isLoggedIn = false;
    isExpert = false;

    constructor(private authService: AuthService) {}

    ngOnInit() {
        this.products = PRODUCTS.filter(product => product.type === "raffle");

        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
        });

        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        }); 
    }
}
