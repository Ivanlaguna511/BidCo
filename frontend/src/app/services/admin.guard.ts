import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class AdminGuard implements CanActivate {
  constructor(
    private auth: AuthService,
    private router: Router
  ) {}

  canActivate(): boolean {
    // Leemos el rol directamente del BehaviorSubject expuesto como observable
    // Para acceso síncrono usamos el valor actual del subject
    const currentRole = this.auth.getCurrentRole();
    if (currentRole === 'admin') {
      return true;
    }
    this.router.navigateByUrl('/admin/login');
    return false;
  }
}