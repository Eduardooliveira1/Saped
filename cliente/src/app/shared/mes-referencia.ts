export class MesReferencia {

  public list: any[];

  constructor() {
    const year: number = new Date().getFullYear();
    const anos: number = year - 4;
    let j = 1;
    for (let i = anos; i < year; i++) {
      const jan = [
        '' + j++,
        'Janeiro/' + i];
      const fev = [
        '' + j++,
        'Fevereiro/' + i];
      const mar = [
        '' + j++,
        'Março/' + i];
      const abr = [
        '' + j++,
        'Abril/' + i];
      const mai = [
        '' + j++,
        'Maio/' + i];
      const jun = [
        '' + j++,
        'Junho/' + i];
      const jul = [
        '' + j++,
        'Julho/' + i];
      const ago = [
        '' + j++,
        'Agosto/' + i];
      const set = [
        '' + j++,
        'Setembro/' + i];
      const out = [
        '' + j++,
        'Outubro/' + i];
      const nov = [
        '' + j++,
        'Novembro/' + i];
      const dez = [
        '' + j++,
        'Dezembro/' + i];
      this.list.push(jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez);
    }
  }
}
