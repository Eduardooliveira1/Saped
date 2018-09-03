import { Component, ContentChild, forwardRef, Input, OnInit } from '@angular/core';
import { FormControlName, NG_VALUE_ACCESSOR } from "@angular/forms";

@Component({
  selector: 'app-custom-textarea',
  templateUrl: './custom-textarea.component.html',
    providers: [
        { provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(()=> CustomTextareaComponent),
            multi: true
        }
    ]
})
export class CustomTextareaComponent implements OnInit {

    @Input() label: string;
    @Input() errorMessage: string;
    @Input() showErrorMessageAfterSubmitForm: boolean = false;
    @Input() showErrorMessage: boolean = true;
    @Input() readonly : boolean = false;
    @Input() showLabel: boolean = true;
    @Input() rows: number;
    @Input() cols: number;
    @Input() placeholder: string = "";

    @ContentChild(FormControlName) formControl: FormControlName;

    private value: string;

    _onChange: any = () => {};
    _registerOnTouched: any = () => {};

    constructor() {
    }

    ngOnInit() {
    }

    hasError(): boolean {
        if (this.formControl) {
            if (!this.formControl.valid && this.showErrorMessageAfterSubmitForm) {
                return true;
            } else {
                return !this.formControl.valid && this.formControl.touched;
            }
        } else {
            return false;
        }
    }

    registerOnChange(fn: any): void {
        this._onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this._registerOnTouched = fn;
    }

    setDisabledState(isDisabled: boolean): void {
    }

    setTouched() {
        this._registerOnTouched(true);
    }

    writeValue(obj: any): void {
        this.value = obj;
    }

    onChange(event){
        this._onChange(event.target.value);
    }

    getValue(): any {
        return this.value || null;
    }

}
