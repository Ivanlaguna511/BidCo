import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { HeaderComponent } from '../../components/header/header.component';
import { ProductDetailsComponent } from '../../components/product-details/product-details.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { PRODUCTS } from '../../datos_estaticos/products';
import { DATA_USER } from '../../datos_estaticos/user_estadisticas';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    FormsModule,
    HeaderComponent,
    ProductDetailsComponent,
    FooterComponent
    ],
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {
    product: any;
    isBlindAuction: boolean = false;
    isRaffle: boolean = false;
    user = DATA_USER;
    isLoggedIn = false;
    isExpert = false;
    showExpertForm = false;
    reviewText = '';
    estimatedPrice = 0;
    
    constructor(private route: ActivatedRoute, private authService: AuthService,) {}

    ngOnInit() {
        //Obtener el id de la URL y buscar el producto
        const productId = Number(this.route.snapshot.paramMap.get('id'));
        this.product = PRODUCTS.find(p => p.id === productId);

        //Determinar si el producto es de una subasta normal, a ciegas o de un sorteo
        if (this.product) {
            this.isBlindAuction = this.product.type === 'blind';
            this.isRaffle = this.product.type === 'raffle';
        }

        //Se comprueba si esta la sesion iniciada para obligar a hacerlo en caso de que no este y asi poder pujar
        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
        });

        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        });   
    }

    openExpertForm() {
        this.showExpertForm = true;
        console.log(this.showExpertForm);
    }

    closeExpertForm() {
        this.showExpertForm = false;
        this.reviewText = '';
        this.estimatedPrice = 0;
    }

    submitReview() {
        console.log("Valoración enviada:", this.reviewText, "Puntuación:", this.estimatedPrice);
        this.closeExpertForm();
      }
}
