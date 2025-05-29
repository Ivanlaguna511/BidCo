import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpParams } from '@angular/common/http';

import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { AuthService } from '../../services/auth.service';
import { SorteoService, SorteoResponseDto, Filtro } from '../../services/raffle.service';
import { AuthExpertService } from '../../services/auth.expert.service';

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
    isLoggedIn = false;
    isExpert = false;
    userPoints = 0;

    constructor(private authService: AuthService, private expertAuthService: AuthExpertService, private sorteoService: SorteoService) {}

    ngOnInit() {
        var storedUser;
        this.sorteoService.getSorteos().subscribe({
            next: data => this.products = data,
            error: err => console.error('Error al obtener los sorteos: ', err)
        });

        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
            if(estado) storedUser = localStorage.getItem('authUser');
        });
        
        //Si no hay sesion de usuario se comprueba si hay sesion de experto
        if(!this.isLoggedIn) {
            this.expertAuthService.isLoggedIn$.subscribe((estado) => {
                this.isLoggedIn = estado;
                this.isExpert = estado;
            });
        }

        if(storedUser) {
            const user = JSON.parse(storedUser);
            this.userPoints = user.puntos;
        }
        
    }

    handleFilterApplied(filtro: Filtro) {
        var camposFiltro = new HttpParams()
        camposFiltro = camposFiltro.append("minPrice", filtro.minPrice);
        camposFiltro = camposFiltro.append("maxPrice", filtro.maxPrice);
        filtro.categories.forEach(categoria => {
            camposFiltro = camposFiltro.append("categorias", categoria);
        })
        camposFiltro = camposFiltro.append("dateOrder", filtro.dateOrder)
        
        this.sorteoService.getSorteosPorFiltro(camposFiltro).subscribe({
            next: data => this.products = data,
            error: err => console.error("Error al obtener sorteos filtrados: ", err)
        });
    }
}
