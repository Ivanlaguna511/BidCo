import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';

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
  // Otros campos adicionales
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);
  private currentUser = new BehaviorSubject<UsuarioResponse | null>(null);

  isLoggedIn$ = this.loggedIn.asObservable();
  currentUser$ = this.currentUser.asObservable();

  private apiUrl: string = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  // Método de login que recibe las credenciales y retorna un objeto con el token
  loginUser(loginData: { identificador: string, contraseña: string }): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, loginData);
  }

  // Decodifica el JWT para extraer el ID del usuario y luego carga el perfil completo
  setUserFromToken(token: string) {
    try {
      // Se decodifica el token y se espera que el claim "sub" contenga el ID del usuario
      const decoded: any = jwtDecode(token);
      const userID = +decoded.sub; // Convertir a número (asegúrate de que el token incluya el id en "sub")

      // Llamamos al endpoint para obtener los datos completos del usuario
      this.loadUserProfile(userID).subscribe({
        next: (user: UsuarioResponse) => {
          this.currentUser.next(user);
          localStorage.setItem('authUser', JSON.stringify(user));
          this.loggedIn.next(true);
        },
        error: (error) => {
          console.error("Error al cargar el perfil del usuario:", error);
        }
      });
    } catch (err) {
      console.error("Error decodificando el token:", err);
    }
  }

  // Carga el perfil completo desde el backend
  loadUserProfile(userID: number): Observable<UsuarioResponse> {
    return this.http.get<UsuarioResponse>(`${this.apiUrl}/${userID}`);
  }

  logout() {
    this.loggedIn.next(false);
    this.currentUser.next(null);
    localStorage.removeItem('authToken');
    localStorage.removeItem('authUser');
  }
}