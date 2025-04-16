import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService, UsuarioResponse } from '../../../services/auth.service';
import { UserService, UsuarioCreate } from '../../../services/user.service';

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
    this.authService.currentUser$.subscribe((userData: UsuarioResponse | null) => {
      console.log('currentUser$ emitió:', userData); // Debug
      if (userData) {
        this.user = userData;
      } else {
        this.errorMessage = 'No se encontraron datos del usuario. Por favor, inicia sesión nuevamente.';
      }
    });
  
    // Opcional: como respaldo, si localStorage tiene datos y currentUser aun es el default
    if (!this.user.usuarioID) {
      const storedUser = localStorage.getItem('authUser');
      if (storedUser) {
        this.user = JSON.parse(storedUser);
        console.log('Datos cargados desde localStorage:', this.user);
      }
    }
  }  

  saveChanges() {
    const updatedUser: UsuarioCreate = {
      nombreUsuario: this.user.nombreUsuario,
      correoElectronico: this.user.correoElectronico,
      contraseña: this.user.contraseña ? this.user.contraseña : '',
      saldo: this.user.saldo || 0,
      puntos: this.user.puntos || 0,
      pais: this.user.pais,
      ciudad: this.user.ciudad,
      codigoPostal: this.user.codigoPostal,
      calle: this.user.calle,
      numeroPiso: this.user.numeroPiso,
      letraPiso: this.user.letraPiso
    };

    this.userService.updateUser(updatedUser).subscribe({
      next: (response: UsuarioResponse) => {
        this.authService.setUser(response);
        this.updateMessage = 'Datos actualizados correctamente.';
        this.errorMessage = '';
      },
      error: (error) => {
        console.error("Error actualizando usuario:", error);
        this.errorMessage = 'No se pudieron actualizar los datos. Inténtalo de nuevo.';
        this.updateMessage = '';
      }
    });
  }

  updatePassword() {
    if (this.newPassword !== this.confirmPassword) {
      this.errorMessage = 'La nueva contraseña y la confirmación no coinciden.';
      return;
    }
    if (this.currentPassword !== (this.user.contraseña || '')) {
      this.errorMessage = 'La contraseña actual es incorrecta.';
      return;
    }
    this.user.contraseña = this.newPassword;
    this.userService.updateUser({
      nombreUsuario: this.user.nombreUsuario,
      correoElectronico: this.user.correoElectronico,
      contraseña: this.user.contraseña,
      saldo: this.user.saldo || 0,
      puntos: this.user.puntos || 0,
      pais: this.user.pais,
      ciudad: this.user.ciudad,
      codigoPostal: this.user.codigoPostal,
      calle: this.user.calle,
      numeroPiso: this.user.numeroPiso,
      letraPiso: this.user.letraPiso
    }).subscribe({
      next: (response: UsuarioResponse) => {
        this.authService.setUser(response);
        this.updateMessage = 'Contraseña actualizada correctamente.';
        this.errorMessage = '';
        this.currentPassword = '';
        this.newPassword = '';
        this.confirmPassword = '';
      },
      error: (error) => {
        console.error("Error actualizando contraseña:", error);
        this.errorMessage = 'No se pudo actualizar la contraseña.';
        this.updateMessage = '';
      }
    });
  }
}