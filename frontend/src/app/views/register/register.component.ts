import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { UserService, UsuarioCreate, UsuarioResponse } from '../../user.service'; 
import { AuthService } from '../../auth.service'; 

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, RouterModule, FooterComponent, HeaderComponent, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  // Propiedades para el formulario
  username: string = '';
  email: string = '';
  password: string = '';
  repeatPassword: string = '';
  city: string = '';
  postalCode: string = '';
  address1: string = '';
  address2: string = '';

  // Propiedades adicionales necesarias por el back-end
  firstName: string = '';       // nombre
  primerApellido: string = '';  
  segundoApellido: string = '';
  pais: string = 'España';       // Valor por defecto o a partir de otro campo
  saldo: number = 0;
  puntos: number = 0;
  numeroPiso: number = 0;
  letraPiso: string = '';

  formError: string = '';

  constructor(private router: Router, private userService: UserService,  private authService: AuthService) {}

  register(form: NgForm) {
    console.log("Formulario enviado", form);

    // Validación del formulario y comparación de contraseñas
    if (form.invalid || this.password !== this.repeatPassword) {
      this.formError = 'Por favor, revisa los campos con errores.';
      Object.values(form.controls).forEach(control => control.markAsTouched());
      return;
    }

    // Crear el objeto que se enviará al back-end. 
    // Aquí puedes ajustar los nombres de los campos según lo que espera tu DTO.
    const nuevoUsuario: UsuarioCreate = {
      nombre: this.firstName || this.username,  // Ajusta según convenga
      primerApellido: this.primerApellido,
      segundoApellido: this.segundoApellido,
      nombreUsuario: this.username,
      correoElectronico: this.email,
      contraseña: this.password,
      saldo: this.saldo,
      puntos: this.puntos,
      pais: this.pais,
      ciudad: this.city,
      codigoPostal: this.postalCode,
      calle: this.address1,
      numeroPiso: this.numeroPiso,
      letraPiso: this.letraPiso
    };

    // Realizar la llamada al servicio para registrar el usuario
    this.userService.registerUser(nuevoUsuario).subscribe({
      next: (respuesta: UsuarioResponse) => {
        console.log("Registro exitoso:", respuesta);
        this.authService.login('user'); 
        // Redirigir a la página de subastas u otra página
        this.router.navigate(['/auction']);
      },
      error: (error) => {
        console.error("Error al registrar usuario:", error);
        this.formError = 'Ha ocurrido un error durante el registro. Inténtalo nuevamente.';
      }
    });
  }
}