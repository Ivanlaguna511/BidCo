import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

@Injectable({
    providedIn: 'root'
})
export class SorteoService {
    private apiUrl = 'http://localhost:8080/api/sorteos';

    constructor(private http: HttpClient) {}

    getSorteos(): Observable<SorteoResponseDto[]> {
        return this.http.get<SorteoResponseDto[]>(this.apiUrl);
      }
}