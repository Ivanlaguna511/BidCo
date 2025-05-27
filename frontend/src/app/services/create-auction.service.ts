import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SubastaCreateDTO {
  fechaInicial: string;
  fechaFinal: string;
  precioInicial: number;
  subastaNormal: boolean;
  nombreArticulo: string;
  descripcion: string;
  creadorId: number;
  categoria: string;
}

export interface SubastaResponseDTO {
  subastaID: number;
  nombreArticulo: string;
  precioFinal: string;
  imagenUrl?: string;
  fechaFinal?: string;
  descripcion?: string;
  creadorId?: number;
  subastaNormal?: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class CreateSubastaService {
  private apiUrl = 'http://localhost:8080/api/subastas';

  constructor(private http: HttpClient) {}

  crearSubasta(data: FormData): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }
}
