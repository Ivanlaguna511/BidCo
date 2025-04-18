import { Component, Input, OnInit } from '@angular/core';
import { CommentService } from '../../services/comment.service'; 
import { AuthService, UsuarioResponse } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ExpertCommentsComponent } from '../expert-comments/expert-comments.component';

@Component({
  selector: 'app-expert-comments-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ExpertCommentsComponent],
  templateUrl: './expert-comments-list.component.html',
  styleUrls: ['./expert-comments-list.component.css']
})

export class ExpertCommentsListComponent implements OnInit {
  @Input() subastaId!: number;  

  comentarios: any[] = [];
  usuario: UsuarioResponse | null = null;
  nuevoComentario = { comentario: '', precio_estimado: 0 };
  editandoComentario: any = null;
  comentarioEditado = { comentario: '', precio_estimado: 0 };

  constructor(
    private commentService: CommentService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.currentUser$.subscribe(data => {
      this.usuario = data;
    });
    this.loadComments();
  }

  loadComments(): void {
    this.commentService.getComments(this.subastaId).subscribe(data => {
      this.comentarios = data;
    });
  }

  createComment(): void {
    if (!this.usuario) return;

    const nuevo = {
      ...this.nuevoComentario,
      subasta_id: this.subastaId,
      trabajador_id: this.usuario.usuarioID 
    };

    this.commentService.createComment(nuevo).subscribe(() => {
      this.nuevoComentario = { comentario: '', precio_estimado: 0 };
      this.loadComments();
    });
  }

  onEditClicked(comentario: any): void {
    this.editandoComentario = comentario;
    this.comentarioEditado = {
      comentario: comentario.comentario,
      precio_estimado: comentario.precio_estimado
    };
  }

  saveEdit(): void {
    if (this.editandoComentario) {
      this.commentService
        .editComment(this.editandoComentario.comentario_id, this.comentarioEditado)
        .subscribe(() => {
          this.editandoComentario = null;
          this.loadComments();
        });
    }
  }

  cancelEdit(): void {
    this.editandoComentario = null;
  }
}
