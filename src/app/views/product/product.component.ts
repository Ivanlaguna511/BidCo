import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';
import { ProductDetailsComponent } from '../../components/product-details/product-details.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    RouterLink,
    HeaderComponent,
    ProductDetailsComponent],
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {}
