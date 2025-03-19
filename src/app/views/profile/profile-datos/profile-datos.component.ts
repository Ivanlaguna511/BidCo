import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 

import { DATA_USER, DATA_EXPERT } from '../../../datos_estaticos/user_estadisticas';
import { AuthService } from '../../../auth.service';

@Component({
  selector: 'app-profile-datos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile-datos.component.html',
  styleUrl: './profile-datos.component.css'
})
export class ProfileDatosComponent {
    user = DATA_USER;
    expert = DATA_EXPERT;
    isExpert = false;

    constructor(private authService: AuthService) {}

    ngOnInit() {
        this.authService.userRole$.subscribe((estado) => {
            this.isExpert = estado === 'expert';
        });
    }
}
