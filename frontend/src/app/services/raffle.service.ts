import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SorteoResponseDto {
    sorteoID: number;
    nombreArticulo: string;
    descripcion: string;
    fechaInicio: string;
    fechaFin: string;
    puntosNecesarios: number;
    creadorId: number; 
}

export interface Filtro {
    minPrice: number, 
    maxPrice: number, 
    categories: string[], 
    dateOrder: string 
}

@Injectable({
    providedIn: 'root'
})
export class SorteoService {
    private apiUrl = 'http://localhost:8080/api/sorteos';

    constructor(private http: HttpClient) {}

    getSorteos(): Observable<SorteoResponseDto[]> {
        return this.http.get<SorteoResponseDto[]>(this.apiUrl);
    }

    getSorteosPorFiltro(filtro: HttpParams): Observable<SorteoResponseDto[]> {
        return this.http.get<SorteoResponseDto[]>(`${this.apiUrl}/filtro`, {params: filtro});
    }
}