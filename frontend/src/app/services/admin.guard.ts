// src/app/admin/admin.guard.ts
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
    const role = (this.auth as any).userRole.getValue?.() || '';
    if (role === 'admin') {
      return true;
    }
    this.router.navigateByUrl('/admin/login');
    return false;
  }
}
