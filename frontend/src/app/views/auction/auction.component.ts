import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { PRODUCTS } from '../../datos_estaticos/products';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-auction',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    HeaderComponent,
    FilterComponent,
    ProductItemComponent,
    FooterComponent
],
  templateUrl: './auction.component.html',
  styleUrl: './auction.component.css'
})

export class AuctionComponent {
    products = PRODUCTS;
    isLoggedIn = false;
    isExpert = false;

    constructor(private authService: AuthService) {}

    ngOnInit() {
        this.products = PRODUCTS.filter(product => product.type === "normal");
        
        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
        });

        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        });  
    }
}