import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductLoggedComponent } from './product-logged.component';

describe('ProductLoggedComponent', () => {
  let component: ProductLoggedComponent;
  let fixture: ComponentFixture<ProductLoggedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductLoggedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductLoggedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
