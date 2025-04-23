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
import { AuthService } from '../../services/auth.service';
import { ProductoService, PujaDTO} from '../../services/product.service';
import { CommentService } from '../../services/comment.service';


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
    productId = 0;
    comments: any[] = [];
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
    tipo = '';
    editandoComentario: any = null;
    comentarioEditado = { comment: '', estimated_price: 0 };

    constructor(
        private route: ActivatedRoute,
        private authService: AuthService,
        private productoService: ProductoService,
        private commentService: CommentService
    ) {}

    ngOnInit() {
        this.authService.isLoggedIn$.subscribe((estado) => {
            this.isLoggedIn = estado;
        });

        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        });

        const storedUser = localStorage.getItem('authUser');
        if (storedUser) {
            this.user = JSON.parse(storedUser);
        }

        this.productId = Number(this.route.snapshot.paramMap.get('id'));

        this.commentService.getComments(this.productId).subscribe((comments) => {
        this.comments = comments;
        });

        this.tipo = this.route.snapshot.data['tipo'];
        if (this.tipo === 'ciega') this.isBlindAuction = true;
        else if (this.tipo === 'sorteo') this.isRaffle = true;

        switch (this.tipo) {
            case 'subasta':
            case 'ciega':
                this.productoService.getSubastaPorId(this.productId).subscribe((product) => {
                    this.product = product;
                });
                break;
            case 'sorteo':
                this.productoService.getSorteoPorId(this.productId).subscribe((product) => {
                    this.product = product;
                });
                break;
            default:
                console.error('Tipo de producto no válido');
        }
    }

    onSubmit() {
        if (this.cantidadPuja == null) {
            alert('Debes ingresar una cantidad.');
            return;
        }

        if (this.isRaffle && this.product.puntosNecesarios >= this.cantidadPuja) {
            alert('La cantidad dada debe ser mayor a la mínima para participar.');
            return;
        } else if (this.isBlindAuction && this.product.precioInicial >= this.cantidadPuja) {
            alert('La puja tiene que ser mayor que el precio base del producto.');
            return;
        } else if (!this.isBlindAuction && this.product.precioFinal >= this.cantidadPuja) {
            alert('La puja tiene que ser mayor que el precio actual del producto.');
            return;
        } else if (this.cantidadPuja > this.user.saldo) {
            alert('Saldo insuficiente.');
            return;
        }

        const storedUser = localStorage.getItem('authUser');

        const nuevaPuja: PujaDTO = {
            importe: this.cantidadPuja,
            fecha: new Date().toISOString().split('T')[0], // yyyy-MM-dd
            subastaId: this.productId,
            pujadorId: this.user.usuarioID
        }
        this.productoService.crearPuja(nuevaPuja).subscribe({
            error: (err) => {
                alert('❌ Error al realizar la puja.');
            }
        });
        
    }

    openExpertForm() {
        this.showExpertForm = true;
    }

    editExpertForm(comment: any) {
        this.editandoComentario = comment;
        this.comentarioEditado = {
        comment: comment.comentario,
        estimated_price: comment.precioEstimado
        };
        this.showExpertForm = true;
    }

    closeExpertForm() {
        this.showExpertForm = false;
        this.editandoComentario = null;
        this.comentarioEditado = { comment: '', estimated_price: 0 };
    }

    submitReview() {
        if (!this.comentarioEditado.comment || this.comentarioEditado.estimated_price <= 0) {
            alert('Debes completar todos los campos requeridos');
        return;
        }

        const productId = Number(this.route.snapshot.paramMap.get('id'));

        const nuevoComentario = {
        comentario: this.comentarioEditado.comment,
        precioEstimado: this.comentarioEditado.estimated_price,
        subastaId: productId,
        trabajadorId: this.user.usuarioID
        };

        this.commentService.createComment(nuevoComentario).subscribe({
        next: (res) => {
            console.log('Comentario creado:', res);
            this.comments.push(res);
            this.closeExpertForm();
        },
        error: (err) => {
            console.error('Error al crear comentario:', err);
        }
        });
    }

    saveEdit(): void {
        if (!this.editandoComentario) return;

        const updatedComentario = {
        comentario: this.comentarioEditado.comment,
        precioEstimado: this.comentarioEditado.estimated_price
        };

        this.commentService.editComment(this.editandoComentario.id, updatedComentario).subscribe({
            next: (res) => {
                const index = this.comments.findIndex(c => c.id === this.editandoComentario.id);
                if (index !== -1) {
                    this.comments[index] = { ...this.comments[index], ...res };
                }
                console.log('Comentario editado:', res);
                this.closeExpertForm();
            },
            error: (err) => {
                console.error('Error al editar comentario:', err);
            }
        });
    }

    cancelEdit(): void {
        this.closeExpertForm();
    }
}

