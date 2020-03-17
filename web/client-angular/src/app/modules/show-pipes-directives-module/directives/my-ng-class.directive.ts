import { Directive, ElementRef, Renderer2, Input } from '@angular/core';

@Directive({
  selector: '[myNgClass]'
})
export class MyNgClassDirective {

  private ngClasses: string[] = [];

  @Input('myNgClass') set classes(classes: string) {

    if(typeof classes === 'string' && classes) {
      this.ngClasses = classes.split(/\s+/);
      this.applyClasses(this.ngClasses);   
    }
  }

  constructor(private elementRef: ElementRef, private renderer: Renderer2) { 
  }

  private applyClasses(classes: string[]) {
    classes.forEach(item => this.renderer.addClass(this.elementRef.nativeElement, item));
  }
}
