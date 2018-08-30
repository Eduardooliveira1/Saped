export class RelatorioPagamentoList {

  cnpj: string;
  nomeFantasia: string;
  valorBoleto: number;
  mesReferencia: string;
  dataVencimento: string;
  statusBoleto: string;

  constructor() {
    this.cnpj = null;
    this.nomeFantasia = null;
    this.valorBoleto = null;
    this.mesReferencia = null;
    this.dataVencimento = null;
    this.statusBoleto = null;
  }

}
