import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SubastaResponseDTO {
    subastaID: number;
    nombreArticulo: string;
    precioFinal: string;
    categoria: string;
}

@Injectable({
    providedIn: 'root'
})
export class SubastaService {
    private apiUrl = 'http://localhost:8080/api/subastas';

    constructor(private http: HttpClient) {}

    getSubastasPorTipo(normal: boolean): Observable<SubastaResponseDTO[]> {
        return this.http.get<SubastaResponseDTO[]>(`${this.apiUrl}/filtrar?normal=${normal}`);
    }

    getSubastasPorCreador(id: number): Observable<SubastaResponseDTO[]> {
        return this.http.get<SubastaResponseDTO[]>(`${this.apiUrl}/filtrar-creador`, {params: { id: id }});
    }    
}
