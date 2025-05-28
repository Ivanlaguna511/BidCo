import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UserService } from './user.service';
import { ExpertoResponse, ExpertService } from './expert.service';
import { jwtDecode } from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthExpertService {
  private loggedIn = new BehaviorSubject<boolean>(false);
  public currentUser = new BehaviorSubject<ExpertoResponse | null>(null);
  private userRole = new BehaviorSubject<string>('user'); 

  // Exponemos como observables
  public isLoggedIn$ = this.loggedIn.asObservable();
  public currentUser$ = this.currentUser.asObservable();
  public userRole$ = this.userRole.asObservable();

  private expertApiUrl: string = 'http://localhost:8080/api/trabajadores';

  constructor(private http: HttpClient, private userService: UserService, private expertService: ExpertService) {
    const token = localStorage.getItem('authToken');
    const userData = localStorage.getItem('authUser');

    if (token && userData) {
        this.loggedIn.next(true);
        this.currentUser.next(JSON.parse(userData));

        // Llamar a loadUserProfile para obtener datos actualizados del servidor
        this.loadExpertProfile(this.currentUser.value!.usuarioID).subscribe({
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


  loginExpert(loginData: { identificador: string; contraseña: string }) {
    return this.http.post<{ token: string }>(
      `${this.expertApiUrl}/login`,
      loginData
    );
  }

  setExpertFromToken(token: string): void {
    localStorage.setItem('authToken', token);
    this.loggedIn.next(true);
    // Ahora sí pedimos el perfil
    this.expertService
      .getExpertoById(+(jwtDecode(token).sub ?? 0))
      .subscribe(user => this.setExperto(user), () => this.logout());
  }

  loadExpertProfile(userID: number): Observable<ExpertoResponse> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  
    return this.http.get<ExpertoResponse>(`${this.expertApiUrl}/${userID}`, { headers });
  }

  // Actualiza el estado del usuario (además de guardar en localStorage)
  setExperto(user: ExpertoResponse): void {
    console.log("Actualizando currentUser en AuthService:", user);
    this.currentUser.next(user);
    localStorage.setItem('authUser', JSON.stringify(user));
    this.setUserRole("expert");
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