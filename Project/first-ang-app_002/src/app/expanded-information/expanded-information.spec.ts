import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpandedInformation } from './expanded-information';

describe('ExpandedInformation', () => {
  let component: ExpandedInformation;
  let fixture: ComponentFixture<ExpandedInformation>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpandedInformation]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpandedInformation);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
