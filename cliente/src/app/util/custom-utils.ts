import { SelectItem } from 'primeng/primeng';
export class CustomUtils {

  public static CAMPO_VALOR_PADRAO = 'id';
  public static CAMPO_LABEL_PADRAO = 'descricao';


  public static entityToDropDown(list: any[], label, value): SelectItem[] {
    const items: SelectItem[] = [];

    list.forEach(item => {
      items.push({ label: item[label], value: item[value] });

    });
    return items;
  }
}