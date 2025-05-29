import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthExpertService } from '../../services/auth.expert.service';

@Component({
  selector: 'app-expert-comments',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './expert-comments.component.html',
  styleUrls: ['./expert-comments.component.css']
})
export class ExpertCommentsComponent {
    @Input() comment:any;
    @Input() expert:any;
    isExpert = false;
    @Output() editClicked = new EventEmitter<any>();

    constructor(private expertAuthService: AuthExpertService) {}

    ngOnInit() {
        //Comprobamos si un experto ha iniciado sesion
        this.expertAuthService.isLoggedIn$.subscribe((estado) => {
            this.isExpert = estado;
        });
    }

    onEdit() {
        this.editClicked.emit(this.comment);
    }
}
