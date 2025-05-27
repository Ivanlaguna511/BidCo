import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSorteoComponent } from './admin-sorteo.component';

describe('AdminSorteoComponent', () => {
  let component: AdminSorteoComponent;
  let fixture: ComponentFixture<AdminSorteoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminSorteoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSorteoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
