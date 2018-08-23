import {Component, OnChanges, OnInit, ViewChild} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {PageNotificationService} from '@basis/angular-components';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {DataTable, SelectItem} from 'primeng/primeng';
import {PessoaJuridicaService} from '../../pessoa-juridica/pessoa-juridica.service';
import {JhiDateUtils} from '../../shared';
import {CustomInputCurrencyMaskComponent} from '../../shared/custom-components/custom-input-currency-mask/custom-input-currency-mask.component';
import {PT_BR} from '../../shared/custom-export-classes/calendar';
import {EnumService} from '../../shared/enum.service';
import {CustomUtils} from '../../util/custom-utils';
import {MensagensUtils} from '../../util/mensagens-util';
import {Page} from '../../util/page';
import {Pageable} from '../../util/pageable-request';
import {RelatoriosService} from '../relatorios.service';
import {FiltroRelatorioPagamentos} from './filtro-relatorio-pagamento';
import {RelatorioPagamentoList} from './relatorio-pagamento-list';

@Component({
  selector: 'app-relatorio-pagamento',
  templateUrl: './relatorio-pagamento.component.html',
  styleUrls: ['/relatorio-pagamento.component.css']
})
export class RelatorioPagamentoComponent implements OnInit, OnChanges {

  @ViewChild('currencyValue') customInputCurrencyMaskComponent: CustomInputCurrencyMaskComponent;
  @BlockUI() blockUI: NgBlockUI;
  @ViewChild('dataTable') dataTable: DataTable;
  dropDownNomePessoaJuridica: SelectItem[];
  dropDownStatusBoleto: SelectItem[];
  dropDownMesReferencia: SelectItem[];
  form: FormGroup;
  ptBr = PT_BR;
  filtro: FiltroRelatorioPagamentos;

  idsPessoasJuridicas: number[];
  valor?: number;
  mesReferencia?: number;
  dataVencimento?: Date;
  tpStatusBoleto?: string;

  result: Page<RelatorioPagamentoList>;

  constructor(private router: Router,
              private pessoaJuridicaService: PessoaJuridicaService,
              private dateUtilService: JhiDateUtils,
              private pageNotificationService: PageNotificationService,
              private enumService: EnumService,
              private relatoriosService: RelatoriosService) {
  }

  ngOnInit() {
    this.createDropDowns();
  }

  ngOnChanges() {

  }

  createDropDowns() {
    this.enumService.listarEnum(EnumService.STATUS_BOLETO).subscribe(result => {
      this.dropDownStatusBoleto = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
    this.pessoaJuridicaService.listarNomes().subscribe(result => {
      this.dropDownNomePessoaJuridica = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
    this.dateUtilService.listarMesReferencia().subscribe((result: any[]) => {
      this.dropDownMesReferencia = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
  }

  updateFiltro() {
    this.valor = this.customInputCurrencyMaskComponent.valor;
    this.filtro = new FiltroRelatorioPagamentos(this.idsPessoasJuridicas,
      this.valor,
      this.mesReferencia,
      this.dataVencimento,
      this.tpStatusBoleto);
    if (!this.filtroIsNull()) {
      this.listarPagamentos(this.filtro, true);
    } else {
      this.pageNotificationService.addWarnMessage(MensagensUtils.FILTRO_INVALIDO);
    }
  }

  filtroIsNull(): boolean {
    return (this.filtro.idsPessoasJuridicas === undefined || this.filtro.idsPessoasJuridicas.length === 0) &&
      (!(this.filtro.valor || this.filtro.valor === 0)) &&
      (!this.filtro.mesReferencia) &&
      (!this.filtro.dataVencimento) &&
      (!this.filtro.tpStatusBoleto);
  }


  listarPagamentos(filtro: FiltroRelatorioPagamentos, hasFiltro: Boolean) {
    this.filtro = filtro;
    const pageable = new Pageable(this.dataTable.first / this.dataTable.rows, this.dataTable.rows);
    this.blockUI.start(MensagensUtils.CARREGANDO);
    this.relatoriosService.listarPagamentos(filtro, pageable, hasFiltro).subscribe(result => {
      this.blockUI.stop();
      this.result = result;
    }, error => {
      this.blockUI.stop();
      this.pageNotificationService.addErrorMessage(error.toString() + MensagensUtils.ERRO_CARREGAR_DADOS);
    });
  }

}
