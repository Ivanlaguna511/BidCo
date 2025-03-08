import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpertCommentsComponent } from './expert-comments.component';

describe('ExpertCommentsComponent', () => {
  let component: ExpertCommentsComponent;
  let fixture: ComponentFixture<ExpertCommentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpertCommentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpertCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
