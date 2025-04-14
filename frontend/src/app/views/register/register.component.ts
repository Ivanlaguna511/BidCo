import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { UserService, UsuarioCreate, UsuarioResponse } from '../../services/user.service'; 
import { AuthService } from '../../services/auth.service'; 

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, RouterModule, FooterComponent, HeaderComponent, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  // Propiedades del formulario
  username: string = '';
  email: string = '';
  password: string = '';
  repeatPassword: string = '';
  city: string = '';
  postalCode: string = '';

  // Nuevos campos de dirección:
  calle: string = '';
  numeroPiso: string = '';  // Se almacena como string y luego se convierte a number
  letraPiso: string = '';     // Opcional

  // Propiedades adicionales requeridas por el back-end
  pais: string = 'España';      // Valor por defecto
  saldo: number = 0;
  puntos: number = 0;

  formError: string = '';

  constructor(private router: Router, private userService: UserService, private authService: AuthService) {}

  register(form: NgForm) {
    console.log("Formulario enviado", form);

    // Validación: campos inválidos o contraseñas que no coinciden
    if (form.invalid || this.password !== this.repeatPassword) {
      this.formError = 'Por favor, revisa los campos con errores.';
      Object.values(form.controls).forEach(control => control.markAsTouched());
      return;
    }

    // Crear el objeto a enviar al back-end con los nombres de propiedad que coinciden con la BD
    const nuevoUsuario: UsuarioCreate = {
      nombreUsuario: this.username,
      correoElectronico: this.email,
      contraseña: this.password,
      saldo: this.saldo,
      puntos: this.puntos,
      pais: this.pais,
      ciudad: this.city,
      codigoPostal: this.postalCode,
      calle: this.calle,
      numeroPiso: +this.numeroPiso, // Conversión a number
      letraPiso: this.letraPiso
    };

    // Llamada al servicio para registrar al usuario
    this.userService.registerUser(nuevoUsuario).subscribe({
      next: (respuesta: UsuarioResponse) => {
        console.log("Registro exitoso:", respuesta);
        this.authService.login('user'); 
        this.router.navigate(['/auction']);
      },
      error: (error) => {
        console.error("Error al registrar usuario:", error);
        if (error.error && error.error.message) {
          this.formError = `Error: ${error.error.message}`;
        } else {
          this.formError = `Ha ocurrido un error durante el registro: ${error.statusText || 'Error desconocido'}. Inténtalo nuevamente.`;
        }
      }
    });
  }
}
