import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Por simplicidad, usaremos un BehaviorSubject para el estado de login
  // Cambiar entre false y true para pruebas del header
  private loggedIn = new BehaviorSubject<boolean>(true);

  // Observable al que se puede suscribir para saber si está logueado
  isLoggedIn$ = this.loggedIn.asObservable();

  constructor() {}

  login() {
    // Aquí iría tu lógica real de login
    this.loggedIn.next(true);
  }

  logout() {
    // Lógica de logout
    this.loggedIn.next(false);
  }

  // Método rápido para saber si está logueado (sin usar Observable)
  isLoggedIn(): boolean {
    return this.loggedIn.value;
  }
}