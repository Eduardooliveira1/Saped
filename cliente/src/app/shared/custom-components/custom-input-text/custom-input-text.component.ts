import {Component, ContentChild, forwardRef, Input, OnInit} from '@angular/core';
import {ControlValueAccessor, FormControlName, NG_VALUE_ACCESSOR} from '@angular/forms';

@Component({
    selector: 'app-custom-input-text',
    templateUrl: './custom-input-text.component.html',
    styleUrls: ['./custom-input-text.component.css'],
    providers: [
        { provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CustomInputTextComponent),
            multi: true
        }
    ]

})
export class CustomInputTextComponent implements OnInit, ControlValueAccessor {

    @Input() label: string;
    @Input() errorMessage: string;
    @Input() showErrorMessageAfterSubmitForm = false;
    @Input() showErrorMessage = true;
    @Input() showLabel = true;
    @Input() pattern: any = '';
    @Input() readonly = false;
    @Input() maxLength: number;
    @Input() currency = false;
    valor: number;

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

    onChange(event) {
        this._onChange(event.target.value);
    }

    getValue(): any {
        return this.value || null;
    }

    getDefaultClass(): string {
        if (!this.readonly) {
            if (this.value) {
                return 'ui-inputtext ui-corner-all ui-state-default ui-widget ui-state-filled ';
            } else {
                return 'ui-inputtext ui-corner-all ui-state-default ui-widget';
            }
        }
    }
}
