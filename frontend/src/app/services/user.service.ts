// user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UsuarioResponse {
  usuarioID: number;
  nombreUsuario: string;
  correoElectronico: string;
  ciudad: string;
  codigoPostal: string;
  calle: string;
  numeroPiso: number;
  letraPiso?: string;
  pais: string;
  // Otros campos...
}

export interface UsuarioCreate {
  nombreUsuario: string;
  correoElectronico: string;
  contraseña: string;
  saldo: number;
  puntos: number;
  pais: string;
  ciudad: string;
  codigoPostal: string;
  calle: string;
  numeroPiso: number;
  letraPiso?: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl: string = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  // Registro ya existente
  registerUser(usuario: UsuarioCreate): Observable<UsuarioResponse> {
    return this.http.post<UsuarioResponse>(this.apiUrl, usuario);
  }

  // Actualizar datos del usuario
  updateUser(usuario: UsuarioCreate): Observable<UsuarioResponse> {
    return this.http.put<UsuarioResponse>(this.apiUrl, usuario);
  }

  // (Opcional) Obtener datos del usuario por ID
  getUserById(id: number): Observable<UsuarioResponse> {
    return this.http.get<UsuarioResponse>(`${this.apiUrl}/${id}`);
  }
}