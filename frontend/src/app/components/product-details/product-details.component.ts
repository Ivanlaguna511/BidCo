import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

import { ProductoService, SorteoResponseDto, SubastaResponseDto, UsuarioResponseDto } from '../../services/product.service';

@Component({
  selector: 'app-product-details',
  imports: [CommonModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
    productoSubasta!: SubastaResponseDto;
    productoSorteo!: SorteoResponseDto;
    nombreUsuario = "";
    tipo = "";
    
    constructor(private route: ActivatedRoute, private productoService: ProductoService) {}

    ngOnInit() {
        //Obtener el id de la URL
        const productId = Number(this.route.snapshot.paramMap.get('id'));
        
        //Determinar si el producto es de una subasta normal, a ciegas o de un sorteo
        this.tipo = this.route.snapshot.data['tipo'];

        //obtener el producto correspondiente
        switch (this.tipo) {
            case 'subasta':
                this.productoService.getSubastaPorId(productId).subscribe((product) => {
                    this.productoSubasta = product;
                    
                    this.productoService.obtenerPrivacidad(this.productoSubasta.creadorId).subscribe((userPriv) => {
                        if(userPriv.privacidadAnonimoPujas === false) {
                            this.productoService.getUsuarioPorId(this.productoSubasta.creadorId).subscribe((user) => {
                                this.nombreUsuario = user.nombreUsuario;
                            })
                        } else {
                            this.nombreUsuario = "Anónimo";
                        }
                    })
                });
                break;
        
            case 'ciega':
                this.productoService.getSubastaPorId(productId).subscribe((product) => {
                    this.productoSubasta = product;
                    
                    this.productoService.obtenerPrivacidad(this.productoSubasta.creadorId).subscribe((userPriv) => {
                        if(userPriv.privacidadAnonimoPujas === false) {
                            this.productoService.getUsuarioPorId(this.productoSubasta.creadorId).subscribe((user) => {
                                this.nombreUsuario = user.nombreUsuario;
                            })
                        } else {
                            this.nombreUsuario = "Anónimo";
                        }
                    })
                });
                break;
        
            case 'sorteo':
                this.productoService.getSorteoPorId(productId).subscribe((product) => {
                    this.productoSorteo = product;
                    console.log(this.productoSorteo);
                });
                break;
        
            default:
                console.error('Tipo de producto no válido');
                break;
        }
    }
}
