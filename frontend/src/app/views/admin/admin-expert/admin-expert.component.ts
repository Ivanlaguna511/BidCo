// src/app/admin/admin-expert.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminService, ExpertDTO } from '../../../services/admin.service';

@Component({
  selector: 'app-admin-expert',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-expert.component.html',
  styleUrls: ['./admin-expert.component.css']
})
export class AdminExpertComponent {
  // Inicializamos con las mismas propiedades que ExpertDTO
  expert: ExpertDTO = {
    nombreUsuario:     '',
    correoElectronico: '',
    contrasena:        '',
    experto:          true
  };

  message: string = '';

  constructor(private adminSvc: AdminService) {}

  submit(): void {
    // Llamada al servicio para registrar un experto
    this.adminSvc.createExpert(this.expert).subscribe({
      next: () => {
        this.message = 'Experto registrado correctamente';
        // Opcional: limpiar el formulario
        this.expert = { nombreUsuario: '', correoElectronico: '', contrasena: '', experto: true };
      },
      error: (err: Error) => {
        this.message = 'Error: ' + err.message;
      }
    });
  }
}