// auth.service.ts (fragmento)
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export interface UsuarioResponse {
  usuarioID: number;
  nombreUsuario: string;
  correoElectronico: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);
  private userRole = new BehaviorSubject<string | null>(null);

  isLoggedIn$ = this.loggedIn.asObservable();
  userRole$ = this.userRole.asObservable();

  // URL base de la API (ajústala si es necesario)
  private apiUrl: string = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  // Método de login: envía identificador y contraseña para autenticar
  loginUser(loginData: { identificador: string, contraseña: string }): Observable<UsuarioResponse> {
    return this.http.post<UsuarioResponse>(`${this.apiUrl}/login`, loginData);
  }

  // Método para actualizar el estado de login (puedes ampliarlo, por ejemplo, guardando JWT)
  setLogin(value: boolean, role: string | null = null) {
    this.loggedIn.next(value);
    this.userRole.next(role);
  }

  login(role: 'user' | 'expert') {
    this.setLogin(true, role);
  }

  logout() {
    this.setLogin(false, null);
  }

  isLoggedIn(): boolean {
    return this.loggedIn.value;
  }

  isExpert(): boolean {
    return this.userRole.value === 'expert';
  }
}