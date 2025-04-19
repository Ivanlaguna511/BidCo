// admin-dashboard.component.ts
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { HeaderComponent } from "../../../components/header/header.component";
import { FooterComponent } from "../../../components/footer/footer.component";

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css',
  standalone: true,
  imports: [HeaderComponent, FooterComponent, RouterModule],
})
export class AdminDashboardComponent {
  constructor(private router: Router) {}

  goToSorteo() {
    this.router.navigateByUrl('/admin/sorteo');
  }
  goToExpertos() {
    this.router.navigateByUrl('/admin/expertos');
  }
}
