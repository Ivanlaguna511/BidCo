import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthExpertService } from '../../services/auth.expert.service';
import { ExpertService } from '../../services/expert.service';

@Component({
  selector: 'app-expert-comments',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './expert-comments.component.html',
  styleUrls: ['./expert-comments.component.css']
})
export class ExpertCommentsComponent {
    @Input() comment:any;
    expert: any;
    isExpert = false;
    @Output() editClicked = new EventEmitter<any>();

    constructor(private expertAuthService: AuthExpertService, private expertService: ExpertService) {}

    ngOnInit() {
        //Comprobamos si un experto ha iniciado sesion
        this.expertAuthService.isLoggedIn$.subscribe((estado) => {
            this.isExpert = estado;
        });

        this.expertService.getExpertoById(this.comment.trabajadorID).subscribe({
            next: (data) => {
                this.expert = data
            }
        })
    }

    onEdit() {
        this.editClicked.emit(this.comment);
    }
}
