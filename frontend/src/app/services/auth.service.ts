import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UserService } from './user.service';
import { ExpertoResponse, ExpertService } from './expert.service';
import { jwtDecode } from 'jwt-decode';
import { environment } from '../../environments/environment';

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
  public currentUser = new BehaviorSubject<UsuarioResponse | null>(null);
  private userRole = new BehaviorSubject<string | null>(null); 

  // Exponemos como observables
  public isLoggedIn$ = this.loggedIn.asObservable();
  public currentUser$ = this.currentUser.asObservable();
  public userRole$ = this.userRole.asObservable();

  private userApiUrl: string = `${environment.apiUrl}/usuarios`;

  constructor(private http: HttpClient, private userService: UserService, private expertService: ExpertService) {
    const token = localStorage.getItem('authToken');
    const userData = localStorage.getItem('authUser');

    if (token && userData) {
        this.loggedIn.next(true);
        this.currentUser.next(JSON.parse(userData));

        // Llamar a loadUserProfile para obtener datos actualizados del servidor
        this.loadUserProfile(this.currentUser.value!.usuarioID).subscribe({
            next: (updatedUser) => {
                // Actualizamos la información del usuario en localStorage
                localStorage.setItem('authUser', JSON.stringify(updatedUser));
                this.currentUser.next(updatedUser);
            },
            error: (err) => {
                console.error('Error al cargar el perfil del usuario:', err);
            }
        });
    }
  }

  // Método para hacer login; se espera que el backend retorne { token: string }
  loginUser(loginData: { identificador: string; contraseña: string }) {
    return this.http.post<{ token: string }>(
      `${this.userApiUrl}/login`,
      loginData
    );
  }

  // Decodifica el token y carga el perfil completo del usuario
  setUserFromToken(token: string): void {
    localStorage.setItem('authToken', token);
    this.loggedIn.next(true);
    // Ahora sí pedimos el perfil
    this.userService
      .getUserById(+(jwtDecode(token).sub ?? 0))
      .subscribe(user => this.setUser(user), () => this.logout());
  }
  

  // Llama al endpoint GET para obtener todos los datos del usuario
  loadUserProfile(userID: number): Observable<UsuarioResponse> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  
    return this.http.get<UsuarioResponse>(`${this.userApiUrl}/${userID}`, { headers });
  }

  // Actualiza el estado del usuario (además de guardar en localStorage)
  setUser(user: UsuarioResponse): void {
    console.log("Actualizando currentUser en AuthService:", user);
    this.currentUser.next(user);
    localStorage.setItem('authUser', JSON.stringify(user));
    this.setUserRole("user");
  }


  // Para actualizar el rol (por ejemplo, "user" o "expert")
  setUserRole(role: string | null): void {
    this.userRole.next(role);
  }

  logout(): void {
    this.loggedIn.next(false);
    this.currentUser.next(null);
    this.setUserRole(null);
    localStorage.removeItem('authToken');
    localStorage.removeItem('authUser');
  }
}