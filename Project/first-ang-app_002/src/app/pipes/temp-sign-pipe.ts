import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tempSign',
  standalone: true
})
export class TempSignPipe implements PipeTransform {

  transform(value: number): string {
    if (value === undefined || value === null) return '';

    return value > 0 ? `+${value}` : `${value}`;
  }

}
