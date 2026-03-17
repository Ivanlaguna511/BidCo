import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [FormsModule, RouterModule, HeaderComponent, FooterComponent],
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.css'
})
export class ForgotPasswordComponent {
  email: string = '';
  enviado: boolean = false;

  recoverPassword() {
    if (this.email) {
      // Aquí iría tu llamada al servicio backend: this.auth.recoverPassword(this.email)...
      this.enviado = true; 
    }
  }
}