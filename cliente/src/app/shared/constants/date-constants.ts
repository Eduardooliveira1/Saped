export class DateConstants {
  static getCalendar() {
    return {
      firstDayOfWeek: 0,
      dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
      dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
      dayNamesMin: ['Do', 'Se', 'Te', 'Qa', 'Qi', 'Se', 'Sa'],
      monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho',
        'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
      monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
      today: 'Hoje',
      clear: 'Limpar'
    };
  }

  static getDropDownMesReferencia() {
    const year = new Date().getFullYear();
    const items = [{'id': 1, 'descricao': 'Janeiro/' + year},
      {'id': 2, 'descricao': 'Fevereiro/' +  year},
      {'id': 3, 'descricao': 'Março/' +  year},
      {'id': 4, 'descricao': 'Abril/' +  year},
      {'id': 5, 'descricao': 'Maio/' +  year},
      {'id': 6, 'descricao': 'Junho/' +  year},
      {'id': 7, 'descricao': 'Julho/' +  year},
      {'id': 8, 'descricao': 'Agosto/' +  year},
      {'id': 9, 'descricao': 'Setembro/' +  year},
      {'id': 10, 'descricao': 'Outubro/' +  year},
      {'id': 12, 'descricao': 'Dezembro/' +  year}];
    return items;
  }
}
