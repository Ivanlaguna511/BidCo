import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAuctionComponent } from './create-auction.component';

describe('CreateAuctionComponent', () => {
  let component: CreateAuctionComponent;
  let fixture: ComponentFixture<CreateAuctionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateAuctionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateAuctionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
