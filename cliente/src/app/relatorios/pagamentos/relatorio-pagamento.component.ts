import {Component, OnChanges, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {SelectItem} from 'primeng/primeng';
import {PessoaJuridicaService} from '../../pessoa-juridica/pessoa-juridica.service';
import {JhiDateUtils} from '../../shared';
import {EnumService} from '../../shared/enum.service';
import {CustomUtils} from '../../util/custom-utils';
import {MensagensUtils} from '../../util/mensagens-util';
import {FiltroRelatorioPagamentos} from './filtro-relatorio-pagamento';

@Component({
  selector: 'app-relatorio-pagamento',
  templateUrl: './relatorio-pagamento.component.html',
  styleUrls: ['/relatorio-pagamento.component.css']
})
export class RelatorioPagamentoComponent implements OnInit, OnChanges {


  filtro: FiltroRelatorioPagamentos;
  dropDownNomePessoaJuridica: SelectItem[];
  dropDownStatusBoleto: SelectItem[];
  dropDownMesReferencia: SelectItem[];
  msgPadraoCampoObrigatorio = MensagensUtils.CAMPO_OBRIGATORIO;
  form: FormGroup;
  submittedForm = false;

  constructor(private router: Router,
              private pessoaJuridicaService: PessoaJuridicaService,
              private dateUtilService: JhiDateUtils,
              private enumService: EnumService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.filtro = new FiltroRelatorioPagamentos();
    this.buildReactiveForm();
    this.ngOnChanges();
  }

  ngOnChanges() {
    this.createDropDowns();
  }


  createDropDowns() {
    this.enumService.listarEnum(EnumService.STATUS_BOLETO).subscribe(result => {
      this.dropDownStatusBoleto = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
    this.pessoaJuridicaService.listarNomes().subscribe(result => {
      this.dropDownNomePessoaJuridica =  CustomUtils.entityToDropDown(result,  CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
    this.dateUtilService.listarMesReferencia().subscribe(result => {
      this.dropDownMesReferencia = CustomUtils.entityToDropDown(result,  CustomUtils.CAMPO_LABEL_PADRAO,
        CustomUtils.CAMPO_VALOR_PADRAO);
    });
  }

  buildReactiveForm() {
    this.form = this.formBuilder.group({
      idsPJs: new FormControl('', ),
      statusBoleto: new FormControl('', ),
      valorBoleto: new FormControl('', ),
      mesReferencia: new FormControl('', ),
      dataVencimento: new FormControl('', )
    }, {updateOn: 'blur'});
  }

}
