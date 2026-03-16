import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, HeaderComponent, FooterComponent, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // "email" representa correo o nombre de usuario
  email: string = '';
  password: string = '';
  remember: boolean = false;
  loginError: string = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    const loginData = {
      identificador: this.email,
      contrasena: this.password
    };

    this.auth.loginUser(loginData).subscribe({
      next: (data) => {
        // data.token contiene el JWT generado en el backend
        if (this.remember) {
          localStorage.setItem('authToken', data.token);
        }
        // Decodificamos el token y cargamos el perfil completo
        this.auth.setUserFromToken(data.token);
        this.router.navigateByUrl('/auction');
      },
      error: (err) => {
        console.error("Error en login:", err);
        this.loginError = "Credenciales incorrectas. Revisa tu identificador y contraseña.";
      }
    });
  }
}