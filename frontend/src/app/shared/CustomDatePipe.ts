import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    standalone: true,
    name: 'customDate'
})
export class CustomDatePipe implements PipeTransform {
    transform(value: Date | string): string {
        const date = (typeof value === 'string') ? new Date(value) : value;

        if (isNaN(date.getTime())) {
            return ''; // Handle invalid date
        }
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');

        return `${day}.${month}.${year}, ${hours}:${minutes}`;
    }
}
