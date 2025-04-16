import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';

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

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);
  private currentUser = new BehaviorSubject<UsuarioResponse | null>(null);
  private userRole = new BehaviorSubject<string>('user'); 

  // Exponemos como observables
  public isLoggedIn$ = this.loggedIn.asObservable();
  public currentUser$ = this.currentUser.asObservable();
  public userRole$ = this.userRole.asObservable();

  private apiUrl: string = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  // Método para hacer login; se espera que el backend retorne { token: string }
  loginUser(loginData: { identificador: string, contraseña: string }): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, loginData);
  }

  // Decodifica el token y carga el perfil completo del usuario
  setUserFromToken(token: string): void {
    try {
      const decoded: any = jwtDecode(token);
      const userID = +decoded.sub;
      console.log('Token decodificado, userID:', userID); // Debug
      this.loadUserProfile(userID).subscribe({
        next: (user: UsuarioResponse) => {
          console.log('Perfil cargado:', user); // Debug
          this.setUser(user);
          localStorage.setItem('authToken', token);
          localStorage.setItem('authUser', JSON.stringify(user));
          this.loggedIn.next(true);
        },
        error: (error) => {
          console.error("Error al cargar el perfil:", error);
        }
      });
    } catch (e) {
      console.error("Error decodificando el token:", e);
    }
  }
  

  // Llama al endpoint GET para obtener todos los datos del usuario
  loadUserProfile(userID: number): Observable<UsuarioResponse> {
    return this.http.get<UsuarioResponse>(`${this.apiUrl}/${userID}`);
  }

  // Actualiza el estado del usuario (además de guardar en localStorage)
  setUser(user: UsuarioResponse): void {
    console.log("Actualizando currentUser en AuthService:", user);
    this.currentUser.next(user);
    localStorage.setItem('authUser', JSON.stringify(user));
  }

  // Para actualizar el rol (por ejemplo, "user" o "expert")
  setUserRole(role: string): void {
    this.userRole.next(role);
  }

  logout(): void {
    this.loggedIn.next(false);
    this.currentUser.next(null);
    localStorage.removeItem('authToken');
    localStorage.removeItem('authUser');
  }
}