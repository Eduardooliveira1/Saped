import { Component, OnInit } from '@angular/core';
import { Pageable } from '../../util/pageable-request';
import { PessoaJuridicaService } from '../pessoa-juridica.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { IfObservable } from 'rxjs/observable/IfObservable';
import { faUserFriends, faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core'

@Component({
  selector: 'app-pessoa-juridica-list',
  templateUrl: './pessoa-juridica-list.component.html',
  styleUrls: ['./pessoa-juridica-list.component.css']
})
export class PessoaJuridicaListComponent implements OnInit {

  result: any;
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
      this.pesquisar(null);
    }else if(!this.filtro || this.filtro.length == 0 && this.ultimoFiltro){
        this.filtro = null;
        this.ultimoFiltro = null;
        this.pesquisar(null);
    }
  }

  pesquisar(event) {

    const pageable = new Pageable(0, 10);
    if (event) {
      pageable.setPage(event.first ? event.first : 0);
      pageable.setSize(event.rows);
      if (event.sortField && event.sortOrder) {
        pageable.setSort(event.sortOrder, event.sortField);
      } else {
        pageable.setSort(1, 'nomeFantasia');
      }
    }
    this.pessoaJuridicaService.listarDirigentes(this.filtro, pageable)
      .subscribe(result => {
        this.result = result.json();
      });
  }
}
