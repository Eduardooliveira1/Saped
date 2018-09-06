import { Component, OnChanges, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { PageNotificationService } from '@basis/angular-components';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { DataTable, SelectItem } from 'primeng/primeng';
import { PessoaJuridicaService } from '../../pessoa-juridica/pessoa-juridica.service';
import { DateConstants } from '../../shared/constants/date-constants';
import { CustomInputCurrencyMaskComponent } from '../../shared/custom-components/custom-input-currency-mask/custom-input-currency-mask.component';
import { EnumService } from '../../shared/enum.service';
import { CustomUtils } from '../../util/custom-utils';
import { MensagensUtils } from '../../util/mensagens-util';
import { Page } from '../../util/page';
import { Pageable } from '../../util/pageable-request';
import { RelatoriosService } from '../relatorios.service';
import { FiltroRelatorioPagamentos } from './filtro-relatorio-pagamento';
import { RelatorioPagamentoList } from './relatorio-pagamento-list';

@Component({
  selector: 'app-relatorio-pagamento',
  templateUrl: './relatorio-pagamento.component.html',
  styleUrls: ['./relatorio-pagamento.component.css']
})
export class RelatorioPagamentoComponent implements OnInit, OnChanges {

  @ViewChild('currencyValue') customInputCurrencyMaskComponent: CustomInputCurrencyMaskComponent;
  @BlockUI() blockUI: NgBlockUI;
  @ViewChild('dataTable') dataTable: DataTable;
  dropDownNomePessoaJuridica: SelectItem[];
  dropDownStatusBoleto: SelectItem[];
  dropDownMesReferencia: SelectItem[];
  form: FormGroup;
  ptBr = DateConstants.getCalendar();
  filtro: FiltroRelatorioPagamentos;

  idsPessoasJuridicas: number[];
  valor?: number;
  mesReferencia?: number;
  dataVencimento?: Date;
  tpStatusBoleto?: string;

  result: Page<RelatorioPagamentoList>;

  constructor(private router: Router,
              private pessoaJuridicaService: PessoaJuridicaService,
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
    this.enumService.listarEnum(EnumService.STATUS_BOLETO).subscribe((result: any) => {
      this.dropDownStatusBoleto = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
    this.pessoaJuridicaService.listarNomes().subscribe((result: any) => {
      this.dropDownNomePessoaJuridica = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
      this.dropDownMesReferencia = CustomUtils.entityToDropDown(DateConstants.getDropDownMesReferencia(), CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
  }

  updateFiltro() {
    this.valor = this.customInputCurrencyMaskComponent.valor;
    this.filtro = new FiltroRelatorioPagamentos(true,
      this.idsPessoasJuridicas,
      this.valor,
      this.mesReferencia,
      this.dataVencimento,
      this.tpStatusBoleto);
    if (!this.filtroIsNull()) {
      this.listarPagamentos(this.filtro);
    } else {
      this.pageNotificationService.addWarnMessage(MensagensUtils.FILTRO_INVALIDO);
    }
  }

  filtroIsNull(): boolean {
    return (this.filtro.hasFiltro === true) &&
      (this.filtro.idsPessoasJuridicas == null || this.filtro.idsPessoasJuridicas.length === 0) &&
      (this.filtro.valor == null) &&
      (!this.filtro.mesReferencia) &&
      (!this.filtro.dataVencimento) &&
      (!this.filtro.tpStatusBoleto);
  }

  listarPagamentos(filtro: FiltroRelatorioPagamentos) {
    if (filtro == null) {
      this.filtro = new FiltroRelatorioPagamentos(false);
    } else {
      this.filtro = filtro;
    }
    const pageable = new Pageable(this.dataTable);
    this.blockUI.start(MensagensUtils.CARREGANDO);
    this.relatoriosService.listarPagamentos(this.filtro, pageable).subscribe(result => {
      this.blockUI.stop();
      this.result = result;
    }, error => {
      this.blockUI.stop();
      this.pageNotificationService.addErrorMessage(error.toString() + MensagensUtils.ERRO_CARREGAR_DADOS);
    });
  }

}
