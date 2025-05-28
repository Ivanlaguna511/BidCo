import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common'; 

import { FooterComponent } from "../../components/footer/footer.component";
import { HeaderComponent } from "../../components/header/header.component";
import { AuthExpertService } from '../../services/auth.expert.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    RouterLink, 
    RouterOutlet, 
    FooterComponent, 
    HeaderComponent, 
    CommonModule
],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent {
    isExpert = false;
    
    constructor(private expertAuthService: AuthExpertService) {}

    ngOnInit() {
        //Comprobamos si un experto ha iniciado sesion
        this.expertAuthService.isLoggedIn$.subscribe((estado) => {
            this.isExpert = estado;
        });
    }
}
