import { MensagensUtils } from '../../util/mensagens-util';
import {DataTable, SelectItem} from 'primeng/primeng';
import {Component, OnChanges, OnInit, ViewChild} from '@angular/core';
import { PageNotificationService } from '@basis/angular-components';
import { Router } from '@angular/router';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import {Page} from '../../util/page';
import {Pageable} from '../../util/pageable-request';
import {RelatorioPagamentoList} from './relatorio-pagamento-list.model';
import {RelatoriosService} from '../relatorios.service';
import {ListaNomesPessoaJuridica} from '../../pessoa-juridica/lista-nomes-pessoa-juridica.model';
import {NomePessoaJuridicaRelatorio} from './nome-pessoa-juridica-relatorio.model';
import {CustomUtils} from '../../util/custom-utils';
import { EnumService } from '../../shared/enum.service';


@Component({
    selector: 'app-relatorio-pagamento-list',
    templateUrl: './relatorio-pagamento-list.component.html',
    styleUrls: ['/relatorio-pagamento-list.component.css']})
export class RelatorioPagamentoListComponent implements OnInit, OnChanges {

    @BlockUI() blockUI: NgBlockUI;
    @ViewChild('dataTable') dataTable: DataTable;

    result: Page<RelatorioPagamentoList>;
    nomesPJ: ListaNomesPessoaJuridica[] = [];
    nomesPJRelatorio: NomePessoaJuridicaRelatorio[] = [];
    dropDownNomePessoaJuridica: SelectItem[];


    constructor(private relatoriosService: RelatoriosService,
                private router: Router,
                private pageNotificationService: PageNotificationService,
                private enumService: EnumService) {
    }

    ngOnInit() {
        this.ngOnChanges();
    }

    ngOnChanges() {
        this.createDropDown();
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

    listarNomes() {
        this.blockUI.start(MensagensUtils.CARREGANDO);
        this.relatoriosService.listarNomesPJs().subscribe(result => {
            this.nomesPJ = result.json();
        }, error => {
            this.blockUI.stop();
            this.pageNotificationService.addErrorMessage(error.toString() + MensagensUtils.ERRO_CARREGAR_DADOS);
        });
    }

    createDropDown() {
        this.converterNomes();
        this.nomesPJRelatorio.subscribe(result => {  CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_NOME,
            CustomUtils.CAMPO_VALOR_PADRAO); } );
        this.enumService.listarEnum(EnumService.SERVICO_TIPO_ENDERECO).subscribe(result => {
            this.dropDownNomePessoaJuridica = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_NOME,
                CustomUtils.CAMPO_VALOR_PADRAO);
        });
    }

    converterNomes() {
        this.listarNomes();
        this.nomesPJ.forEach(nome => {
            this.nomesPJRelatorio.push(new NomePessoaJuridicaRelatorio(nome.id, nome.cnpj
                .replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3') + ' - ' + nome.nomeFantasia));
        });
    }
}

