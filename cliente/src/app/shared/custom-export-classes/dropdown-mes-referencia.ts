import {DropdownMesReferenciaInterface} from './dropdown-mes-referencia-interface';

const year = new Date().getFullYear();
export const CURRENT_YEAR_LIST: DropdownMesReferenciaInterface = {
  items: [{'id': 1, 'descricao': 'Janeiro/' + year},
    {'id': 2, 'descricao': 'Fevereiro/' + year},
    {'id': 3, 'descricao': 'Março/' + year},
    {'id': 4, 'descricao': 'Abril/' + year},
    {'id': 5, 'descricao': 'Maio/' + year},
    {'id': 6, 'descricao': 'Junho/' + year},
    {'id': 7, 'descricao': 'Julho/' + year},
    {'id': 8, 'descricao': 'Agosto/' + year},
    {'id': 9, 'descricao': 'Setembro/' + year},
    {'id': 10, 'descricao': 'Outubro/' + year},
    {'id': 12, 'descricao': 'Dezembro/' + year}]
};
