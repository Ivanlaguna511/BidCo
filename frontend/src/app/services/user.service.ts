import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Interfaz para la respuesta del usuario
export interface UsuarioResponse {
  usuarioID: number;
  nombreUsuario: string;
  correoElectronico: string;
}

// Interfaz para los datos que se envían al back-end
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
  // URL base de la API
  private apiUrl: string = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  // Método para registrar usuario
  registerUser(usuario: UsuarioCreate): Observable<UsuarioResponse> {
    return this.http.post<UsuarioResponse>(this.apiUrl, usuario);
  }
}
