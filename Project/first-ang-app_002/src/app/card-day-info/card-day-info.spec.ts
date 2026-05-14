import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardDayInfo } from './card-day-info';

describe('CardDayInfo', () => {
  let component: CardDayInfo;
  let fixture: ComponentFixture<CardDayInfo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardDayInfo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardDayInfo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
