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
        minPrice: number | null, 
        maxPrice: number | null, 
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

    applyFilter() {
        this.filterChanged.emit({
          minPrice: this.minPrice,
          maxPrice: this.maxPrice,
          categories: this.selectedCategories,
          dateOrder: this.dateOrder
        });
    }
}
