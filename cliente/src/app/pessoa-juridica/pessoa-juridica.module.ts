import { FormsModule } from '@angular/forms';
import { pessoaJuridicaRoute } from './pessoa-juridica.route';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaJuridicaListComponent } from './pessoa-juridica-list/pessoa-juridica-list.component';
import { RouterModule } from '@angular/router';
import { ButtonModule, DataTableModule } from 'primeng/primeng';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(pessoaJuridicaRoute),
    FormsModule,
    ButtonModule,
    DataTableModule
  ],
  declarations: [PessoaJuridicaListComponent]
})
export class PessoaJuridicaModule { }
