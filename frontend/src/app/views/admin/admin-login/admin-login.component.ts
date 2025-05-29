// src/app/admin/admin-login.component.ts
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../../../services/admin.service';
import { AuthService }  from '../../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-login.component.html',
  styleUrl: './admin-login.component.css'
})
export class AdminLoginComponent {
  username = '';
  password = '';
  error    = '';

  constructor(
    private adminSvc: AdminService,
    private auth: AuthService,
    private router: Router
  ) {}

  login() {
    this.adminSvc.loginAdmin({ username: this.username, password: this.password, rol: false})
      .subscribe({
        next: () => {
          this.auth.setUserRole('admin');
          this.router.navigateByUrl('/admin');
        },
        error: err => this.error = err.message
      });
  }
}