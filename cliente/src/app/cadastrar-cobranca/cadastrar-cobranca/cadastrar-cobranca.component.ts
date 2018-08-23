import { DadosUtils } from '../../util/dados-utils';
import { CadastarCobrancaService } from '../cadastrar-cobranca.service';
import { SelectItem } from 'primeng/primeng';
import { CustomUtils } from '../../util/custom-utils';
import { NgBlockUI, BlockUI } from 'ng-block-ui';
import { Cobranca } from '../cobranca-model';
import { Component, OnInit } from '@angular/core';
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
  anosReferencia: any[] = [];
  listaCobrancas: Cobranca[] = [];
  anoReferencia: string; 
  idPessoaJuridicaSelecionada: string;
  idCobrancaEmitir : any;

  mostrarModalEmitirCobranca = false;
  emitirCobrancaCheckBox = false;
  
  constructor(private pageNotificationService: PageNotificationService,
              private pessoaJuridicaService: PessoaJuridicaService,
              private cadastarCobrancaService: CadastarCobrancaService) { 

  }
  
  ngOnInit() {
    this.anosReferencia = DadosUtils.anosReferencia;
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
    this.anosCobranca = CustomUtils.entityToDropDown(this.anosReferencia, CustomUtils.CAMPO_ANO_REFERENCIA, CustomUtils.CAMPO_VALOR_PADRAO);
  }

  deletarCobranca(id) {
    alert("deletar cobrança " + id);
  }

  imprimirCobranca(id) {
    alert("Imprimir cobrança " + id);
  }

  gerarCobranca(id) {
    this.idCobrancaEmitir = id;
    if(this.listaCobrancas[id].valor) {
      this.mostrarModalEmitirCobranca = true;
    } else {
      this.pageNotificationService.addWarnMessage(MensagensUtils.COBRANCA_INSERIR_VALOR);
    }
  }

  emitirCobranca() {
    this.listaCobrancas[this.idCobrancaEmitir]

        this.blockUI.start(MensagensUtils.CARREGANDO);
      this.cadastarCobrancaService.gerarBoleto(this.listaCobrancas[this.idCobrancaEmitir])
      .subscribe(result => {
        this.blockUI.stop();
        this.listaCobrancas[this.idCobrancaEmitir] = result;
      }, error => {
        this.blockUI.stop();
        this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_CARREGAR_DADOS);
      });

    this.mostrarModalEmitirCobranca = false;
  }

  cancelar() {
    this.mostrarModalEmitirCobranca = false;
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

  obterListaDeCobrancas() {
      let anoSelecionado = this.anosCobranca[parseInt(this.anoReferencia)-1].label;
      this.cadastarCobrancaService.obterCobrancasDoAno(anoSelecionado, this.idPessoaJuridicaSelecionada).subscribe(result=>{
        this.listaCobrancas = result;
      });
  }

  atualizaColunaQuintoDiaUtil(quintosdiasUteis: any) {
    for(let i = 0; i< this.listaCobrancas.length; i++) {
      this.listaCobrancas[i].dataVencimento = quintosdiasUteis[i];
    }
  }
}
