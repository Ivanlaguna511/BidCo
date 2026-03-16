import { Component, OnInit } from '@angular/core'; // Añadido OnInit
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../environments/environment'; // IMPORTANTE: Verifica que la ruta sea correcta
import { ProductoService, SorteoResponseDto, SubastaResponseDto } from '../../services/product.service';

@Component({
  selector: 'app-product-details',
  standalone: true, // Asegúrate de si es standalone o no según tu proyecto
  imports: [],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {
    productoSubasta!: SubastaResponseDto;
    productoSorteo!: SorteoResponseDto;
    nombreUsuario = "";
    tipo = "";

    constructor(private route: ActivatedRoute, private productoService: ProductoService) {}

    ngOnInit() {
        const productId = Number(this.route.snapshot.paramMap.get('id'));
        this.tipo = this.route.snapshot.data['tipo'];

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
        
            case 'sorteo':
                this.productoService.getSorteoPorId(productId).subscribe((product) => {
                    this.productoSorteo = product;
                });
                break;
        }
    }
}