import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthService } from '../../auth.service';

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

    constructor(private authService: AuthService) {}

    ngOnInit() {
        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        });
    }

    onEdit() {
        this.editClicked.emit(this.comment);
    }
}
