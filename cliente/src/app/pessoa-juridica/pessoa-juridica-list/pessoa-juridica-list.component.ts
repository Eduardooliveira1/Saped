import {Component, OnInit, ViewChild} from '@angular/core';
import {Pageable} from '../../util/pageable-request';
import {PessoaJuridicaService} from '../pessoa-juridica.service';
import {faEdit, faTrashAlt, faUserFriends} from '@fortawesome/free-solid-svg-icons';
import {DataTable} from 'primeng/primeng';
import {Page} from '../../util/page';
import {PessoaJuridicaLista} from './pessoa-juridica-lista.model';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import { Injectable} from '@angular/core';


@Component({
  selector: 'app-pessoa-juridica-list',
  templateUrl: './pessoa-juridica-list.component.html',
  styleUrls: ['./pessoa-juridica-list.component.css']
})
@Injectable()
export class PessoaJuridicaListComponent implements OnInit {

  @ViewChild('dataTable') dataTable: DataTable;

  result: Page<PessoaJuridicaLista>;
  filtro: string;
  ultimoFiltro: string;

  faUserFriends = faUserFriends;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;


  constructor(private pessoaJuridicaService: PessoaJuridicaService,
              private router: Router,
              private toastrService: ToastrService) {
  }

  ngOnInit() {
  }

  filtrar() {
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

  pesquisar() {
    const pageable = new Pageable(this.dataTable.first / this.dataTable.rows, this.dataTable.rows);
    pageable.setSort(this.dataTable.sortOrder, this.dataTable.sortField);

    this.pessoaJuridicaService.listarDirigentes(this.filtro, pageable)
      .subscribe(result => {
        this.result = result.json();
      });
  }

  excluir(id: number) {
    this.pessoaJuridicaService.apagarPessoa(id);
  }

}
