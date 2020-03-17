import { Directive, ElementRef, Renderer2, Input } from '@angular/core';

@Directive({
  selector: '[myNgStyle]'
})
export class MyNgStyleDirective {

  private ngStyles: {[key: string]: string};

  @Input('myNgStyle') set styles(styles: {[key: string]: string}) {
    this.ngStyles = styles;
    Object.keys(this.ngStyles).forEach(style => this.setStyle(style, this.ngStyles[style]));
  }

  constructor(private elementRef: ElementRef, private renderer: Renderer2) { 
  }

  private setStyle(name: string, value: string): void {
    if (value) {
      this.renderer.setStyle(this.elementRef.nativeElement, name, value as string);
    } else {
      this.renderer.removeStyle(this.elementRef.nativeElement, name);
    }
  }  
}
