import { Component, HostListener, ElementRef } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { AuthService } from '../../auth.service'; // Ajusta la ruta a tu servicio

@Component({
  selector: 'app-header',
  standalone: true, // <- Si tu componente es standalone
  imports: [
    CommonModule, // <--- Esto habilita *ngIf, *ngFor, etc.
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isDropdownOpen = false;
  isUserMenuOpen = false;
  isLoggedIn = false; // Control local del estado de login

  constructor(
    private elRef: ElementRef,
    private el2Ref: ElementRef,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    // Opción 1: Suscribirte al observable
    this.authService.isLoggedIn$.subscribe((estado) => {
      this.isLoggedIn = estado;
    });

    // Opción 2 (en vez de la suscripción):
    // this.isLoggedIn = this.authService.isLoggedIn();
  }

  toggleDropdown(event: MouseEvent) {
    event.stopPropagation();
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  toggleUserMenu(event: MouseEvent) {
    event.stopPropagation();
    this.isUserMenuOpen = !this.isUserMenuOpen;
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: MouseEvent) {
    // Cierra ambos menús si se hace clic fuera
    if (!this.elRef.nativeElement.contains(event.target)) {
      this.isDropdownOpen = false;
      this.isUserMenuOpen = false;
    }
  }
  
  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/auction');
  }
}
