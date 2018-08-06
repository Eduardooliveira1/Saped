import { Injectable } from "@angular/core";

export class MensagensUtils {
   
    /**
     * globais
     */
   
    public static CAMPO_OBRIGATORIO ='Campo obrigatório.';
    public static CARREGANDO='Carregando...';
    public static CONFIRMACAO_EXCLUSAO = 'Realmente deseja excluir o item selecionado?';
    public static ERRO_CARREGAR_DADOS='Erro ao carregar dados';
    public static ERRO_EXCLUIR_REGISTRO='Erro ao excluir registro';
    public static EXCLUINDO='Excluindo...';
    public static PREENCHA_CAMPOS_OBRIGATORIOS ='Preencha os campos obrigatórios.';
    public static NAO='Não';
    public static REGISTRO_SALVO ='Salvo com Sucesso.';
    public static SALVANDO='Salvando...';
    public static SIM='Sim';
    public static EMAIL_VALIDO='Insira um e-mail válido.';
    public static ENUM_NOTIFICACAO_SIM = 'S';
    public static ENUM_NOTIFICACAO_NAO = 'N';

    /**
     * pessoa jurídica
     */
    public static CADASTRAR_PJ ='Cadastrar Pessoa Jurídica';
    public static CNPJ_INVALIDO ='CNPJ inválido.';
    public static EDITAR_PJ ='Editar Pessoa Jurídica';
    
    /**
     * pessoa representante
     */
    public static REPRESENTANTE_CONFIRMACAO_INCLUSAO = "Deseja inserir este representante?"
    public static REPRESENTANTE_CONFIRMACAO_EXCLUSAO = "Realmente deseja excluir este representante?"
    public static REPRESENTANTE_INSERIR_TELEFONE = "Por favor, preencha os campos DDD e telefone antes de adicionar outro."

}