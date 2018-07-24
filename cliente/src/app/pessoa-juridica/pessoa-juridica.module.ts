import {FormsModule} from '@angular/forms';
import {pessoaJuridicaRoute} from './pessoa-juridica.route';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PessoaJuridicaListComponent} from './pessoa-juridica-list/pessoa-juridica-list.component';
import {RouterModule} from '@angular/router';
import {ButtonModule, DataTableModule} from 'primeng/primeng';
import {PessoaJuridicaService} from './pessoa-juridica.service';
import {Ng2BRPipesModule} from 'ng2-brpipes';
import {ToastrModule} from 'ngx-toastr';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {HttpClientModule} from '@angular/common/http';
import { HttpRestModule } from 'ngx-http-rest';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(pessoaJuridicaRoute),
    FormsModule,
    ButtonModule,
    DataTableModule,
    Ng2BRPipesModule,
    FontAwesomeModule,
    HttpClientModule,
    HttpRestModule,
    ToastrModule.forRoot(),
  ],
  declarations: [PessoaJuridicaListComponent],
  providers: [
      PessoaJuridicaService,
  ],
  exports: [Ng2BRPipesModule]
})

export class PessoaJuridicaModule { }

