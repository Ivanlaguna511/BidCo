import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService, UsuarioResponse } from '../../../services/auth.service';
import { UserService, UsuarioUpdate } from '../../../services/user.service';

@Component({
  selector: 'app-profile-datos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile-datos.component.html',
  styleUrls: ['./profile-datos.component.css']
})
export class ProfileDatosComponent implements OnInit {
  user: UsuarioResponse = {
    usuarioID: 0,
    nombreUsuario: '',
    correoElectronico: '',
    contraseña: '',
    ciudad: '',
    codigoPostal: '',
    calle: '',
    numeroPiso: 0,
    letraPiso: '',
    pais: '',
    saldo: 0,
    puntos: 0,
  };

  updateMessage: string = '';
  errorMessage: string = '';

  // Variables para cambio de contraseña
  currentPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';

  constructor(private authService: AuthService, private userService: UserService) {}

  ngOnInit() {
    const storedUser = localStorage.getItem('authUser');
    
    // Si hay usuario almacenado pero falta información
    if (storedUser) {
      this.user = JSON.parse(storedUser);
      if (!this.user.nombreUsuario) { // Verifica un campo obligatorio
        this.loadUserFromBackend();
      }
    } else {
      this.loadUserFromBackend();
    }
  }
  
  private loadUserFromBackend() {
    const userId = this.authService.currentUser.value?.usuarioID;
    if (userId) {
      this.userService.getUserById(userId).subscribe({
        next: (user) => {
          this.user = user;
          this.authService.setUser(user);
        },
        error: (err) => console.error('Error cargando usuario:', err)
      });
    }
  }

  saveChanges() {
    const updatedData = {
      nombreUsuario: this.user.nombreUsuario,
      correoElectronico: this.user.correoElectronico,
      ciudad: this.user.ciudad,
      codigoPostal: this.user.codigoPostal,
      calle: this.user.calle,
      numeroPiso: this.user.numeroPiso,
      letraPiso: this.user.letraPiso,
      // Campos requeridos por el backend pero no editables
      pais: this.user.pais,
      saldo: this.user.saldo,
      puntos: this.user.puntos,
      contraseña: this.user.contraseña // Mantenemos la contraseña actual
    };
  
    this.userService.updateUser(this.user.usuarioID, updatedData).subscribe({
      next: (response) => {
        this.authService.setUser(response);
        this.updateMessage = '¡Datos actualizados!';
        setTimeout(() => this.updateMessage = '', 3000);
      },
      error: (error) => {
        this.errorMessage = (error.error?.message || 'Error desconocido');
      }
    });
  }
  
  updatePassword() {
    if (this.newPassword !== this.confirmPassword) {
      this.errorMessage = 'Las contraseñas no coinciden.';
      return;
    }
  
    this.userService.updatePassword(this.user.usuarioID, {
      currentPassword: this.currentPassword,
      newPassword: this.newPassword
    }).subscribe({
      next: () => {
        this.updateMessage = '¡Contraseña actualizada correctamente!';
        setTimeout(() => this.updateMessage = '', 3000);
        this.currentPassword = '';
        this.newPassword = '';
        this.confirmPassword = '';
      },
      error: (error) => {
        this.errorMessage = error.message || 'Error al cambiar la contraseña';
        setTimeout(() => this.errorMessage = '', 5000); 
      }
    });
  }

  cantidadRecarga: number = 0;
  recargaMessage: string = '';

  recargarSaldo() {
    if (this.cantidadRecarga > 0) {
      this.userService.recargarSaldo(this.user.usuarioID, this.cantidadRecarga).subscribe({
        next: (nuevoSaldo) => {
          this.user.saldo = nuevoSaldo;
          this.recargaMessage = '¡Saldo recargado correctamente!';
          this.cantidadRecarga = 0;
          setTimeout(() => this.recargaMessage = '', 5000);
        },
        error: () => {
          this.recargaMessage = 'Error al recargar saldo.';
          setTimeout(() => this.recargaMessage = '', 5000);
        }
      });
    }
}
}