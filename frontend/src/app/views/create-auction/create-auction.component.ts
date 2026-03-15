import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from "../../components/footer/footer.component";
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


import { CreateSubastaService, SubastaCreateDTO } from '../../services/create-auction.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-create-auction',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
    FormsModule
],
  templateUrl: './create-auction.component.html',
  styleUrls: ['./create-auction.component.css']
})
export class CreateAuctionComponent {
    imagen: File | null = null;
    imagenPreview: string | ArrayBuffer | null = null;
    subasta: SubastaCreateDTO = {
        fechaInicial: new Date().toISOString().split('T')[0],
        fechaFinal: '',
        precioInicial: 0,
        subastaNormal: true,
        nombreArticulo: '',
        descripcion: '',
        creadorId: 1,
        categoria: ''
    };
    formularioInvalido: boolean = false;
    
    constructor(private crearSubastaService: CreateSubastaService, private router: Router, private authService: AuthService) {}
    
    onSubmit(form: any) {
        this.formularioInvalido = false;

        if (!form.valid) {
            this.formularioInvalido = true;
            return;
        }

        const hoy = new Date();
        const fechaFin = new Date(this.subasta.fechaFinal);

        if (fechaFin < hoy) {
            this.formularioInvalido = true;
            return;
        }

        if (this.imagenPreview === null) {
            this.formularioInvalido = true;
            return;
        }

        if (this.subasta.categoria === "") {
            this.formularioInvalido = true;
            return;
        }
        
        const storedUser = localStorage.getItem('authUser');
        
        if(storedUser) {
            const user = JSON.parse(storedUser);
            this.subasta.creadorId = user.usuarioID;

            const formData = new FormData();
            formData.append('subasta', new Blob([JSON.stringify(this.subasta)], { type: 'application/json' }));

            if (this.imagen) {
                formData.append('imagen', this.imagen);
            }
        
            this.crearSubastaService.crearSubasta(formData).subscribe({
                next: (res) => {
                    console.log('Subasta creada con éxito:', res);
                    this.authService.loadUserProfile(user.usuarioID).subscribe({
                        next: (userActualizado) => this.authService.setUser(userActualizado)
                    });
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

            const reader = new FileReader();
            reader.onload = () => {
                this.imagenPreview = reader.result;
            };
            reader.readAsDataURL(this.imagen);
        }
    }
}
