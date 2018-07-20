import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pessoa-juridica-form',
  templateUrl: './pessoa-juridica-form.component.html',
  styleUrls: ['./pessoa-juridica-form.component.css']
})
export class PessoaJuridicaFormComponent implements OnInit {

  tituloPagina = "Cadastrar Pessoa Jurídica"
  
  constructor() { }

  ngOnInit() {
  }

}
