import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FooterComponent } from '../../components/footer/footer.component';
import { HeaderComponent } from '../../components/header/header.component';
import { FormsModule, NgForm } from '@angular/forms';

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

  // Mensaje general de error (para informar que hay errores en el formulario)
  formError: string = '';

  constructor(private router: Router) {}

  register(form: NgForm) {
    console.log("Formulario enviado", form);
    // Si el formulario es inválido o las contraseñas no coinciden
    if (form.invalid || this.password !== this.repeatPassword) {
      this.formError = 'Por favor, revisa los campos con errores.';
      // Marca todos los controles como touched para mostrar los mensajes de error
      Object.values(form.controls).forEach(control => control.markAsTouched());
      return;
    }

    // Si todo es correcto, limpia el mensaje de error y navega a /auction
    this.formError = '';
    console.log("Registro exitoso");
    // Aquí iría la lógica para registrar al usuario (p. ej., llamar a un servicio)
    this.router.navigate(['/auction']);
  }
}
