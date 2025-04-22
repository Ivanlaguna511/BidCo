import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SubastaResponseDto {
    subastaID: number;
    nombreArticulo: string;
    descripcion: string;
    fechaInicial: string;
    fechaFinal: string;
    precioInicial: number;
    precioFinal: number;
    imagen: string;
    creadorId: number;
    comentarios: any[];
    pujas: any[];
    privacidad_anonimo: boolean;
}


export interface SorteoResponseDto {
    sorteoID: number;
    nombreArticulo: string;
    descripcion: string;
    fechaInicio: string;
    fechaFin: string;
    puntosNecesarios: number;
    imagen: string;
}

export interface UsuarioResponseDto {
    usuarioID: number;
    nombreUsuario: string;
}

export interface PujaDTO {
    importe: number;
    fecha: string;
    subastaID: number;
    pujadorID: number;
  }

@Injectable({
    providedIn: 'root'
})
export class ProductoService {
    private subastaUrl = 'http://localhost:8080/api/subastas';
    private pujaSubastaUrl = 'http://localhost:8080/api/pujas';
    private sorteoUrl = 'http://localhost:8080/api/sorteos';
    private usuarioUrl = 'http://localhost:8080/api/usuarios';

    constructor(private http: HttpClient) {}

    getSubastaPorId(id: number): Observable<any> {
        return this.http.get(`${this.subastaUrl}/${id}`);
    }

    getSorteoPorId(id: number): Observable<any> {
        return this.http.get(`${this.sorteoUrl}/${id}`);
    }

    getUsuarioPorId(id: number): Observable<any> {
        return this.http.get(`${this.usuarioUrl}/${id}`);
    }

    crearPuja(puja: PujaDTO): Observable<PujaDTO> {
        return this.http.post<PujaDTO>(this.pujaSubastaUrl, puja);
    }
}
