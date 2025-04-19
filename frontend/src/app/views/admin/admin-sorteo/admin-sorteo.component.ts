// admin-sorteo.component.ts
import { Component } from '@angular/core';
import { AdminService, SorteoDTO } from '../../../services/admin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-sorteo',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './admin-sorteo.component.html',
  styleUrl: './admin-sorteo.component.css'
})
export class AdminSorteoComponent {
  sorteo: SorteoDTO = {
    nombreArticulo: '',
    descripcion: '',
    fechaInicio: '',
    fechaFin: '',
    puntosNecesarios: 0,
    imagen: null
  };
  message = '';
  constructor(private admin: AdminService) {}

  onFile(evt: Event) {
    const f = (evt.target as HTMLInputElement).files![0];
    this.sorteo.imagen = f;
  }

  submit() {
    const formData = new FormData();
    formData.append('nombreArticulo', this.sorteo.nombreArticulo);
    formData.append('descripcion', this.sorteo.descripcion);
    formData.append('fechaInicio', this.sorteo.fechaInicio);
    formData.append('fechaFin', this.sorteo.fechaFin);
    formData.append('puntosNecesarios', this.sorteo.puntosNecesarios.toString());
    if (this.sorteo.imagen) {
      formData.append('imagen', this.sorteo.imagen);
    }
    this.admin.createSorteo(formData).subscribe({
      next: () => this.message = 'Sorteo creado',
      error: e => this.message = 'Error: ' + e.message
    });
  }
}