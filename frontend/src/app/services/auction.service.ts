import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SubastaResponseDTO {
    subastaID: number;
    nombreArticulo: string;
    precioFinal: string;
    categoria: string;
}

export interface Filtro {
    minPrice: number, 
    maxPrice: number, 
    categories: string[]|null, 
    dateOrder: string 
}

@Injectable({
    providedIn: 'root'
})
export class SubastaService {
    private subastasUrl = 'http://localhost:8080/api/subastas';
    private pujasSubastasUrl = 'http://localhost:8080/api/pujas';

    constructor(private http: HttpClient) {}

    getSubastasPorTipo(normal: boolean): Observable<SubastaResponseDTO[]> {
        return this.http.get<SubastaResponseDTO[]>(`${this.subastasUrl}/filtrar?normal=${normal}`);
    }

    getSubastasPorCreador(id: number): Observable<SubastaResponseDTO[]> {
        return this.http.get<SubastaResponseDTO[]>(`${this.subastasUrl}/filtrar-creador`, {params: { id: id }});
    }   

    getSubastasPujasUsuaroId(id: number): Observable<SubastaResponseDTO[]> {
        return this.http.get<SubastaResponseDTO[]>(`${this.pujasSubastasUrl}/subastas-usuario/${id}`)
    }

    getSubastasFiltradas(filtro: Filtro): Observable<SubastaResponseDTO[]> {
        return this.http.get<SubastaResponseDTO[]>(`${this.subastasUrl}/filtro-normal`)
    }
}
