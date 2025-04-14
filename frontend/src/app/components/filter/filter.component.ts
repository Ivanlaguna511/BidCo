import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-filter',
  imports: [FormsModule, CommonModule],
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent {
    minPossible: number = 0;
    maxPossible: number = 1000;
    minPrice: number = 100;  // Valor inicial, ajustable según necesidad
    maxPrice: number = 900;  // Valor inicial, ajustable según necesidad

    categories: string[] = ['Electrónica', 'Ropa', 'Hogar', 'Juguetes'];
    selectedCategories: string[] = [];
    dateOrder: string = '';

    @Output() filterChanged = new EventEmitter<{ 
        minPrice: number, 
        maxPrice: number, 
        categories: string[], 
        dateOrder: string 
    }>();

    updateCategoryFilter(event: any) {
        const category = event.target.value;
        if (event.target.checked) {
            this.selectedCategories.push(category);
        } else {
            this.selectedCategories = this.selectedCategories.filter(c => c !== category);
        }
        this.applyFilter();
    }

    /**
     * Método de validación: Se pasa 'min' o 'max' según cuál valor fue modificado.
     * Si el usuario cambia el mínimo y este supera el máximo, se ajusta el máximo.
     * Si cambia el máximo y este es menor que el mínimo, se ajusta el mínimo.
     */
    applyFilter(changedField?: 'min' | 'max') {
      if (changedField === 'min' && this.minPrice > this.maxPrice) {
         this.maxPrice = this.minPrice;
      } else if (changedField === 'max' && this.maxPrice < this.minPrice) {
         this.minPrice = this.maxPrice;
      }
      
      this.filterChanged.emit({
          minPrice: this.minPrice,
          maxPrice: this.maxPrice,
          categories: this.selectedCategories,
          dateOrder: this.dateOrder
      });
    }
}
