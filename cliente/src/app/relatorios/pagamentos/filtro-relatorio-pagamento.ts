export class FiltroRelatorioPagamentos {

  public idsPessoasJuridicas: number[];
  public valor?: number;
  public mesReferencia?: number;
  public dataVencimento?: Date;
  public tpStatusBoleto?: string;

  constructor(idsPessoasJuridicas: number[],
              valor?: number,
              mesReferencia?: number,
              dataVencimento?: Date,
              tpStatusBoleto?: string) {
    this.idsPessoasJuridicas = idsPessoasJuridicas;
    this.valor = valor;
    this.mesReferencia = mesReferencia;
    this.dataVencimento = dataVencimento;
    this.tpStatusBoleto = tpStatusBoleto;
  }
}
