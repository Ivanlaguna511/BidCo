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
  categoria:          string;
}

export interface ExpertDTO {
  nombreUsuario:     string;
  correoElectronico: string;
  contrasena:        string;
  experto:           boolean;
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

  createSorteo(sorteo: any, imagen: File | null) {
    const formData = new FormData();
    formData.append('sorteo', new Blob([JSON.stringify({
      nombreArticulo: sorteo.nombreArticulo,
      descripcion: sorteo.descripcion,
      fechaInicio: sorteo.fechaInicio,
      fechaFin: sorteo.fechaFin,
      puntosNecesarios: sorteo.puntosNecesarios,
    })], { type: 'application/json' }));
  
    if (imagen) {
      formData.append('imagen', imagen, imagen.name);
    }

    return this.http.post<void>(`${this.api}/sorteos`, formData, { withCredentials: true });
  }
  

  createExpert(dto: ExpertDTO): Observable<void> {
    return this.http.post<void>(
      `/api/trabajadores`,
      dto,
      { withCredentials: true }
    ).pipe(
      catchError(err => throwError(() => new Error(err.error?.message || 'Error registrando experto')))
    );
  }
}