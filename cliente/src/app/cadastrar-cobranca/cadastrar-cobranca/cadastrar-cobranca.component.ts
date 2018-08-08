import { AnoCobranca } from './../ano-cobranca-model';
import { Cobranca } from './../cobranca-model';
import { Component, OnInit } from '@angular/core';
import {DropdownModule} from 'primeng/dropdown';
import { faPrint, faTimesCircle, faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-cadastrar-cobranca',
  templateUrl: './cadastrar-cobranca.component.html',
  styleUrls: ['./cadastrar-cobranca.component.css']
})

export class CadastrarCobrancaComponent implements OnInit {

  constructor() { }

  listaCobrancas: Cobranca[] = [];
  anosCobranca: AnoCobranca[] = [];
  anoCobranca1: AnoCobranca;
  anoCobranca2: AnoCobranca;

  faPrint = faPrint;
  faTimesCircle = faTimesCircle;
  faTrashAlt = faTrashAlt;
  
  
  ngOnInit() {
    // this.anoCobranca1.ano='2018';
    // this.anoCobranca1.cobrancaAnual = [{mesReferencia: 'Janeiro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Fevereiro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'março',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Abril',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Maio',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Junho',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Julho',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Agosto',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Setembro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Outubro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Novembro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    //                                   {mesReferencia: 'Dezembro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'}];
    // this.anoCobranca2.ano = '2019';
    // this.anoCobranca2.cobrancaAnual =[{mesReferencia: 'Janeiro',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Fevereiro',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'março',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Abril',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Maio',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Junho',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Julho',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Agosto',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Setembro',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Outubro',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Novembro',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'},
    //                                   {mesReferencia: 'Dezembro',dataVencimento: '13/12/2019',dataPagamento: '13/12/2019', dataSegundaVia:'13/12/2019', valor: '1550,45', status:'À vencer'}];

    // this.anosCobranca.push(this.anoCobranca1,this.anoCobranca2);
    
    this.listaCobrancas = [{mesReferencia: 'Janeiro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Fevereiro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'março',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Abril',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Maio',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Junho',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Julho',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Agosto',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Setembro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Outubro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Novembro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'},
    {mesReferencia: 'Dezembro',dataVencimento: '05/05/2018',dataPagamento: '05/05/2018', dataSegundaVia:'05/05/2018', valor: '345,52', status:'À vencer'}];
  }

  deletarCobranca(id) {
    alert("deletar cobrança " + id);
  }

  imprimirCobranca(id) {
    alert("Imprimir cobrança " + id);
  }

  gerarCobranca() {
    alert("Gerar cobrança");
  }

  teste() {
    alert("value teste");
  }
}
