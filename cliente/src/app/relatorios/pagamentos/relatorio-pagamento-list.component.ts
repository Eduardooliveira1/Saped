import {Component, OnChanges, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {PageNotificationService} from '@basis/angular-components';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {DataTable, SelectItem} from 'primeng/primeng';
import {EnumService} from '../../shared/enum.service';
import {MesReferencia} from '../../shared/mes-referencia';
import {CustomUtils} from '../../util/custom-utils';
import {MensagensUtils} from '../../util/mensagens-util';
import {Page} from '../../util/page';
import {Pageable} from '../../util/pageable-request';
import {RelatoriosService} from '../relatorios.service';
import {FiltroRelatorioPagamentos} from './filtro-relatorio-pagamento';


@Component({
    selector: 'app-relatorio-pagamento-list',
    templateUrl: './relatorio-pagamento-list.component.html',
    styleUrls: ['/relatorio-pagamento-list.component.css']})
export class RelatorioPagamentoListComponent implements OnInit, OnChanges {

    @BlockUI() blockUI: NgBlockUI;
    @ViewChild('dataTable') dataTable: DataTable;
    form: FormGroup;

    result: Page<FiltroRelatorioPagamentos>;
    filtro: FiltroRelatorioPagamentos;
    dropDownNomePessoaJuridica: SelectItem[];
    dropDownStatusBoleto: SelectItem[];
    dropDownMesReferencia: SelectItem[];
    msgPadraoCampoObrigatorio = MensagensUtils.CAMPO_OBRIGATORIO;
    submittedForm = false;

    constructor(private relatoriosService: RelatoriosService,
                private formBuilder: FormBuilder,
                private router: Router,
                private pageNotificationService: PageNotificationService,
                private enumService: EnumService) {
    }

    ngOnInit() {
        this.filtro = new FiltroRelatorioPagamentos();
        this.buildReactiveForm();
        this.ngOnChanges();
    }

    ngOnChanges() {
        this.createDropDowns();
    }

    listarPagamentos() {
        const pageable = new Pageable(this.dataTable.first / this.dataTable.rows, this.dataTable.rows);
        pageable.setSort(this.dataTable.sortOrder, this.dataTable.sortField);

        this.blockUI.start(MensagensUtils.CARREGANDO);
        this.relatoriosService.listarPagamentos(pageable)
            .subscribe(result => {
                this.blockUI.stop();
                this.result = result.json();
            }, error => {
                this.blockUI.stop();
                this.pageNotificationService.addErrorMessage(error.toString() + MensagensUtils.ERRO_CARREGAR_DADOS);
            });
    }

    createDropDowns() {
        this.enumService.listarEnum(EnumService.SERVICO_LIST_NOMES_PJ).subscribe(result => {
            this.dropDownNomePessoaJuridica = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
                CustomUtils.CAMPO_VALOR_PADRAO);
        });
        this.enumService.listarEnum(EnumService.STATUS_BOLETO).subscribe(result => {
            this.dropDownStatusBoleto = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO,
                CustomUtils.CAMPO_VALOR_PADRAO);
        });
        this.dropDownMesReferencia = CustomUtils.entityToDropDown(new MesReferencia(),  CustomUtils.CAMPO_LABEL_PADRAO,
          CustomUtils.CAMPO_VALOR_PADRAO);
    }

    buildReactiveForm() {
        this.form = this.formBuilder.group({
            idsPJs: new FormControl('', ),
            statusboleto: new FormControl('', ),
            valorBoleto: new FormControl('', ),
            mesReferencia: new FormControl('', ),
            dataVencimento: new FormControl('', )
        }, { updateOn: 'blur' });
    }

}

