import { pessoaJuridicaRoute } from './pessoa-juridica.route';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaJuridicaListComponent } from './pessoa-juridica-list/pessoa-juridica-list.component';
import { RouterModule } from '@angular/router';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(pessoaJuridicaRoute)
  ],
  declarations: [PessoaJuridicaListComponent]
})
export class PessoaJuridicaModule { }
