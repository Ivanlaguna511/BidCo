import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-product-item',
  imports: [],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css'
})
export class ProductItemComponent {
    @Input() product: any;
    isBlindAuction: boolean = false;
    isRaffle: boolean = false;

    ngOnInit() {
        //Determinar si el producto es de una subasta normal, a ciegas o de un sorteo
        if (this.product) {
            this.isBlindAuction = this.product.type === 'blind';
            this.isRaffle = this.product.type === 'raffle';
        }
    }
}
