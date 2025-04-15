import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from "../../components/header/header.component";
import { FooterComponent } from "../../components/footer/footer.component";
import { AuthService, UsuarioResponse } from "../../services/auth.service";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent, FooterComponent, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  email: string = '';
  password: string = '';
  remember: boolean = false;
  loginError: string = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    const loginData = {
      identificador: this.email,
      contraseña: this.password
    };

    this.auth.loginUser(loginData).subscribe({
      next: (resp: UsuarioResponse) => {
        console.log("Login exitoso:", resp);
        // Si se marcó "Recordarme", guardamos la info en localStorage
        if (this.remember) {
          localStorage.setItem('authUser', JSON.stringify(resp));
        } else {
          localStorage.removeItem('authUser');
        }
        // Actualizamos el estado en el servicio de autenticación y redirigimos
        this.auth.login('user');
        this.router.navigateByUrl('/auction');
      },
      error: (err) => {
        console.error("Error en login:", err);
        this.loginError = "Credenciales incorrectas. Por favor, revisa tu correo o nombre de usuario y contraseña.";
      }
    });
  }
}