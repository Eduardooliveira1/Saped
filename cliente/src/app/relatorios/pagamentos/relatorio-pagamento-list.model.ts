export class RelatorioPagamentoList{

    constructor(
        public nomeFantasia?: string,
        public  valorBoleto?: number,
        public  mesReferencia?: number,
        public  dataVencimento?: Date,
        public dataSegundaVia?: Date
    ) {
    }

}
