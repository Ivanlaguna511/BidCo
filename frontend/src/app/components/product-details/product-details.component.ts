import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

import { PRODUCTS } from '../../datos_estaticos/products';

@Component({
  selector: 'app-product-details',
  imports: [CommonModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
    product: any;
    isBlindAuction: boolean = false;
    isRaffle: boolean = false;
    
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
