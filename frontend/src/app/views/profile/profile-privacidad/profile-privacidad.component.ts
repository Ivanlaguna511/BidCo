import { Component, OnInit } from '@angular/core';
import { PrivacityService, Privacidad } from '../../../services/privacity.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile-privacidad',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile-privacidad.component.html',
  styleUrls: ['./profile-privacidad.component.css']
})
export class ProfilePrivacidadComponent implements OnInit {
  privacidad: Privacidad = {
    privacidadAnonimoPujas: false,
    privacidadEstadisticas: true,
    privacidadPerfilVisible: true
  };
  cargando = true;
  usuarioID: number = 0;

  ngOnInit() {
    const storedUser = localStorage.getItem('authUser');
    if (storedUser) {
      const user = JSON.parse(storedUser);
      this.usuarioID = user.usuarioID;
      this.privacityService.getPrivacidad(this.usuarioID).subscribe({
        next: data => {
          this.privacidad = data;
          this.cargando = false;
        },
        error: err => {
          console.error('Error al cargar privacidad:', err);
          this.cargando = false;
        }
      });
    } else {
      this.cargando = false;
    }
  }

  constructor(private privacityService: PrivacityService) {}

  guardarPrivacidad() {
    this.privacityService.updatePrivacidad(this.usuarioID, this.privacidad).subscribe();
  }
}