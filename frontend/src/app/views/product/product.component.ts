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
import { ProductoService, PujaCreateDTO, PujaResponseDTO, PujaSorteoCreateDTO} from '../../services/product.service';
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
    tipo = '';
    isBlindAuction: boolean = false;
    isRaffle: boolean = false;
    countdown: string = '';
    
    user: any;
    isLoggedIn = false;
    cantidadPuja: number | null = null;
    candidatoGanadorPuja: string | null = null;
    ganadorSorteo: string | null = null;

    expert = DATA_EXPERT;
    isExpert = false;
    comments: any[] = [];
    showExpertForm = false;
    isExpertComment = false;
    editandoComentario: any = null;
    estimatedPrice = 0;
    reviewText = '';
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
                    this.setupCountdown(this.product.fechaFinal);
                });
                break;
            case 'sorteo':
                this.productoService.getSorteoPorId(this.productId).subscribe((product) => {
                    this.product = product;
                    this.setupCountdown(this.product.fechaFin);
                });
                break;
            default:
                console.error('Tipo de producto no válido');
        }
        
            
        
        this.productoService.obtenerPujaMaximaPorSubasta(this.productId).subscribe((puja) => {
            if (!puja || !puja.pujadorID) {
                return;
            }
            
            const userId = puja.pujadorID;
            this.productoService.obtenerPrivacidad(userId).subscribe((userPriv) => {
                if(userPriv.privacidadAnonimoPujas === false) {
                    this.productoService.getUsuarioPorId(userId).subscribe((user) => {
                        this.candidatoGanadorPuja = user.nombreUsuario;
                    })
                } else {
                    this.candidatoGanadorPuja = "Anónimo";
                }
            })
        })

    }

    onSubmit() {
        if(this.user.usuarioID == this.product.creadorId) {
            alert('No puedes pujar en una subasta que has creado.');
            return;
        }

        if (this.cantidadPuja == null) {
            alert('Debes ingresar una cantidad.');
            return;
        }

        if (this.isRaffle && this.product.puntosNecesarios > this.cantidadPuja) {
            alert('La cantidad dada debe ser mayor a la mínima para participar.');
            return;
        } else if (this.isBlindAuction && this.product.precioInicial >= this.cantidadPuja) {
            alert('La puja tiene que ser mayor que el precio base del producto.');
            return;
        } else if (!this.isBlindAuction && this.product.precioFinal >= this.cantidadPuja) {
            alert('La puja tiene que ser mayor que el precio actual del producto.');
            return;
        } else if (this.isRaffle && this.cantidadPuja > this.user.puntos) {
            alert('No tienes suficientes puntos para participar.');
            return;
        } else if (!this.isRaffle && this.cantidadPuja > this.user.saldo) {
            alert('Saldo insuficiente.');
            return;
        }
        alert('Puja realizada correctamente.');
        if (this.isRaffle) {
            const nuevaPujaSorteo: PujaSorteoCreateDTO = {
                puntos: this.cantidadPuja,
                fecha: new Date().toISOString().split('T')[0], // yyyy-MM-dd
                sorteoId: this.productId,
                pujadorId: this.user.usuarioID
            }
            this.productoService.crearPujaSorteo(nuevaPujaSorteo).subscribe({
                next: () => {
                    this.authService.loadUserProfile(this.user.usuarioID).subscribe({
                        next: (usuarioActualizado) => {
                            this.user = usuarioActualizado;
                            localStorage.setItem('authUser', JSON.stringify(usuarioActualizado));
                        },
                        error: (err) => {
                            console.error('Error al actualizar el usuario:', err);
                        }
                    });
                    this.ngOnInit();
                },
                error: (err) => {
                    alert('❌ Error al realizar la puja.');
                }
            });

        } else {
            const nuevaPuja: PujaCreateDTO = {
                importe: this.cantidadPuja,
                fecha: new Date().toISOString().split('T')[0], // yyyy-MM-dd
                subastaId: this.productId,
                pujadorId: this.user.usuarioID
            }
            this.productoService.crearPuja(nuevaPuja).subscribe({
                next: () => {this.ngOnInit();},
                error: (err) => {
                    alert('❌ Error al realizar la puja.');
                }
            });
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
                
                if(!this.product.ganador) {
                    switch (this.tipo) {
                        case 'subasta':
                        case 'ciega':
                            this.productoService.finalizarSubasta(this.productId).subscribe();
                            break;
                        case 'sorteo':
                            this.productoService.finalizarSorteo(this.productId).subscribe((sorteoActualizado) => {
                                this.product = sorteoActualizado;
                                this.setGanadorSorteo();
                            });
                            break;
                    }
                }
                            
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

    setGanadorSorteo() {
        console.log(this.product.ganador);
        this.productoService.obtenerPrivacidad(this.product.ganador).subscribe((userPriv) => {
            if(userPriv.privacidadAnonimoPujas === false) {
                this.productoService.getUsuarioPorId(this.product.ganador).subscribe((user) => {
                    this.ganadorSorteo = user.nombreUsuario;
                })
            } else {
                this.ganadorSorteo = "Anónimo";
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

