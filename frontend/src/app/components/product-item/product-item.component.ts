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
    countdown: string = '';

    ngOnInit() {
        //Determinar si el producto es de una subasta normal, a ciegas o de un sorteo
        if (this.product) {
            const esSorteo = this.product.hasOwnProperty('subastaNormal');
            if (esSorteo) {
                this.isBlindAuction = this.product.subastaNormal === true;
            } else {
                this.isRaffle = this.product.type === 'raffle';
            }
        }

        this.setupCountdown(this.product.fechaFinal);
    }

    setupCountdown(endDateString: string) {
        const fullDateString = endDateString.includes('T') 
            ? endDateString 
            : `${endDateString}T23:59:59`;

        const endDate = new Date(fullDateString).getTime();
      
        const interval = setInterval(() => {
            const now = new Date().getTime();
            const distance = endDate - now;
        
            if (distance < 0) {
                this.countdown = "Finalizada";
                clearInterval(interval);
                return;
            }
        
            const days = Math.floor(distance / (1000 * 60 * 60 * 24));
            const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((distance % (1000 * 60)) / 1000);
        
            this.countdown = `${days}d ${hours}h ${minutes}m ${seconds}s`;
        }, 1000);
    }
}
