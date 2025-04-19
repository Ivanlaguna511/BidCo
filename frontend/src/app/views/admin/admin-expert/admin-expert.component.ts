// src/app/admin/admin-expert.component.ts
import { Component } from '@angular/core';
import { AdminService, ExpertDTO } from '../../../services/admin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-expert',
  standalone: true,
  imports: [CommonModule, FormsModule],
  styleUrl: './admin-expert.component.css',
  templateUrl: './admin-expert.component.html'
})
export class AdminExpertComponent {
  expert: ExpertDTO = {
    nombreUsuario:     '',
    correoElectronico: '',
    contrasena:        ''
  };
  message = '';

  constructor(private adminSvc: AdminService) {}

  submit() {
    this.adminSvc.createExpert(this.expert).subscribe({
      next: () => this.message = 'Experto registrado correctamente',
      error: e => this.message = 'Error: ' + e.message
    });
  }
}