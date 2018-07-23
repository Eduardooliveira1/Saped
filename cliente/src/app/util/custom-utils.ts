import { SelectItem } from 'primeng/primeng';
export class CustomUtils {

  entityToDropDown(list: any[], label, value): SelectItem[] {
    const items: SelectItem[] = [];

    list.forEach(item => {
      items.push({ label: item[label], value: item[value] });

    });
    return items;
  }
}