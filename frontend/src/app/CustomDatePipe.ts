import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    standalone: true,
    name: 'customDate'
})
export class CustomDatePipe implements PipeTransform {
    transform(value: string): string {
        const date = new Date(value);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');

        return `${day}.${month}.${year}, ${hours}:${minutes}`;
    }
}
