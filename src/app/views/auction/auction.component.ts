import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { HeaderComponent } from '../../components/header/header.component';
import { FilterComponent } from '../../components/filter/filter.component';
import { ProductItemComponent } from '../../components/product-item/product-item.component';
import { FooterComponent } from "../../components/footer/footer.component";

@Component({
  selector: 'app-auction',
  standalone: true,
  imports: [
    RouterLink,
    HeaderComponent,
    FilterComponent,
    ProductItemComponent,
    FooterComponent
],
  templateUrl: './auction.component.html',
  styleUrl: './auction.component.css'
})

export class AuctionComponent {
}