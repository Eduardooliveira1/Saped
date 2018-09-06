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

export class FiltroListagemCobranca{
    public ano: string;
    public idPj: string;
    constructor(ano: string , idPj: string ){
        this.ano = ano;
        this.idPj =  idPj;
    }
}

export class DadosGerarBoleto{
    public idPj: string;
    public dataVencimento: string;
    public valor: string;

    constructor(idPj: string, dataVencimento: string, valor: any){
        this.idPj = idPj;
        this.dataVencimento = dataVencimento;
        this.valor =  valor;
    }
}

