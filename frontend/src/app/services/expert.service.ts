import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable } from 'rxjs';

export interface ExpertoResponse {
  usuarioID: number;
  nombreUsuario: string;
  correoElectronico: string;
  contraseña?: string;
}

export interface ExpertoCreate {
  nombreUsuario: string;
  correoElectronico: string;
  contraseña: string;
}


@Injectable({
  providedIn: 'root'
})
export class ExpertService {
  private apiUrl: string = 'http://localhost:8080/api/trabajadores';

  constructor(private http: HttpClient) {}

  // Obtiene los datos del usuario por ID (GET)
  getExpertoById(id: number): Observable<ExpertoResponse> {
    return this.http.get<ExpertoResponse>(`${this.apiUrl}/${id}`);
  }
}