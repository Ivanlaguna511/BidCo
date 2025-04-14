import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from "../../components/footer/footer.component";
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { SubastaService, SubastaCreateDTO } from '../../services/create-auction.service';

@Component({
  selector: 'app-create-auction',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
    FormsModule],
  templateUrl: './create-auction.component.html',
  styleUrls: ['./create-auction.component.css']
})
export class CreateAuctionComponent {
    subasta: SubastaCreateDTO = {
        fechaInicial: new Date().toISOString().split('T')[0],
        fechaFinal: '',
        precioInicial: 0,
        subastaNormal: true,
        nombreArticulo: '',
        descripcion: '',
        creadorId: 1 
    };
    
    constructor(private subastaService: SubastaService, private router: Router) {}
    
    onSubmit() {
        this.subastaService.crearSubasta(this.subasta).subscribe({
            next: (res) => {
                console.log('Subasta creada con éxito:', res);
                this.router.navigate(['/']);
            },
            error: (err) => {
                console.error('Error al crear subasta:', err);
            }
        });
    }
}
