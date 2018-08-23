export class Cobranca{
    public mesReferencia: string;
    public dataVencimento: string;
    public dataPagamento: string;
    public dataSegundaVia: string;
    public valor: string;
    public idPj: string;
    public acaoGerar: string;
    public habilitaExcluir: boolean;
    public habilitaImprimir: boolean;
    public habilitarGerarBoleto: boolean;
    public habilitarInserirValor: boolean;
    public status: Status;

    constructor(
    ){
        
    }
}

export class Status {
    public codigo: string;
    public descricao: string;
    constructor() {}
}