import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from '../../components/header/header.component';
import { ProductDetailsComponent } from '../../components/product-details/product-details.component';

import { PRODUCTS } from '../../datos_estaticos/products';
import { ESTADISTICAS } from '../../datos_estaticos/user_estadisticas';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    HeaderComponent,
    ProductDetailsComponent],
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {
    product: any;
    isBlindAuction: boolean = false;
    isRaffle: boolean = false;
    user = ESTADISTICAS
    
    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        //Obtener el id de la URL y buscar el producto
        const productId = Number(this.route.snapshot.paramMap.get('id'));
        this.product = PRODUCTS.find(p => p.id === productId);

        //Determinar si el producto es de una subasta normal, a ciegas o de un sorteo
        if (this.product) {
            this.isBlindAuction = this.product.type === 'blind';
            this.isRaffle = this.product.type === 'raffle';
        }
    }
}
