import { Telefone } from './pessoa-representante-telefone';
export class PessoaRepresentante{
    constructor(
        public id?: number,
        public nome?: string,
        public cargo?: string,
        public email?: string,
        public notificacao?: string,
        public notificacaoFront?: boolean,
        public telefone?: Telefone[],
    ){
        
    }
}