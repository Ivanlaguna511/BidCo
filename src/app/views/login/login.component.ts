import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../../components/header/header.component";
import { FooterComponent } from "../../components/footer/footer.component";
import { AuthService } from "../../auth.service";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  // Asegúrate de incluir RouterModule para que funcionen tanto routerLink como la navegación por código
  imports: [RouterModule, HeaderComponent, FooterComponent, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    // Validación simple de credenciales
    if (this.email === 'usuarioX@bidco.com' && this.password === '123456') {
      this.auth.login('user');
      // Usa navigateByUrl o navigate, según prefieras
      this.router.navigateByUrl('/auction');
    } else {
      alert('Credenciales incorrectas');
    }
  }
}
