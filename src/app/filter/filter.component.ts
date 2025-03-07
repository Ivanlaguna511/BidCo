import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-filter',
  imports: [FormsModule],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {
    minPrice: number | null = null;
    maxPrice: number | null = null;

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
