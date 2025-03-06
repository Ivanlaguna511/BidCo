import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home-logged',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './home-logged.component.html',
  styleUrls: ['./home-logged.component.css']
})
export class HomeLoggedComponent {}
