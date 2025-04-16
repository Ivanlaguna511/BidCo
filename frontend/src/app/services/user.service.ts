import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UsuarioResponse {
  usuarioID: number;
  nombreUsuario: string;
  correoElectronico: string;
  contraseña?: string;
  ciudad: string;
  codigoPostal: string;
  calle: string;
  numeroPiso: number;
  letraPiso?: string;
  pais: string;
  saldo?: number;
  puntos?: number;
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

export interface UsuarioUpdate {
  nombreUsuario: string;
  correoElectronico: string;
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

  // Actualiza el usuario (PUT)
  updateUser(id: number, usuario: UsuarioUpdate): Observable<UsuarioResponse> {
    const token = localStorage.getItem('authToken');
    return this.http.put<UsuarioResponse>(
      `${this.apiUrl}/${id}`, 
      usuario,
      {
        headers: new HttpHeaders({
          'Authorization': `Bearer ${token}`
        })
      }
    );
  }

  // Registro (POST)
  registerUser(usuario: UsuarioCreate): Observable<UsuarioResponse> {
    return this.http.post<UsuarioResponse>(this.apiUrl, usuario);
  }

  // Obtiene los datos del usuario por ID (GET)
  getUserById(id: number): Observable<UsuarioResponse> {
    return this.http.get<UsuarioResponse>(`${this.apiUrl}/${id}`);
  }
}