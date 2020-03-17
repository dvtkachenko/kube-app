import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'sumarray',
    pure: false
})
export class SumArrayPipe implements PipeTransform {
    transform(array: number[]): number | null {
        if (array.length < 1) {
            return;
        }
        return array.reduce((a,b) => a + b);
    }
}