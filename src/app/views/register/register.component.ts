import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FooterComponent } from "../../components/footer/footer.component";
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, FooterComponent, HeaderComponent, FooterComponent],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {}
