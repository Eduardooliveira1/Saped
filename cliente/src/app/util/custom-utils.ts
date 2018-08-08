import { SelectItem } from 'primeng/primeng';
import {ListaNomesPessoaJuridica} from '../pessoa-juridica/lista-nomes-pessoa-juridica.model';
export class CustomUtils {

  public static CAMPO_VALOR_PADRAO = 'id';
  public static CAMPO_LABEL_PADRAO = 'descricao';
  public static CAMPO_LABEL_NOME = 'nome';

  public static entityToDropDown(list: any[], label, value): SelectItem[] {
    const items: SelectItem[] = [];

    list.forEach(item => {
      items.push({ label: item[label], value: item[value] });

    });
    return items;
  }

}
