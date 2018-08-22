export class FiltroRelatorioPagamentos {
    idsPessoasJuridicas: number[];
    valor?: number;
    mesReferencia?: number;
    dataVencimento?: Date;
    tpStatusBoleto?: string;

    constructor() {
    }

    public updateFiltro( idsPessoasJuridicas: number[], valor: number, mesReferencia: number, dataVencimento: Date, tpBoleto: string) {
      this.idsPessoasJuridicas = idsPessoasJuridicas;
      this.valor = valor;
      this.mesReferencia = mesReferencia;
      this.dataVencimento = dataVencimento;
      this.tpStatusBoleto = tpBoleto;
    }

}
