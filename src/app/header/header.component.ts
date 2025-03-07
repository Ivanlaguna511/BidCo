import { Component, HostListener, ElementRef } from '@angular/core';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-header',
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})

export class HeaderComponent {
    isDropdownOpen = false;

    constructor(private elRef: ElementRef) {}

    toggleDropdown(event: MouseEvent) {
        event.stopPropagation(); // Evita que el clic dentro del menú cierre el menú
        this.isDropdownOpen = !this.isDropdownOpen; // Alterna entre mostrar y ocultar
    }

    @HostListener('document:click', ['$event'])
    onClickOutside(event: MouseEvent) {
        if (this.isDropdownOpen && !this.elRef.nativeElement.contains(event.target)) {
        this.isDropdownOpen = false; // Cierra el menú si el clic es fuera del contenedor
    }
  }
}
