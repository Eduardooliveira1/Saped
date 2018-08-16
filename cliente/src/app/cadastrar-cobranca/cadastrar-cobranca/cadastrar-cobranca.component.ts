import { CadastarCobrancaService } from './../cadastrar-cobranca.service';
import { SelectItem } from 'primeng/primeng';
import { CustomUtils } from './../../util/custom-utils';
import { NgBlockUI, BlockUI } from 'ng-block-ui';
import { Cobranca } from './../cobranca-model';
import { Component, OnInit } from '@angular/core';
import { faPrint, faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { PessoaJuridicaService } from '../../pessoa-juridica/pessoa-juridica.service';
import { MensagensUtils } from '../../util/mensagens-util';
import { PageNotificationService } from '@basis/angular-components';

@Component({
  selector: 'app-cadastrar-cobranca',
  templateUrl: './cadastrar-cobranca.component.html',
  styleUrls: ['./cadastrar-cobranca.component.css']
})

export class CadastrarCobrancaComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;


  pessoasJuridicasCadastradas:  SelectItem[];
  anosCobranca: SelectItem[];
  listaCobrancas: Cobranca[] = [];
  anoReferencia: string; 

  mostrarModalEmitirCobranca = false;
  emitirCobrancaCheckBox = false;
  
  constructor(private pageNotificationService: PageNotificationService,
              private pessoaJuridicaService: PessoaJuridicaService,
              private cadastarCobrancaService: CadastarCobrancaService) { 

  }
  
  ngOnInit() {
    this.listaCobrancas =[{acaoGerar:'', id:'1', ano:'2009', mesReferencia: 'Janeiro',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'Pago'},
                          {acaoGerar:'', id:'2', ano:'2010', mesReferencia: 'Fevereiro',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'Pago'},
                          {acaoGerar:'2ª Via', id:'3', ano:'2011', mesReferencia: 'Março',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'Vencido'},
                          {acaoGerar:'2ª Via', id:'4', ano:'2012', mesReferencia: 'Abril',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'Vencido'},
                          {acaoGerar:'Emitir', id:'5', ano:'2013', mesReferencia: 'Maio',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'},
                          {acaoGerar:'Emitir', id:'6', ano:'2014', mesReferencia: 'Junho',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'},
                          {acaoGerar:'Emitir', id:'7', ano:'2015', mesReferencia: 'Julho',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'},
                          {acaoGerar:'Emitir', id:'8', ano:'2016', mesReferencia: 'Agosto',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'},
                          {acaoGerar:'Emitir', id:'9', ano:'2017', mesReferencia: 'Setembro',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'},
                          {acaoGerar:'Emitir', id:'10', ano:'2018', mesReferencia: 'Outubro',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'},
                          {acaoGerar:'Emitir', id:'11', ano:'2019', mesReferencia: 'Novembro',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'},
                          {acaoGerar:'Emitir', id:'12', ano:'2000', mesReferencia: 'Dezembro',dataVencimento: '',dataPagamento: '', dataSegundaVia:'', valor: '345,52', status:'À vencer'}];

    this.obterPessoasuridicas();
    this.obterAnosCobranca();
  }

  obterPessoasuridicas() {
    this.blockUI.start(MensagensUtils.CARREGANDO);
    this.pessoaJuridicaService.listarTodas()
      .subscribe(result => {
        this.blockUI.stop();
        this.pessoasJuridicasCadastradas = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_PESSOA_JURIDICA_PADRAO, CustomUtils.CAMPO_VALOR_PADRAO);
      }, error => {
        this.blockUI.stop();
        this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_CARREGAR_DADOS);
      });
  }

  obterAnosCobranca() {
    this.anosCobranca = CustomUtils.entityToDropDown(this.listaCobrancas, CustomUtils.CAMPO_ANO_REFERENCIA, CustomUtils.CAMPO_VALOR_PADRAO);
  }

  deletarCobranca(id) {
    alert("deletar cobrança " + id);
  }

  imprimirCobranca(id) {
    alert("Imprimir cobrança " + id);
  }

  gerarCobranca() {
    this.mostrarModalEmitirCobranca = true;
  }

  emitirCobranca() {
    this.mostrarModalEmitirCobranca = false;
  }

  cancelar() {
    this.mostrarModalEmitirCobranca = false;
  }
  
  botaoGerar(mesRefetencia) {
    false;
  }

  emitirTudo() {
    alert("Emitir tudo");
  }

  adiantarPagamento() {
    alert("Adiantar pagamento");
  }

  exportar() {
    alert("Exportar");
  }

  bucarQuintosDiasUteis() {
      let anoSelecionado = this.anosCobranca[parseInt(this.anoReferencia)-1].label;
      this.cadastarCobrancaService.obterQuintosDiasUtis(anoSelecionado).subscribe(result=>{
        this.atualizaColunaQuintoDiaUtil(result)
      });
  }

  atualizaColunaQuintoDiaUtil(quintosdiasUteis: any) {
    for(let i = 0; i< this.listaCobrancas.length; i++) {
      this.listaCobrancas[i].dataVencimento = quintosdiasUteis[i];
    }
  }
}
