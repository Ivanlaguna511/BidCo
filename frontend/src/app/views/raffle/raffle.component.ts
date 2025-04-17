import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { DATA_USER } from '../../datos_estaticos/user_estadisticas';
import { AuthService } from '../../services/auth.service';
import { SorteoService, SorteoResponseDto } from '../../services/raffle.service';

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
    products: SorteoResponseDto[] = [];
    user = DATA_USER;
    isLoggedIn = false;
    isExpert = false;

    constructor(private authService: AuthService, private sorteoService: SorteoService) {}

    ngOnInit() {
        this.sorteoService.getSorteos().subscribe({
            next: data => this.products = data,
            error: err => console.error('Error al obtener los sorteos: ', err)
        });

        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
        });

        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        }); 
    }
}
