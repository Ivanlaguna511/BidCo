import { Component, Input, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment'; // Añade la importación

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css'
})
export class ProductItemComponent implements OnInit {
    @Input() product: any;
    isBlindAuction: boolean = false;
    isRaffle: boolean = false;
    countdown: string = '';

    // Añadimos la variable para la ruta de imágenes
    readonly uploadsUrl = environment.apiUrl.replace('/api', '') + '/uploads/';

    ngOnInit() {
        if (this.product) {
            const esSubasta = this.product.hasOwnProperty('subastaNormal');
            if (esSubasta) {
                this.isBlindAuction = this.product.subastaNormal === false;
            } else {
                this.isRaffle = true;
            }
        }

        if(this.isRaffle) {
            this.setupCountdown(this.product.fechaFin);
        } else {
            this.setupCountdown(this.product.fechaFinal);
        }
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
        
            this.countdown = `${days}d ${hours}h ${minutes}m `;
        }, 1000);
    }
}
