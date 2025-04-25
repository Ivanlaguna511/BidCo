import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { AuthService } from '../../services/auth.service';
import { SubastaService } from '../../services/auction.service';

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
    products: any;
    userId: number | null = null;

    constructor(private authService: AuthService, private subastaService: SubastaService) {}

    ngOnInit() {
        this.authService.currentUser$.subscribe((user) => {
            if (user) {
                this.subastaService.getSubastasPorCreador(user.usuarioID).subscribe((lista) => {
                    this.products = lista;
                    console.log(this.products);
                });
            }
        })
    }
}
