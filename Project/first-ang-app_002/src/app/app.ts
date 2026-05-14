import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CardDayInfo } from './card-day-info/card-day-info';
import { HomePage } from './home-page/home-page';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HomePage, CardDayInfo],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('first-ang-app');
}
