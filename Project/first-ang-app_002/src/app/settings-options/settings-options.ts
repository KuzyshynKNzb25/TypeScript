import { Component, Input, Output, EventEmitter, signal, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-settings-options',
  imports: [DatePipe],
  templateUrl: './settings-options.html',
  styleUrl: './settings-options.css',
  standalone: true,
})
export class SettingsOptions implements OnInit{
  @Output() farenheitChoice = new EventEmitter<void>();
  @Output() celciumChoice = new EventEmitter<void>();
  @Output() kilometerChoice = new EventEmitter<void>();
  @Output() meterPerSecond = new EventEmitter<void>();
  @Output() amTime = new EventEmitter<void>();
  @Output() europeTime = new EventEmitter<void>();
  
  @Input() temperature: boolean = false;
  @Input() europeTimeValue: boolean = false;
  @Input() meterPerSecondValue: boolean = false;

  
  currentTime = signal(new Date());

  constructor() {
    setInterval(() => {
      this.currentTime.set(new Date());
    }, 60000);
  }

  ngOnInit(): void {
    // const value = document.getElementById("select-time-format") as HTMLSelectElement;
    // console.log(this.europeTimeValue);
    // if (this.europeTimeValue) {
    //   value.value = "europe-time";
    // }
    // else {
    //   value.value = "am-time"  
    // }
  }

  makeAmChoice = () => {
    this.amTime.emit();
  }

  makeEuropeTimeChoice = () => {
    this.europeTime.emit();
  }

  makeFarenheitChoice = () => {
    this.farenheitChoice.emit();
  }

  makeCelciumChoice = () => {
    this.celciumChoice.emit();
  }

  makeKilometerChoice = () => {
    this.kilometerChoice.emit();
  }

  makeMeterChoice = () => {
    this.meterPerSecond.emit();
  }
}
