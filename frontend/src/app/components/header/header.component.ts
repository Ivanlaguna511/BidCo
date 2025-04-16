import { Component, HostListener, ElementRef } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service'; // Ajusta la ruta a tu servicio

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isDropdownOpen = false;
  isUserMenuOpen = false;
  isExamplesDropdownOpen = false;
  isLoggedIn = false; // Control local del estado de login
  isExpert = false;

  constructor(
    private elRef: ElementRef,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    // Suscríbete para actualizar el estado del login
    this.authService.isLoggedIn$.subscribe((estado: boolean) => {
      this.isLoggedIn = estado;
    });

    // Suscríbete al userRole (asegúrate de tiparlo explícitamente)
    this.authService.userRole$.subscribe((estado: string) => {
      this.isExpert = estado === 'expert';
    });
  }

  toggleDropdown(event: MouseEvent) {
    event.stopPropagation();
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  toggleUserMenu(event: MouseEvent) {
    event.stopPropagation();
    this.isUserMenuOpen = !this.isUserMenuOpen;
  }

  toggleDropdownExamples(event: MouseEvent) {
    event.stopPropagation();
    this.isExamplesDropdownOpen = !this.isExamplesDropdownOpen;
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: MouseEvent) {
    // Cierra ambos menús si se hace clic fuera
    if (!this.elRef.nativeElement.contains(event.target)) {
      this.isDropdownOpen = false;
      this.isUserMenuOpen = false;
      this.isExamplesDropdownOpen = false;
    }
  }
  
  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/auction');
  }

  loginUser() {
    this.authService.setUserRole('user');
  }

  loginExpert() {
    this.authService.setUserRole('expert');
  }
}