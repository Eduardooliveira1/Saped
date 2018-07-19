import { Component, OnInit, ViewChild } from '@angular/core';
import { Pageable } from '../../util/pageable-request';
import { PessoaJuridicaService } from '../pessoa-juridica.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { IfObservable } from 'rxjs/observable/IfObservable';
import { faUserFriends, faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core'
import { DataTable } from 'primeng/primeng';
import { Page } from '../../util/page';
import { PessoaJuridicaLista } from './pessoa-juridica-lista.model';

@Component({
  selector: 'app-pessoa-juridica-list',
  templateUrl: './pessoa-juridica-list.component.html',
  styleUrls: ['./pessoa-juridica-list.component.css']
})
export class PessoaJuridicaListComponent implements OnInit {

  @ViewChild('dataTable') dataTable: DataTable;

  result: Page<PessoaJuridicaLista>;
  filtro: string;
  ultimoFiltro: string;

  faUserFriends = faUserFriends;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;

  constructor(private pessoaJuridicaService: PessoaJuridicaService) { }

  ngOnInit() {
  }

  filtrar(value) {
    if (this.filtro && this.filtro.length >= 3) {
      this.ultimoFiltro = this.filtro;
      this.dataTable.first = 0;
      this.pesquisar();
    } else if (!this.filtro || this.filtro.length == 0 && this.ultimoFiltro) {
      this.filtro = null;
      this.ultimoFiltro = null;
      this.dataTable.first = 0;
      this.pesquisar();
    }
  }

  pesquisar(page?: number) {

    let pageable = new Pageable(this.dataTable.first / this.dataTable.rows, this.dataTable.rows);
    pageable.setSort(this.dataTable.sortOrder, this.dataTable.sortField);

    this.pessoaJuridicaService.listarDirigentes(this.filtro, pageable)
      .subscribe(result => {
        this.result = result.json();
      });
  }
}
