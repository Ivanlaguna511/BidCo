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
  user: UsuarioResponse;
  updateMessage: string = '';
  errorMessage: string = '';

  // Variables para cambio de contraseña
  currentPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';

  constructor(private authService: AuthService, private userService: UserService) {}

  ngOnInit() {
    const storedUser = localStorage.getItem('authUser');
    if (storedUser) {
      this.user = JSON.parse(storedUser);
    } else {
      this.errorMessage = 'No se encontraron datos del usuario. Por favor, inicia sesión nuevamente.';
    }
  }

  saveChanges() {
    // Se construye el objeto con la información que se quiere actualizar:
    const updatedUser: UsuarioCreate = {
      nombreUsuario: this.user.nombreUsuario,
      correoElectronico: this.user.correoElectronico,
      contraseña: this.user.contraseña, // En un caso real se separaría la actualización de datos y la contraseña
      saldo: 0, // Suponiendo que no se edita en el perfil
      puntos: 0,
      pais: this.user.pais,
      ciudad: this.user.ciudad,
      codigoPostal: this.user.codigoPostal,
      calle: this.user.calle,
      numeroPiso: this.user.numeroPiso,
      letraPiso: this.user.letraPiso
    };

    this.userService.updateUser(updatedUser).subscribe({
      next: (response: UsuarioResponse) => {
        this.authService.currentUser$.next(response);
        localStorage.setItem('authUser', JSON.stringify(response));
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
    if (this.currentPassword !== this.user.contraseña) {  // Nota: en producción, la verificación se realiza en el backend
      this.errorMessage = 'La contraseña actual es incorrecta.';
      return;
    }
    // Actualizamos la contraseña y realizamos la llamada de actualización
    this.user.contraseña = this.newPassword;
    this.userService.updateUser(this.user).subscribe({
      next: (response: UsuarioResponse) => {
        this.authService.currentUser$.next(response);
        localStorage.setItem('authUser', JSON.stringify(response));
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