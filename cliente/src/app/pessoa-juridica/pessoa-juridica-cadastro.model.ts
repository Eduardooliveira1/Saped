export class PessoaJuridicaCadastro{
    constructor(
        public id?: number,
        public cnpj?: string,
        public sigla?: string,
        public nomeFantasia?: string,
        public razaoSocial?: string
    ){
        
    }
}