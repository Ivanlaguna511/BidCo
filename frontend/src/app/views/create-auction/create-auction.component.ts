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
    imagen: File | null = null;
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
        const storedUser = localStorage.getItem('authUser');
        
        if(storedUser) {
            const user = JSON.parse(storedUser);
            this.subasta.creadorId = user.usuarioID;

            const formData = new FormData();
            formData.append('subasta', new Blob([JSON.stringify(this.subasta)], { type: 'application/json' }));

            if (this.imagen) {
                formData.append('imagen', this.imagen);
            }
        
            this.subastaService.crearSubasta(formData).subscribe({
                next: (res) => {
                    console.log('Subasta creada con éxito:', res);
                    this.router.navigate(['/']);
                },
                error: (err) => {
                    console.error('Error al crear subasta:',err.message, err.error);
                }
            });
        }
    }

    onImgSelected(event: Event): void {
        const input = event.target as HTMLInputElement;

        if (input.files && input.files.length > 0) {
            this.imagen = input.files[0];
        }
    }
}
