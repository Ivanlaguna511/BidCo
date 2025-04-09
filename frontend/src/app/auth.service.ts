import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Por simplicidad, usaremos un BehaviorSubject para el estado de login
  // Cambiar entre false y true para pruebas del header
  private loggedIn = new BehaviorSubject<boolean>(false);
  private userRole = new BehaviorSubject<string | null>(null);

  // Observable al que se puede suscribir para saber si está logueado y que rol tiene el usuario
  isLoggedIn$ = this.loggedIn.asObservable();
  userRole$ = this.userRole.asObservable();

  constructor() {}

  login(role: 'user' | 'expert') {
    // Aquí iría tu lógica real de login
    this.loggedIn.next(true);
    this.userRole.next(role);
  }

  logout() {
    // Lógica de logout
    this.loggedIn.next(false);
    this.userRole.next(null);
  }

  // Método rápido para saber si está logueado (sin usar Observable)
  isLoggedIn(): boolean {
    return this.loggedIn.value;
  }

  isExpert(): boolean {
    return this.userRole.value === 'expert';
  }
}