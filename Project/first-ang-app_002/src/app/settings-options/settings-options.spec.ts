import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsOptions } from './settings-options';

describe('SettingsOptions', () => {
  let component: SettingsOptions;
  let fixture: ComponentFixture<SettingsOptions>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SettingsOptions]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SettingsOptions);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
