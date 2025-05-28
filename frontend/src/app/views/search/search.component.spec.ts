import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuctionComponent } from './search.component';

describe('AuctionComponent', () => {
  let component: AuctionComponent;
  let fixture: ComponentFixture<AuctionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuctionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuctionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
