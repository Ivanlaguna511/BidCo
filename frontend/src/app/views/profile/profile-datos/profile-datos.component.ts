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
    const updatedData: UsuarioUpdate = {
      nombreUsuario: this.user.nombreUsuario,
      correoElectronico: this.user.correoElectronico,
      ciudad: this.user.ciudad,
      codigoPostal: this.user.codigoPostal,
      calle: this.user.calle,
      numeroPiso: this.user.numeroPiso,
      letraPiso: this.user.letraPiso
    };
  
    this.userService.updateUser(this.user.usuarioID, updatedData).subscribe({
      next: (response) => {
        this.authService.setUser(response);
        this.updateMessage = '¡Datos actualizados correctamente!';
        setTimeout(() => this.updateMessage = '', 3000);
      },
      error: (error) => {
        this.errorMessage = 'Error al actualizar: ' + error.error.message;
      }
    });
  }

  updatePassword() {
    //En progreso: Implementar la lógica para cambiar la contraseña
  }
}