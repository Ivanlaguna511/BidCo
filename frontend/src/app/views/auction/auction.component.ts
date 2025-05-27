import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpParams } from '@angular/common/http';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { SubastaService, SubastaResponseDTO, Filtro } from '../../services/auction.service';
import { AuthService } from '../../services/auth.service';;

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
    products: SubastaResponseDTO[] = [];
    isLoggedIn = false;
    isExpert = false;
    saldoUser = 0;

    constructor(private authService: AuthService, private subastaService: SubastaService) {}

    ngOnInit() {
        this.subastaService.getSubastasPorTipo(true).subscribe({
            next: data => this.products = data,
            error: err => console.error('Error al obtener las subastas normales: ', err)
        });
        
        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
        });

        this.authService.currentUser$.subscribe((user) => {
            if (user) this.saldoUser = user.saldo ?? 0;
        })

        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        }); 
    }

    handleFilterApplied(filtro: Filtro) {
        var camposFiltro = new HttpParams()
        camposFiltro = camposFiltro.append("minPrice", filtro.minPrice);
        camposFiltro = camposFiltro.append("maxPrice", filtro.maxPrice);
        filtro.categories.forEach(categoria => {
            camposFiltro = camposFiltro.append("categorias", categoria);
        })
        camposFiltro = camposFiltro.append("dateOrder", filtro.dateOrder)
        
        this.subastaService.getSubastasFiltradasNormal(camposFiltro).subscribe({
            next: data => this.products = data,
            error: err => console.error("Error al obtener subastas filtradas: ", err)
        });
    }
}