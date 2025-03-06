import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-product-logged',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './product-logged.component.html',
  styleUrls: ['./product-logged.component.css']
})
export class ProductLoggedComponent {}
