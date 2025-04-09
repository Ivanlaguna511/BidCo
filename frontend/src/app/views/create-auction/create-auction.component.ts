import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from "../../components/footer/footer.component";

@Component({
  selector: 'app-create-auction',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent],
  templateUrl: './create-auction.component.html',
  styleUrls: ['./create-auction.component.css']
})
export class CreateAuctionComponent {}
