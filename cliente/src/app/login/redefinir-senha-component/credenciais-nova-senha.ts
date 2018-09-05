export class CredenciaisNovaSenha {

  novaSenha: string;
  cnpj: string;

  constructor(cnpj: string, novaSenha: string) {
    this.novaSenha = novaSenha;
    this.cnpj = cnpj;
  }

}
