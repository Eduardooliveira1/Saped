/*==============================================================*/
/* Table: ta_Notificacao_Pessoa_Juridica                        */
/*==============================================================*/
create table dbo.ta_Notificacao_Pessoa_Juridica (
   fk_Notificacao       bigint               not null,
   fk_Pessoa_Juridica   bigint               not null,
   constraint fktb_Notificacao_Pessoa_Juridica primary key nonclustered (fk_Notificacao, fk_Pessoa_Juridica)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.ta_Notificacao_Pessoa_Juridica
   Esquema: dbo
   Tabelas Relacionadas:  tb_Notificacao, tb_Pessoa_Juridica
   Descrição: Armazena a associação de notificação com pessoas jurídicas.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'ta_Notificacao_Pessoa_Juridica'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.ta_Notificacao_Pessoa_Juridica, para indicar a relação com a tabela dbo.tb_Notificacao.',
   'user', 'dbo', 'table', 'ta_Notificacao_Pessoa_Juridica', 'column', 'fk_Notificacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.ta_Notificacao_Pessoa_Juridica, para indicar a relação com a tabela dbo.tb_Pessoa_Juridica.',
   'user', 'dbo', 'table', 'ta_Notificacao_Pessoa_Juridica', 'column', 'fk_Pessoa_Juridica'
go

/*==============================================================*/
/* Index: idxsta_Notificacao_Pessoa_Juridica_fk_Notificacao     */
/*==============================================================*/
create index idxsta_Notificacao_Pessoa_Juridica_fk_Notificacao on dbo.ta_Notificacao_Pessoa_Juridica (
fk_Notificacao ASC
)
go

/*==============================================================*/
/* Index: idxsta_Notificacao_Pessoa_Juridica_fk_Pessoa_Juridica */
/*==============================================================*/
create index idxsta_Notificacao_Pessoa_Juridica_fk_Pessoa_Juridica on dbo.ta_Notificacao_Pessoa_Juridica (
fk_Pessoa_Juridica ASC
)
go

/*==============================================================*/
/* Table: tb_Boleto                                             */
/*==============================================================*/
create table dbo.tb_Boleto (
   pk_Boleto              bigint identity(1,1) not null,
   fk_Boleto_Adiantamento bigint,
   fk_Pessoa_Juridica     bigint               not null,
   mm_Referencia          numeric(2),
   aa_Referencia          numeric(4),
   tp_Boleto              char(2)              not null default 'NO'
      constraint cktb_Boleto_tp_Boleto check (tp_Boleto in ('NO','SV','AD')),
   nr_Nosso_Numero        numeric(17)          not null,
   dv_Nosso_Numero        numeric(1),
   vl_Boleto              numeric(20,2)        not null default 0,
   vl_Juros				  numeric(20,2),
   vl_Multa				  numeric(20,2),
   dt_Vencimento          date                 not null,
   constraint pktb_Boleto primary key nonclustered (pk_Boleto)
)
go

alter table dbo.tb_Boleto add constraint cktb_Boleto_Referencia
check ((tp_Boleto in ('NO','SV') and mm_Referencia is not null and aa_Referencia is not null) or (tp_Boleto = 'AD' and mm_Referencia is null and aa_Referencia is null));

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Boleto
   Esquema: dbo
   Tabelas Relacionadas:  tb_Pessoa_Juridica
   Descrição: Armazena o boleto.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Boleto'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.tb_Boleto, incrementada automaticamente pelo banco.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'pk_Boleto'
go

execute sp_addextendedproperty 'MS_Description',
   'Identifica o boleto de adiantamento referenciado. Faz parte de um relacionamento recursivo. Armazena a chave estrangeira da tabela dbo.tb_Boleto, para indicar a relação com a tabela dbo.tb_Boleto.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'fk_Boleto_Adiantamento'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.tb_Boleto, para indicar a relação com a tabela dbo.tb_Pessoa_Juridica.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'fk_Pessoa_Juridica'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do mês da referência do boleto. Valor obrigatório para os boletos com exceção dos boletos de adiantamento.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'mm_Referencia'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do ano da referência do boleto. Valor obrigatório para os boletos com exceção dos boletos de adiantamento.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'aa_Referencia'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o tipo de Boleto. Pode assumir os seguintes valores:
NO - NORMAL
SV - SEGUNDA VIA
AD - ADIANTAMENTO',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'tp_Boleto'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do Nosso Número.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'nr_Nosso_Numero'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do digito verificador do Nosso Número do boleto.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'dv_Nosso_Numero'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação o valor do Boleto.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'vl_Boleto'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação o valor do juros no boleto.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'vl_Juros'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação o valor da multa no boleto.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'vl_Multa'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da data do Vencimento.',
   'user', 'dbo', 'table', 'tb_Boleto', 'column', 'dt_Vencimento'
go

/*==============================================================*/
/* Index: idxstb_Boleto_fk_Pessoa_Juridica                      */
/*==============================================================*/
create index idxstb_Boleto_fk_Pessoa_Juridica on dbo.tb_Boleto (
fk_Pessoa_Juridica ASC
)
go

/*==============================================================*/
/* Index: idxstb_Boleto_Referencia_fk_Boleto                    */
/*==============================================================*/
create index idxstb_Boleto_fk_Boleto_Adiantamento on dbo.tb_Boleto (
fk_Boleto_Adiantamento ASC
)
go

/*==============================================================*/
/* Table: tb_Pessoa_Endereco                                    */
/*==============================================================*/
create table dbo.tb_Pessoa_Endereco (
   fk_Pessoa_Endereco   bigint identity(1,1) not null,
   fk_Logradouro        bigint               not null,
   tp_Endereco          char(2)              not null
      constraint cktb_Pessoa_Endereco_tp_Endereco check (tp_Endereco in ('CB','CM')),
   ed_Logradouro        varchar(300)         not null,
   ds_Complemento       varchar(100),
   constraint pktb_Pessoa_Endereco primary key nonclustered (fk_Pessoa_Endereco)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Endereco
   Esquema: dbo
   Tabelas Relacionadas:  tb_Pessoa, td_Logradouro
   Descrição: Armazena os endereços da pessoas.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Pessoa_Endereco'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.tb_Pessoa_Endereco, incrementada automaticamente pelo banco. Armazena a chave estrangeira da tabela dbo.tb_Pessoa_Endereco, para indicar a relação com a tabela dbo.tb_Pessoa.',
   'user', 'dbo', 'table', 'tb_Pessoa_Endereco', 'column', 'fk_Pessoa_Endereco'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.tb_Endereco, para indicar a relação com a tabela dbo.td_Logradouro.',
   'user', 'dbo', 'table', 'tb_Pessoa_Endereco', 'column', 'fk_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o tipo de Endereço. Pode assumir os seguintes valores:
CB - Cobrança
CM - Comercial',
   'user', 'dbo', 'table', 'tb_Pessoa_Endereco', 'column', 'tp_Endereco'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do endereço do Logradouro.',
   'user', 'dbo', 'table', 'tb_Pessoa_Endereco', 'column', 'ed_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da descrição do Complemento.',
   'user', 'dbo', 'table', 'tb_Pessoa_Endereco', 'column', 'ds_Complemento'
go

/*==============================================================*/
/* Index: idxstb_Endereco_fk_Logradouro                         */
/*==============================================================*/
create index idxstb_Pessoa_Endereco_fk_Logradouro on dbo.tb_Pessoa_Endereco (
fk_Logradouro ASC
)
go

/*==============================================================*/
/* Index: idxstb_Endereco_fk_Pessoa                             */
/*==============================================================*/
create index idxstb_Pessoa_Endereco_fk_Pessoa on dbo.tb_Pessoa_Endereco (
fk_Pessoa_Endereco ASC
)
go

/*==============================================================*/
/* Table: td_Feriado_Nacional                                   */
/*==============================================================*/
create table dbo.td_Feriado_Nacional (
   pk_Feriado_Nacional  bigint identity(1,1) not null,
   dt_Feriado			date not null,
   ds_Feriado			varchar(100),
   constraint pkFeriado_Nacional primary key nonclustered (pk_Feriado_Nacional)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Feriado_Nacional
   Esquema: dbo
   Tabelas Relacionadas:
   Descrição: Armazena a carga anual de feriados nacionais. A carga devera ser executada por rotinas internas do MME anualmente.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Feriado_Nacional'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.td_Feriado_Nacional, incrementada automaticamente pelo banco.',
   'user', 'dbo', 'table', 'td_Feriado_Nacional', 'column', 'pk_Feriado_Nacional'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a data do feriado.',
   'user', 'dbo', 'table', 'td_Feriado_Nacional', 'column', 'dt_Feriado'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a descrição do feriado.',
   'user', 'dbo', 'table', 'td_Feriado_Nacional', 'column', 'ds_Feriado'
go

/*==============================================================*/
/* Table: tb_Notificacao                                        */
/*==============================================================*/
create table dbo.tb_Notificacao (
   pk_Notificacao       bigint identity(1,1) not null,
   ds_Assunto           varchar(100)         not null,
   ds_Mensagem          varchar(2000)        not null,
   dh_Envio             datetime             not null default sysdatetime(),
   constraint pktb_Notificacao primary key nonclustered (pk_Notificacao)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Notificacao
   Esquema: dbo
   Tabelas Relacionadas:
   Descrição: Armazena as notificações.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Notificacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.tb_Notificacao, incrementada automaticamente pelo banco.',
   'user', 'dbo', 'table', 'tb_Notificacao', 'column', 'pk_Notificacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da descrição do Assunto.',
   'user', 'dbo', 'table', 'tb_Notificacao', 'column', 'ds_Assunto'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da descrição do Mensagem.',
   'user', 'dbo', 'table', 'tb_Notificacao', 'column', 'ds_Mensagem'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da data e hora do Envio.',
   'user', 'dbo', 'table', 'tb_Notificacao', 'column', 'dh_Envio'
go

/*==============================================================*/
/* Table: tb_Pessoa                                             */
/*==============================================================*/
create table dbo.tb_Pessoa (
   pk_Pessoa            bigint identity(1,1) not null,
   em_Pessoa            varchar(70),
   fl_Status			char(1)				 not null default 'S'
	constraint cktb_Pessoa_fl_Status check (fl_Status in ('S','N')),
   dt_Cadastro          date                 not null default GETDATE(),
   constraint pktb_Pessoa primary key nonclustered (pk_Pessoa)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Pessoa
   Esquema: dbo
   Tabelas Relacionadas:
   Descrição: Armazena as pessoas.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Pessoa'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.tb_Pessoa, incrementada automaticamente pelo banco.',
   'user', 'dbo', 'table', 'tb_Pessoa', 'column', 'pk_Pessoa'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do e-mail da Pessoa.',
   'user', 'dbo', 'table', 'tb_Pessoa', 'column', 'em_Pessoa'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o tipo de Status do boleto. Pode assumir os seguintes valores:
S - Sim, ativo
N - Não, inativo',
   'user', 'dbo', 'table', 'tb_Pessoa', 'column', 'fl_Status'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da data de cadastro da pessoa no sistema.',
   'user', 'dbo', 'table', 'tb_Pessoa', 'column', 'dt_Cadastro'
go

/*==============================================================*/
/* Table: tb_Pessoa_Juridica                                    */
/*==============================================================*/
create table dbo.tb_Pessoa_Juridica (
   fk_Pessoa_Juridica   bigint               not null,
   co_Cnpj              varchar(14)          not null,
   no_Fantasia          varchar(100)         not null,
   no_Razao_Social      varchar(100)         not null,
   sg_Pessoa_Juridica   varchar(20)          not null,
   ds_Senha_Acesso      varchar(300),
   constraint pktb_Pessoa_Juridica primary key nonclustered (fk_Pessoa_Juridica),
   constraint uktb_Pessoa_Juridica_co_Cnpj unique (co_Cnpj)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Pessoa_Juridica
   Esquema: dbo
   Tabelas Relacionadas:  tb_Pessoa
   Descrição: Armazena as pessoas jurídicas.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Pessoa_Juridica'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.tb_Pessoa_Juridica, para indicar a relação com a tabela dbo.tb_Pessoa_Juridica.',
   'user', 'dbo', 'table', 'tb_Pessoa_Juridica', 'column', 'fk_Pessoa_Juridica'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do código do CNPJ.',
   'user', 'dbo', 'table', 'tb_Pessoa_Juridica', 'column', 'co_Cnpj'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome Fantasia.',
   'user', 'dbo', 'table', 'tb_Pessoa_Juridica', 'column', 'no_Fantasia'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome da Razão Social.',
   'user', 'dbo', 'table', 'tb_Pessoa_Juridica', 'column', 'no_Razao_Social'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da sigla do Pessoa_Juridica.',
   'user', 'dbo', 'table', 'tb_Pessoa_Juridica', 'column', 'sg_Pessoa_Juridica'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da Senha de acesso.',
   'user', 'dbo', 'table', 'tb_Pessoa_Juridica', 'column', 'ds_Senha_Acesso'
go

/*==============================================================*/
/* Table: tb_Pessoa_Representante                               */
/*==============================================================*/
create table dbo.tb_Pessoa_Representante (
   fk_Pessoa_Representante bigint            not null,
   fk_Pessoa_Juridica   bigint               not null,
   no_Representante     varchar(100)         not null,
   no_Cargo             varchar(80)          not null,
   fl_Notificacao       char(1)              not null default 'S'
      constraint ck_Pessoa_Representante_fl_Notificacao check (fl_Notificacao in ('S','N')),
   constraint pkta_Pessoa_Juridica_Representante primary key nonclustered (fk_Pessoa_Representante)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Pessoa_Representante
   Esquema: dbo
   Tabelas Relacionadas:  tb_Pessoa, tb_Pessoa_Juridica
   Descrição: Armazena os representantes.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Pessoa_Representante'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.tb_Pessoa_Representante, para indicar a relação com a tabela dbo.tb_Pessoa_Representante.',
   'user', 'dbo', 'table', 'tb_Pessoa_Representante', 'column', 'fk_Pessoa_Representante'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.tb_Pessoa_Representante, para indicar a relação com a tabela dbo.tb_Pessoa_Juridica.',
   'user', 'dbo', 'table', 'tb_Pessoa_Representante', 'column', 'fk_Pessoa_Juridica'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome do Representante.',
   'user', 'dbo', 'table', 'tb_Pessoa_Representante', 'column', 'no_Representante'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome do Cargo.',
   'user', 'dbo', 'table', 'tb_Pessoa_Representante', 'column', 'no_Cargo'
go

execute sp_addextendedproperty 'MS_Description',
   'Define se o representante receberá notificações. Pode assumir os seguintes valores:
S - Sim, recebe
N - Não, não recebe',
   'user', 'dbo', 'table', 'tb_Pessoa_Representante', 'column', 'fl_Notificacao'
go

/*==============================================================*/
/* Index: idxstb_Pessoa_Representante_fk_Pessoa_Juridica        */
/*==============================================================*/
create index idxstb_Pessoa_Representante_fk_Pessoa_Juridica on dbo.tb_Pessoa_Representante (
fk_Pessoa_Juridica ASC
)
go

/*==============================================================*/
/* Table: tb_Status_Boleto                                      */
/*==============================================================*/
create table dbo.tb_Status_Boleto (
   pk_Status_Boleto     bigint identity(1,1) not null,
   fk_Boleto            bigint               not null,
   tp_Status            char(2)              not null default 'AV'
      constraint cktb_Status_Boleto_tp_Status check (tp_Status in ('AV','AD','EM','PG','VE')),
   fl_Situacao_Bancaria	char(2)
	  constraint cktb_Status_Boleto_fl_Situacao_Banco check (fl_Situacao_Bancaria is null or fl_Situacao_Bancaria in ('00','01','02','03','10','11','21','22','25','26','27','28','29','30','99')),
   dh_Status            datetime             not null default sysdatetime(),
   constraint pktb_Status_Boleto primary key nonclustered (pk_Status_Boleto)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Status_Boleto
   Esquema: dbo
   Tabelas Relacionadas:  tb_Boleto
   Descrição: Armazena os status dos boletos.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Status_Boleto'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.tb_Status_Boleto, incrementada automaticamente pelo banco.',
   'user', 'dbo', 'table', 'tb_Status_Boleto', 'column', 'pk_Status_Boleto'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.tb_Status_Boleto, para indicar a relação com a tabela dbo.tb_Boleto.',
   'user', 'dbo', 'table', 'tb_Status_Boleto', 'column', 'fk_Boleto'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o tipo de Status do boleto. Pode assumir os seguintes valores:
AV - À VENCER
AD - ADIANTADO
EM - EMITIDO
PG - PAGO
VE - VENCIDO',
   'user', 'dbo', 'table', 'tb_Status_Boleto', 'column', 'tp_Status'
go

execute sp_addextendedproperty 'MS_Description',
   'Indica a situação do pagamento do boleto em retorno do banco. Valores possíveis:
00  pagamento efetuado
01  pagamento não autorizado/transação recusada
02  erro no processamento da consulta
03  pagamento não localizado
10  campo idConv inválido ou nulo
11  valor informado é inválido, nulo ou não confere com o valor registrado
21  Pagamento Web não autorizado
22  erro no processamento da consulta
23  erro no processamento da consulta
24 - Convênio não cadastrado
25 - Convênio não ativo
26 - Convênio não permite debito em conta
27 - Serviço inválido
28 - Boleto emitido
29  pagamento não efetuado
30  erro no processamento da consulta
99  Operação cancelada pelo cliente',
   'user', 'dbo', 'table', 'tb_Status_Boleto', 'column', 'fl_Situacao_Bancaria'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da data e hora do Status.',
   'user', 'dbo', 'table', 'tb_Status_Boleto', 'column', 'dh_Status'
go

/*==============================================================*/
/* Index: idxstb_Status_Boleto_fk_Boleto                        */
/*==============================================================*/
create index idxstb_Status_Boleto_fk_Boleto on dbo.tb_Status_Boleto (
fk_Boleto ASC
)
go

/*==============================================================*/
/* Table: tb_Telefone_Representante                             */
/*==============================================================*/
create table dbo.tb_Telefone_Representante (
   pk_Telefone_Representante        bigint identity(1,1) not null,
   fk_Pessoa_Representante			bigint				 not null,
   nr_Ddd							numeric(2)           not null,
   nr_Telefone						numeric(9)           not null,
   fl_Status						char(1)              not null default 'S'
      constraint ck_tb_Telefone_Representante_fl_Status check (fl_Status in ('S','N')),
   constraint pktb_Telefone_Representante primary key nonclustered (pk_Telefone_Representante)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tb_Telefone_Representante
   Esquema: dbo
   Tabelas Relacionadas:
   Descrição: Armazena os telefones para os Representantes.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tb_Telefone_Representante'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.tb_Telefone_Representante, incrementada automaticamente pelo banco.',
   'user', 'dbo', 'table', 'tb_Telefone_Representante', 'column', 'pk_Telefone_Representante'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.tb_Telefone_Representante, para indicar a relação com a tabela dbo.tb_Pessoa_Representante.',
   'user', 'dbo', 'table', 'tb_Telefone_Representante', 'column', 'fk_Pessoa_Representante'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do DDD.',
   'user', 'dbo', 'table', 'tb_Telefone_Representante', 'column', 'nr_Ddd'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do Telefone.',
   'user', 'dbo', 'table', 'tb_Telefone_Representante', 'column', 'nr_Telefone'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o status do registro. Pode assumir os seguintes valores:
S - Ativo
N - Inativo',
   'user', 'dbo', 'table', 'tb_Telefone_Representante', 'column', 'fl_Status'
go

/*=====================================================================*/
/* Index: idxstb_Telefone_Representante_fk_Pessoa_Representante        */
/*=====================================================================*/
create index idxstb_Telefone_Representante_fk_Pessoa_Representante on dbo.tb_Telefone_Representante (
fk_Pessoa_Representante ASC
)
go

/*==============================================================*/
/* Table: tl_Vpn_Status_Boleto                                  */
/*==============================================================*/
create table dbo.tl_Vpn_Retorno_Bancario (
	pk_Vpn_Retorno_Bancario     bigint identity(1,1) not null,
	ds_Registro					varchar(1000) not null,
	dh_Registro					datetime default sysdatetime(),
	constraint pktl_Vpn_Status_Boleto primary key (pk_Vpn_Retorno_Bancario)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.tl_Vpn_Status_Boleto
   Esquema: dbo
   Tabelas Relacionadas:
   Descrição: Armazena o log de comunicação com a VPN do Banco do Brasil para a atualização de status dos boletos.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'tl_Vpn_Retorno_Bancario'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.pk_Vpn_Status_Boleto, incrementada automaticamente pelo banco.',
   'user', 'dbo', 'table', 'tl_Vpn_Retorno_Bancario', 'column', 'pk_Vpn_Retorno_Bancario'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a descrição registrada pelo log.',
   'user', 'dbo', 'table', 'tl_Vpn_Retorno_Bancario', 'column', 'ds_Registro'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a data e hora do registro do log.',
   'user', 'dbo', 'table', 'tl_Vpn_Retorno_Bancario', 'column', 'dh_Registro'
go


/*=====================================================================*/
/* Index                                                               */
/*=====================================================================*/

create index idxttb_Pessoa_Juridica_co_Cnpj on dbo.tb_Pessoa_Juridica (co_Cnpj)
go
create index idxttb_Pessoa_Juridica_sg_Pessoa_Juridica on dbo.tb_Pessoa_Juridica (sg_Pessoa_Juridica)
go
create index idxttb_Pessoa_Juridica_no_Fantasia on dbo.tb_Pessoa_Juridica (no_Fantasia)
go
create index idxttb_Pessoa_Juridica_no_Razao_Social on dbo.tb_Pessoa_Juridica (no_Razao_Social)
go
create index idxstb_Boleto_dt_Vencimento on dbo.tb_Boleto (dt_Vencimento)
go
create index idxstb_Boleto_vl_Boleto on dbo.tb_Boleto (vl_Boleto)
go
create index idxstb_Boleto_nr_Nosso_Numero on dbo.tb_Boleto (nr_Nosso_Numero)
go
create index idxctb_Boleto_000001 on dbo.tb_Boleto (fk_Pessoa_Juridica,mm_Referencia,aa_Referencia)
go
create index idxstb_Boleto_aa_Referencia on dbo.tb_Boleto (aa_Referencia)
go
create index idxttb_Status_Boleto_tp_Status on dbo.tb_Status_Boleto (tp_Status)
go
create index idxstd_Localidade_nr_Cep on dbo.td_Localidade (nr_Cep)
go

/*=====================================================================*/
/* Foreign key                                                         */
/*=====================================================================*/

alter table dbo.ta_Notificacao_Pessoa_Juridica
   add constraint fktb_Notificacao_ta_Notificacao_Pessoa_Juridica foreign key (fk_Notificacao)
      references dbo.tb_Notificacao (pk_Notificacao)
go

alter table dbo.ta_Notificacao_Pessoa_Juridica
   add constraint fktb_Pessoa_Juridica_ta_Notificacao_Pessoa_Juridica foreign key (fk_Pessoa_Juridica)
      references dbo.tb_Pessoa_Juridica (fk_Pessoa_Juridica)
go

alter table dbo.tb_Telefone_Representante
   add constraint fktb_Pessoa_Representante_tb_Telefone_Representante foreign key (fk_Pessoa_Representante)
      references dbo.tb_Pessoa_Representante (fk_Pessoa_Representante)
go

alter table dbo.tb_Boleto
   add constraint fktb_Pessoa_Juridica_tb_Boleto foreign key (fk_Pessoa_Juridica)
      references dbo.tb_Pessoa_Juridica (fk_Pessoa_Juridica)
go

alter table dbo.tb_Boleto
   add constraint fktb_Boleto_tb_Boleto_Adiantamento foreign key (fk_Boleto_Adiantamento)
      references dbo.tb_Boleto (pk_Boleto)
go

alter table dbo.tb_Pessoa_Endereco
   add constraint fktb_Pessoa_tb_Pessoa_Endereco foreign key (fk_Pessoa_Endereco)
      references dbo.tb_Pessoa (pk_Pessoa)
go

alter table dbo.tb_Pessoa_Endereco
   add constraint fktd_Logradouro_tb_Pessoa_Endereco foreign key (fk_Logradouro)
      references dbo.td_Logradouro (pk_Logradouro)
go

alter table dbo.tb_Pessoa_Juridica
   add constraint fktb_Pessoa_tb_Pessoa_Juridica foreign key (fk_Pessoa_Juridica)
      references dbo.tb_Pessoa (pk_Pessoa)
go

alter table dbo.tb_Pessoa_Representante
   add constraint fktb_Pessoa_Juridica_tb_Pessoa_Representante foreign key (fk_Pessoa_Juridica)
      references dbo.tb_Pessoa_Juridica (fk_Pessoa_Juridica)
go

alter table dbo.tb_Pessoa_Representante
   add constraint fktb_Pessoa_tb_Pessoa_Representante foreign key (fk_Pessoa_Representante)
      references dbo.tb_Pessoa (pk_Pessoa)
go

alter table dbo.tb_Status_Boleto
   add constraint fktb_Boleto_tb_Status_Boleto foreign key (fk_Boleto)
      references dbo.tb_Boleto (pk_Boleto)
go
