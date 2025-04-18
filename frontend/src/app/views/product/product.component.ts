import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { HeaderComponent } from '../../components/header/header.component';
import { ProductDetailsComponent } from '../../components/product-details/product-details.component';
import { ExpertCommentsComponent } from '../../components/expert-comments/expert-comments.component';
import { FooterComponent } from "../../components/footer/footer.component";

import { DATA_EXPERT } from '../../datos_estaticos/user_estadisticas';
import { COMMENTS } from '../../datos_estaticos/expert_comments';
import { AuthService } from '../../services/auth.service';
import { ProductoService } from '../../services/product.service';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    FormsModule,
    HeaderComponent,
    ProductDetailsComponent,
    ExpertCommentsComponent,
    FooterComponent
    ],
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {
    product: any;
    comments = COMMENTS;
    isBlindAuction: boolean = false;
    isRaffle: boolean = false;
    user: any;
    expert = DATA_EXPERT;
    isLoggedIn = false;
    isExpert = false;
    showExpertForm = false;
    reviewText = '';
    estimatedPrice = 0;
    cantidadPuja: number | null = null;
    isExpertComment = false;
    tipo = "";
    
    constructor(private route: ActivatedRoute, private authService: AuthService, private productoService: ProductoService) {}

    ngOnInit() {
        
        //Se comprueba si esta la sesion iniciada para obligar a hacerlo en caso de que no este y asi poder pujar
        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
        });
        
        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        });

        //Datos del usuario
        const storedUser = localStorage.getItem('authUser');
        if(storedUser) {
            this.user = JSON.parse(storedUser);
        }

        //Obtener el id de la URL
        const productId = Number(this.route.snapshot.paramMap.get('id'));
    
        this.comments = COMMENTS.filter(comment => productId == comment.product_id);
    
        //Determinar si el producto es de una subasta normal, a ciegas o de un sorteo
        this.tipo = this.route.snapshot.data['tipo'];
    
        if (this.tipo === 'ciega') {
            this.isBlindAuction = true;
        } else if (this.tipo === 'sorteo') {
            this.isRaffle = true;
        }

        //obtener el producto correspondiente
        switch (this.tipo) {
            case 'subasta':
                this.productoService.getSubastaPorId(productId).subscribe((product) => {
                    this.product = product;
                    console.log(this.product);
                });
                break;
        
            case 'ciega':
                this.productoService.getSubastaPorId(productId).subscribe((product) => {
                    this.product = product;
                    console.log(this.product);
                });
                break;
        
            case 'sorteo':
                this.productoService.getSorteoPorId(productId).subscribe((product) => {
                    this.product = product;
                    console.log(this.product);
                });
                break;
        
            default:
                console.error('Tipo de producto no válido');
                break;
        }


        //si un experto inicia sesión y aparece un comentario suyo, se habilita la opcion de editar
        if(this.isExpert) {
            
        }
    }

    onSubmit() {
        if (this.cantidadPuja === null || this.cantidadPuja === undefined) {
            alert("Debes ingresar una cantidad.");
        } else {
            if(this.isRaffle && this.product.actual_price >= this.cantidadPuja) {
                alert("La cantidad dada debe ser mayor a la minima para participar.");
            } else if (this.isBlindAuction && this.product.base_price >= this.cantidadPuja) {
                alert("La puja tiene que ser mayor que el precio base del producto.");
            } else if (this.product.actual_price >= this.cantidadPuja) {
                alert("La puja tiene que ser mayor que el precio actual del producto.");
            }
        }
    }

    openExpertForm() {
        this.showExpertForm = true;
        console.log(this.showExpertForm);
    }

    editExpertForm(comment: any) {
        this.reviewText = comment.comment;
        this.estimatedPrice = comment.estimated_price;
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
