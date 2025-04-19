// src/app/services/admin.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface AdminLoginDTO {
  username: string;
  password: string;
}

export interface SorteoDTO {
  nombreArticulo:     string;
  descripcion:        string;
  fechaInicio:        string;         // Formato "YYYY-MM-DD"
  fechaFin:           string;         // Formato "YYYY-MM-DD"
  puntosNecesarios:   number;
  imagen:             File | null;    // Para adjuntar la imagen en FormData
}

export interface ExpertDTO {
  nombreUsuario:     string;
  correoElectronico: string;
  contrasena:        string;
}

@Injectable({ providedIn: 'root' })
export class AdminService {
  private api = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) {}

  loginAdmin(dto: AdminLoginDTO): Observable<void> {
    return this.http.post<void>(
      `${this.api}/login`,
      dto,
      { withCredentials: true }
    ).pipe(
      catchError(err => throwError(() => new Error(err.error?.message || 'Login admin fallido')))
    );
  }

  createSorteo(data: FormData): Observable<void> {
    return this.http.post<void>(
      `${this.api}/sorteos`,
      data,
      { withCredentials: true }
    ).pipe(
      catchError(err => throwError(() => new Error(err.error?.message || 'Error creando sorteo')))
    );
  }

  createExpert(dto: ExpertDTO): Observable<void> {
    return this.http.post<void>(
      `${this.api}/expertos`,
      dto,
      { withCredentials: true }
    ).pipe(
      catchError(err => throwError(() => new Error(err.error?.message || 'Error registrando experto')))
    );
  }
}