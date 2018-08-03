import { Telefone } from './pessoa-representante-telefone';
export class PessoaRepresentante{
    id: number;
    nome: string;
    cargo: string;
    email: string;
    notificacao: string;
    telefone: Telefone[];

    constructor(
    ){
        this.telefone = [];
    }
}
