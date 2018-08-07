import { PessoaRepresentante } from './pessoa-representante-model';
export class PessoaJuridicaCadastro{
    id: number;
    cnpj: string;
    sigla: string;
    nomeFantasia: string;
    razaoSocial: string;
    representantes:PessoaRepresentante[];
    
    constructor(){
        this.representantes = [];
    }
}