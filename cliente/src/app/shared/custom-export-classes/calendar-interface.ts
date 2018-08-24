import {InjectionToken} from '@angular/core';

export interface CalendarInterface {
  firstDayOfWeek: number;
  dayNames: string[];
  dayNamesShort: string[];
  dayNamesMin: string[];
  monthNames: string[];
  monthNamesShort: string[];
  today: string;
  clear: string;
}
export declare let CALENDAR: InjectionToken<CalendarInterface>;
