import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = 'http://localhost:8080/api/comentarios';

  constructor(private http: HttpClient) {}

  // ✅ Obtener comentarios por subasta con nuevo endpoint
  getComments(auctionId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/subasta/${auctionId}`);
  }

  // Crear comentario nuevo
  createComment(comment: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, comment);
  }

  // Editar comentario existente
  editComment(id: number, newComment: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, newComment);
  }
}
