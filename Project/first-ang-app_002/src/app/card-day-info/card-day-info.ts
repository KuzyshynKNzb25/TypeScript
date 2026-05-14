import { Component, OnInit, HostListener, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WeatherRequest, City, chooseWeather, DailyTemperature,  cloudCase, putCorrectPop} from '../../types';
import { DatePipe } from '@angular/common';
import { ExpandedInformation } from '../expanded-information/expanded-information';
import { SettingsOptions } from '../settings-options/settings-options';
import { response } from 'express';

@Component({
  selector: 'app-card-day-info',
  imports: [DatePipe, ExpandedInformation, SettingsOptions],
  templateUrl: './card-day-info.html',
  styleUrl: './card-day-info.css',
  standalone: true
})
  
  
export class CardDayInfo implements OnInit {
  private CARD_WIDTH = 133;
  private MOBILE_WIDTH = 570;
  private TABLET_WIDTH = 963;
  
  days: string[] = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
  shortDays: string[] = ["SUN", "MON", "TUE", "WED", "THU", "FR", "SAT"];
  cloud = cloudCase;
  buttonChoose: string[] = ["clear.png", "almost_clear.png", "cloud.png", "full_cloud.png"];
  cloudIndex = signal<number[]>([]);
  daysToDisplay = signal<DailyTemperature[]>([]);

  cityName = 'Kyiv';

  private BASE_URL: string = 'http://localhost:8085';
  private http = inject(HttpClient)

  celciumTemperature: boolean = true;
  private farenheitTempurature: boolean = false;
  meterPerSecond: boolean = true;
  private kilometerPerHour: boolean = false;
  europeTime = signal<boolean>(true);


  private today = new Date(); // Поточна дата
  dayIndex = this.today.getDay();

  expandedInformation: boolean = false;
  dayIndexToExpand: number = 0;

  data = signal<WeatherRequest|undefined>(undefined)
  currentDay: string = "Monday";
  left = signal<number>(0)
  isFocused = signal<boolean>(false);
  searchResults = signal<City[]>([]);

  selectMode = false;
  settingsMode = signal<boolean>(false)

  private arrayNum = 7;
  maxWidth = signal<number>(900);

  @HostListener('window:resize')
  onResize() {
    this.checkWindowWidth();    
  }

  @HostListener('document:click', ['$event'])
  onClick(event: MouseEvent) {
    if(this.settingsMode()){
      const target = event.target as HTMLElement;

      const clickInside = target.closest('.settings-text') || 
                          target.closest('.lucide-settings') || 
        target.closest('.settings-form');
      
      if (!clickInside) {
        this.showSettings();
      }
    }
  }


  ngOnInit(): void {
    this.fetchData();
  }

  private celsiumToFarenheitCount = (temperature: number):number => {
    return Math.round(temperature * 1.8 + 32);
  }

  private farenheitToCelcium = (temperature: number):number => {
        return Math.round((temperature - 32) / 1.8);
  }

  counter() {
    return new Array(this.arrayNum);
  }


  private changeDayCloudiness = (day: DailyTemperature[]) => {
    let box: DailyTemperature[] = [];
    let boxCloundiness: number[] = [];
    for (let item of day) {
      boxCloundiness.push(
        chooseWeather(item)
      );  
      putCorrectPop(item);
    }

    this.cloudIndex.set(boxCloundiness);
  }


  private changeDayWeatherInfo = (weather: WeatherRequest) => {
    let min, max, cloudiness, numberOfItems: number;

    for (let i = 0, j = 0; i < 5; i++){
      min = 5000;
      max = -273;
      cloudiness = 0;
      numberOfItems = 0;

      for (;
        j < weather.hourlyWeather.length &&
        weather.hourlyWeather[j].time.substring(0, 10) ===
        weather.dailyTemperature[i].date;
        j++, numberOfItems++
      ){
        putCorrectPop(weather.hourlyWeather[j]);
        weather.hourlyWeather[j].digitTime = new Date(
          weather.hourlyWeather[j].time
        );

        if (weather.hourlyWeather[j].temperature < min) {
          min = weather.hourlyWeather[j].temperature;
        }
        if (weather.hourlyWeather[j].temperature > max) {
          max = weather.hourlyWeather[j].temperature;
        }
        cloudiness += weather.hourlyWeather[j].cloudiness;
      }

      if (weather.dailyTemperature[i].minTemperature > min) {
        weather.dailyTemperature[i].minTemperature = min;
      }
      if (weather.dailyTemperature[i].maxTemperature < max) {
        weather.dailyTemperature[i].maxTemperature = max;
      }

      weather.dailyTemperature[i].cloudiness = cloudiness / numberOfItems;
      weather.dailyTemperature[i].pop = 
                    Math.round(weather.dailyTemperature[i].pop * 100);

    }
  }


  private checkWidth = () => {
    if (window.innerWidth < this.TABLET_WIDTH) {
      this.maxWidth.set(0.9*window.innerWidth)
    }
  }


  private checkWindowWidth = () => {
    if (window.innerWidth < this.MOBILE_WIDTH) {
      this.arrayNum = 6;
    }
    else if (window.innerWidth < this.TABLET_WIDTH) {
      this.arrayNum = 8;
    }
    else {
      this.arrayNum = 7;
    }

    this.leftForExpandedInfo();   
    this.checkWidth();
  }


  displayExpandedInformation(indexDay: number) {
    if (this.dayIndexToExpand == indexDay) {
      this.expandedInformation = false;
      this.dayIndexToExpand = -1;
    }
    else {
      this.dayIndexToExpand = indexDay;
      this.expandedInformation = true;
    }

    this.leftForExpandedInfo();
  }


  private leftForExpandedInfo = () => {
    if (this.dayIndexToExpand > 4 && (
      window.innerWidth < this.MOBILE_WIDTH ||
      window.innerWidth > this.TABLET_WIDTH
    ) || this.dayIndexToExpand > 5) {
      this.left.set(-this.CARD_WIDTH*1.9);
    }
    else {
      if (window.innerWidth < this.MOBILE_WIDTH) {
        this.left.set(
           -this.CARD_WIDTH * (this.dayIndexToExpand % 3)
        );
      }
      else if (window.innerWidth < this.TABLET_WIDTH) {
        this.left.set(
          -this.CARD_WIDTH * (this.dayIndexToExpand % 4)
        );
      }
      else {
        this.left.set(
          -124 * this.dayIndexToExpand
        );
      }
    }
  }


  onAmTimeChoice = () => {
    if (this.europeTime()) {
      this.europeTime.set(false);
    }
  }


  onCelciumChoice = () => {
    if (!this.celciumTemperature) {
      this.celciumTemperature = true;
      this.farenheitTempurature = false;

      if (this.data() !== null && this.data() !== undefined) {
        let temp = this.data();
        if (this.data !== null) {

        if(temp?.dailyTemperature)
          for (let i = 0; i < temp.dailyTemperature.length || 0; i++) {
            temp.dailyTemperature[i].dayTemperature =
              this.farenheitToCelcium(temp.dailyTemperature[i].dayTemperature);
            temp.dailyTemperature[i].eveningTemperature =
              this.farenheitToCelcium(temp.dailyTemperature[i].eveningTemperature);
            temp.dailyTemperature[i].feelsLikeDay =
              this.farenheitToCelcium(temp.dailyTemperature[i].feelsLikeDay);
            temp.dailyTemperature[i].feelsLikeEvening =
              this.farenheitToCelcium(temp.dailyTemperature[i].feelsLikeEvening);
            temp.dailyTemperature[i].feelsLikeMorning =
              this.farenheitToCelcium(temp.dailyTemperature[i].feelsLikeMorning);
            temp.dailyTemperature[i].feelsLikeNight =
              this.farenheitToCelcium(temp.dailyTemperature[i].feelsLikeNight);
            temp.dailyTemperature[i].maxTemperature =
              this.farenheitToCelcium(temp.dailyTemperature[i].maxTemperature);
            temp.dailyTemperature[i].minTemperature =
              this.farenheitToCelcium(temp.dailyTemperature[i].minTemperature);
            temp.dailyTemperature[i].morningTemperature =
              this.farenheitToCelcium(temp.dailyTemperature[i].morningTemperature);
            temp.dailyTemperature[i].nightTemperature =
              this.farenheitToCelcium(temp.dailyTemperature[i].nightTemperature);
          }
      }
      if (temp?.hourlyWeather) {
        for (let i = 0; i < temp.hourlyWeather.length; i++){
          temp.hourlyWeather[i].feelsLike = this.farenheitToCelcium(temp.hourlyWeather[i].feelsLike);
          temp.hourlyWeather[i].temperature = this.farenheitToCelcium(temp.hourlyWeather[i].temperature);

        }
      }
        
        this.data.set(temp);
      }
    }
  }


  onEuropeTimeChoice = () => {
    if (!this.europeTime()) {
      this.europeTime.set(true);
    }
  }


  onFarenheitChoise = () => {
    if (!this.farenheitTempurature) {
      this.farenheitTempurature = true;
      this.celciumTemperature = false;

      if (this.data() !== undefined) {
        let temp = this.data();
        if(temp?.dailyTemperature)
          for (let i = 0; i < temp.dailyTemperature.length; i++) {
            temp.dailyTemperature[i].dayTemperature =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].dayTemperature);
            temp.dailyTemperature[i].eveningTemperature =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].eveningTemperature);
            temp.dailyTemperature[i].feelsLikeDay =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].feelsLikeDay);
            temp.dailyTemperature[i].feelsLikeEvening =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].feelsLikeEvening);
            temp.dailyTemperature[i].feelsLikeMorning =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].feelsLikeMorning);
            temp.dailyTemperature[i].feelsLikeNight =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].feelsLikeNight);
            temp.dailyTemperature[i].maxTemperature =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].maxTemperature);
            temp.dailyTemperature[i].minTemperature =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].minTemperature);
            temp.dailyTemperature[i].morningTemperature =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].morningTemperature);
            temp.dailyTemperature[i].nightTemperature =
              this.celsiumToFarenheitCount(temp.dailyTemperature[i].nightTemperature);
          }
        
        if (temp?.hourlyWeather) {
        for (let i = 0; i < temp.hourlyWeather.length; i++){
          temp.hourlyWeather[i].feelsLike = this.celsiumToFarenheitCount(temp.hourlyWeather[i].feelsLike);
          temp.hourlyWeather[i].temperature = this.celsiumToFarenheitCount(temp.hourlyWeather[i].temperature);

        }
        }
        
        this.data.set(temp);
      }
      
    }
  }


  onKilometerPerHourChoice = () => {
    if (!this.kilometerPerHour) {
      this.kilometerPerHour = true;
      this.meterPerSecond = false;

      let temp = this.data();

      if (temp?.hourlyWeather) {
        for (let i = 0; i < temp.hourlyWeather.length; i++) {
          temp.hourlyWeather[i].windSpeed = Math.round(100 * temp.hourlyWeather[i].windSpeed * 3.6)/100;
        }
      }

      this.data.set(temp);
    }
  }


  onMeterPerSecond = () => {
    if (!this.meterPerSecond) {
      this.meterPerSecond = true;
      this.kilometerPerHour = false;

      let temp = this.data();

      if (temp?.hourlyWeather) {
        for (let i = 0; i < temp.hourlyWeather.length; i++) {
          temp.hourlyWeather[i].windSpeed = Math.round(temp.hourlyWeather[i].windSpeed / 3.6 * 100)/100 ;
        }
      }
    }
  }


  onSearchByCityItem = (item: City, value: HTMLInputElement) => {
    this.cityName = item.cityName;

    if (item.city.state) {
      this.cityName += ', ' + item.city.state;
    }
    if (item.countryName) {
      this.cityName += ', ' + item.countryName;
    }

    this.http.post<WeatherRequest>(this.BASE_URL + "/home/find-by-item", item)
      .subscribe(response => {
        this.reactOnResponse(response);
      })
    
    this.selectMode = false;
    value.value = '';

  }


  onSearchByCityName = () => {
    this.searchResults.set([]);
  }


  onType(value: string) {
    console.log("OnType works")

    if (value.length > 2) {
      this.selectMode = true;

      this.http.get<City[]>(this.BASE_URL + "/home/offer-city-by-name/" + value)
        .subscribe(response => {
          if (response && response.length > 0) {
            this.searchResults.set(response);
        }
        })
    }
    else {
      this.searchResults.set([]);
    }
  }


  onUsTimeChoice = () => {
    if (this.europeTime()) {
      this.europeTime.set(false);
    }
  }


  private reactOnResponse = (response: WeatherRequest) => {
    if (window.innerWidth > 963) {
      this.expandedInformation = true;
      this.dayIndexToExpand = 0;
    }

    this.checkWindowWidth();

    this.today = new Date();
    this.dayIndex = this.today.getDay();
    this.currentDay = this.days[this.dayIndex];

    this.changeDayWeatherInfo(response);

    this.changeDayCloudiness(response.dailyTemperature);

    this.setCurrentDate(response);
  }


  private setCurrentDate = (weather: WeatherRequest) => {
    this.data.set({
      cityName: weather.cityName,
      hourlyWeather: weather.hourlyWeather.map(hour => ({
        ...hour, 
        temperature: Math.round(hour.temperature),
        feelsLike: Math.round(hour.feelsLike),
      })),
      dailyTemperature: weather.dailyTemperature.map(day => (
        {
          ...day,
          minTemperature: Math.round(day.minTemperature),
          maxTemperature: Math.round(day.maxTemperature),
          eveningTemperature: Math.round(day.eveningTemperature),
          nightTemperature: Math.round(day.nightTemperature),
          dayTemperature: Math.round(day.dayTemperature),
          morningTemperature: Math.round(day.morningTemperature),


          feelsLikeEvening: Math.round(day.feelsLikeEvening),
          feelsLikeNight: Math.round(day.feelsLikeNight),
          feelsLikeDay: Math.round(day.feelsLikeDay),
          feelsLikeMorning: Math.round(day.feelsLikeMorning),
        }
      ))
    })
  }


  setFocus(value: boolean) {
    if (value) {
      this.selectMode = true;
    }
    this.isFocused.set(value);
  }


  showSettings() {
    this.settingsMode.set(!this.settingsMode());
  }


  fetchData() {
    this.http.get<WeatherRequest>(this.BASE_URL+"/home/initial-request")
      
      .subscribe(response => {
        this.reactOnResponse(response);
    })
  }
}
