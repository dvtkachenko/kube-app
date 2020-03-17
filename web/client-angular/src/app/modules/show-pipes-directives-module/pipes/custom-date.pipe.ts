import { Pipe, PipeTransform } from "@angular/core";

const dateDelimeter: string = '-';
const dateTimeDelimeter: string = ' ';
const timeDelimeter: string = ':';

class CustomDate {

    formats: string [] = [];

    year: number;
    month: number;
    date: number;
    hours: number;
    minutes: number;
    seconds: number;
    miliseconds: number;
    day: number;

    constructor(timestampDate: number, dateFormat: string) {
        
        this.formats = dateFormat.split(/\/|\s|\:/);
        const date = new Date(timestampDate);

        this.year = date.getFullYear();
        this.month = date.getMonth();
        this.date = date.getDate();
        this.hours = date.getHours();
        this.minutes = date.getMinutes();
        this.seconds = date.getSeconds();
        this.miliseconds = date.getMilliseconds();
        this.day = date.getDay();
    }

    public toString() {

        return this.getYearAsString(this.getFormat('Y')) 
               + dateDelimeter 
               + this.getMonthAsString()
               + dateDelimeter
               + this.getDateAsString()
               + dateTimeDelimeter
               + this.getHoursAsString()
               + timeDelimeter
               + this.getMinutesAsString()
               + timeDelimeter
               + this.getSecondsAsString();
    }

    private getFormat(key: string): string {
        let result: string;
        this.formats.forEach(item => { 
            if (item.includes(key)) {
                result = item; 
            }
        });
        return result;
    }

    private getYearAsString(format: string): string {
        
        let result: string;
        const testYear = this.year % 100;

        if (format.length < 3) {
            testYear < 10 ? result = '0' + testYear.toString() : result = testYear.toString() 
        } else {
            result = this.year.toString();           
        }
        return result; 
    }
    
    private getMonthAsString(): string {
        if (this.month < 10) {
            return '0' + this.month.toString();
        }
        return this.month.toString();
    }
        
    private getDateAsString(): string {
        if (this.date < 10) {
            return '0' + this.date.toString();
        }
        return this.date.toString();
    }
    
    private getHoursAsString(): string {
        if (this.hours < 10) {
            return '0' + this.hours.toString();
        }
        return this.hours.toString();
    }
    
    private getMinutesAsString(): string {
        if (this.minutes < 10) {
            return '0' + this.minutes.toString();
        }
        return this.minutes.toString();
    }
    
    private getSecondsAsString(): string {
        if (this.seconds < 10) {
            return '0' + this.seconds.toString();
        }
        return this.seconds.toString();
    }
    
    private getMilisecondsAsString(): string {
        if (this.miliseconds < 10) {
            return '0' + this.miliseconds.toString();
        }
        return this.miliseconds.toString();
    }
    
    private getDayAsString(): string {
        return this.day.toString();
    }

}

@Pipe({
    name: 'customDate'
})
export class CustomDatePipe implements PipeTransform {
    transform(timestampDate: number, locale: string = "en-US", dateFormat: string = "YYYY/MM/DD HH:mm:ss"): string | null {

        // TODO there is should be my own format data implementation here
        // it needs more time and will be implemented later

        let customDate: CustomDate = new CustomDate(timestampDate, dateFormat);

        if (timestampDate) {
            return customDate.toString();
        } else {
            return null;
        }
    }
}