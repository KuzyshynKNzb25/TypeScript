import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'windDirection',
  standalone: true
})
export class WindDirectionPipe implements PipeTransform {

  transform(value: number): number {
    value = (value + 135) % 360;
    if (value < 23 || value > 338) {
      return 135;
    }

    for (let i = 45; i <= 315; i += 45){
      if (value > i - 24 && value < i + 23) {
        return i;
      }
    }
    return 0;
  }

}
