import {Component, OnChanges, OnInit, ViewChild} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {SelectItem} from 'primeng/primeng';
import {PessoaJuridicaService} from '../../pessoa-juridica/pessoa-juridica.service';
import {JhiDateUtils} from '../../shared';
import {CustomInputTextComponent} from '../../shared/custom-components/custom-input-text/custom-input-text.component';
import {PT_BR} from '../../shared/custom-export-classes/calendar';
import {EnumService} from '../../shared/enum.service';
import {CustomUtils} from '../../util/custom-utils';
import {FiltroRelatorioPagamentos} from './filtro-relatorio-pagamento';
import {RelatorioPagamentoListComponent} from './pagamentos-list-component/relatorio-pagamento-list.component';

@Component({
  selector: 'app-relatorio-pagamento',
  templateUrl: './relatorio-pagamento.component.html',
  styleUrls: ['/relatorio-pagamento.component.css']
})
export class RelatorioPagamentoComponent implements OnInit, OnChanges {

  @ViewChild('relatorioPagamentoList') relatorioPagamentoList: RelatorioPagamentoListComponent;
  @ViewChild('currencyValue') customInputTextComponent: CustomInputTextComponent;
  filtro: FiltroRelatorioPagamentos;
  dropDownNomePessoaJuridica: SelectItem[];
  dropDownStatusBoleto: SelectItem[];
  dropDownMesReferencia: SelectItem[];
  form: FormGroup;
  ptBr = PT_BR;

  constructor(private router: Router,
              private pessoaJuridicaService: PessoaJuridicaService,
              private dateUtilService: JhiDateUtils,
              private enumService: EnumService) {
  }

  ngOnInit() {
    this.filtro = new FiltroRelatorioPagamentos();
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
    this.dateUtilService.listarMesReferencia().subscribe(result => {
      this.dropDownMesReferencia = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
  }

  updateFiltro() {
    this.filtro.valor = this.customInputTextComponent.valor;
    this.relatorioPagamentoList.listarPagamentos(this.filtro);
  }


}
