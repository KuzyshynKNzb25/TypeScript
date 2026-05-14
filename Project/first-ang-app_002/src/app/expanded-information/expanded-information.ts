import { Component, Input, OnChanges,HostListener, SimpleChanges, signal, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { HourlyTemperature, DailyTemperature, chooseWeather, cloudCase } from '../../types';
import { TempSignPipe } from '../pipes/temp-sign-pipe';
import { WindDirectionPipe } from '../pipes/wind-direction-pipe';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-expanded-information',
  imports: [TempSignPipe, WindDirectionPipe, DatePipe],
  templateUrl: './expanded-information.html',
  styleUrl: './expanded-information.css',
  standalone: true
})
export class ExpandedInformation implements OnChanges, AfterViewInit {
  private MOBILE_WIDTH = 570;
  private TABLET_WIDTH = 963;
  @ViewChild('scrollBox') scrollBox!: ElementRef<HTMLElement>;

  @Input() hourWeather: HourlyTemperature[] = [];
  @Input() date: string = "";
  @Input() dailyWeather?: DailyTemperature;
  @Input() dayMode: boolean = false;
  @Input() europeTime: boolean = true;

  cloud = cloudCase;
  cloudIndex = signal<number[]>([]);

  currentDay = 0;

  todayCloudinessIndex = 0;
  hasScroll= signal<boolean>(false) ;
  hourMode = true;


  indexesToDisplay = signal<number[]>([]);


  ngAfterViewInit(): void {
    setTimeout(() => {
    this.checkScroll();
  }, 0);
  }


  ngOnChanges(changes: SimpleChanges) {

    if (changes['hourWeather']) {
      this.hourWeather = changes['hourWeather'].currentValue;
    }
    if (changes['date']) {
      this.date = changes['date'].currentValue;
    }
    if(changes['dailyWeather']) {
      this.dailyWeather = changes['dailyWeather'].currentValue;
    }

    this.hourMode = true;

    console.log("ngOnChanges works");
    
    let i = 0;
    let boxCloundiness: number[] = [];



    while (i < this.hourWeather.length && !this.hourWeather[i].time.startsWith(this.date)) {
      i++;
    }

    
    if (i >= this.hourWeather.length) {
      this.hourMode = false;
      if (this.dailyWeather) {
        this.todayCloudinessIndex = chooseWeather(this.dailyWeather);
      }
    }
    else {
      let boxArrayToDisplay: number[] = [];

      while (i < this.hourWeather.length && this.hourWeather[i].time.startsWith(this.date)) {
        boxCloundiness.push(chooseWeather(this.hourWeather[i]));
        
        boxArrayToDisplay.push(i);
          i++;
      }

    this.indexesToDisplay.set(boxArrayToDisplay);

    }

    this.cloudIndex.set(boxCloundiness);
  }  


  checkScroll() {
    const el = this.scrollBox.nativeElement;
    this.hasScroll.set(el.scrollWidth > el.clientWidth);
  }

}
