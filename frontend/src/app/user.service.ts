import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Puedes definir una interfaz para la respuesta
export interface UsuarioResponse {
  usuarioID: number;
  nombreUsuario: string;
  correoElectronico: string;
}

// También puedes crear una interfaz para los datos que envías (si no usas directamente el DTO)
export interface UsuarioCreate {
  // Asegúrate de que los nombres de propiedad coincidan con los que espera el back-end,
  // por ejemplo, primerApellido, segundoApellido, etc.
  nombre: string;
  primerApellido: string;
  segundoApellido: string;
  nombreUsuario: string;
  correoElectronico: string;
  contraseña: string;
  saldo: number;
  puntos: number;
  pais: string;
  ciudad: string;
  codigoPostal: string;
  calle: string;
  numeroPiso?: number;
  letraPiso?: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // Define la URL base de tu API. Si usas variables de entorno, ponla en environment.ts.
  private apiUrl: string = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  // Método para registrar usuario
  registerUser(usuario: UsuarioCreate): Observable<UsuarioResponse> {
    return this.http.post<UsuarioResponse>(this.apiUrl, usuario);
  }
}
