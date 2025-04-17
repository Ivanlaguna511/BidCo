import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Privacidad {
  privacidadAnonimoPujas: boolean;
  privacidadEstadisticas: boolean;
  privacidadPerfilVisible: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class PrivacityService {
  private apiUrl = 'http://localhost:8080/api/privacidad';

  constructor(private http: HttpClient) {}

  getPrivacidad(usuarioID: number): Observable<Privacidad> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<Privacidad>(`${this.apiUrl}/${usuarioID}`, { headers });
  }

  updatePrivacidad(usuarioID: number, privacidad: Privacidad): Observable<Privacidad> {
    const token = localStorage.getItem('authToken');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.put<Privacidad>(`${this.apiUrl}/${usuarioID}`, privacidad, { headers });
  }
}