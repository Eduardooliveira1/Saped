/*==============================================================*/
/* Table: td_Bairro                                             */
/*==============================================================*/
create table dbo.td_Bairro (
   pk_Bairro            bigint               not null,
   fk_Sigla_Uf          char(2)              not null,
   fk_Localidade        bigint,
   no_Bairro            varchar(72),
   no_Abreviado         varchar(36),
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Bairro primary key nonclustered (pk_Bairro)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Bairro
   Esquema: dbo
   Tabelas Relacionadas:  td_Uf, td_Localidade
   Descrição: Armazena os domínios de carga dos bairros.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Bairro'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.td_Bairro.',
   'user', 'dbo', 'table', 'td_Bairro', 'column', 'pk_Bairro'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Bairro, para indicar a relação com a tabela dbo.td_Sigla_Uf.',
   'user', 'dbo', 'table', 'td_Bairro', 'column', 'fk_Sigla_Uf'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Bairro, para indicar a relação com a tabela dbo.td_Localidade.',
   'user', 'dbo', 'table', 'td_Bairro', 'column', 'fk_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome do Bairro.',
   'user', 'dbo', 'table', 'td_Bairro', 'column', 'no_Bairro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome Abreviado do bairro.',
   'user', 'dbo', 'table', 'td_Bairro', 'column', 'no_Abreviado'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Bairro', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Index: idxstd_Bairro_fk_Localidade                           */
/*==============================================================*/
create index idxstd_Bairro_fk_Localidade on dbo.td_Bairro (
fk_Localidade ASC
)
go

/*==============================================================*/
/* Index: idxstd_Bairro_fk_Sigla_Uf                             */
/*==============================================================*/
create index idxstd_Bairro_fk_Sigla_Uf on dbo.td_Bairro (
fk_Sigla_Uf ASC
)
go

/*==============================================================*/
/* Table: td_Bairro_Denominacao                                 */
/*==============================================================*/
create table dbo.td_Bairro_Denominacao (
   fk_Bairro            bigint               not null,
   nr_Ordem             numeric(8),
   no_Denominacao       varchar(72),
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Bairro_Denominacao primary key nonclustered (fk_Bairro)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Bairro_Denominacao
   Esquema: dbo
   Tabelas Relacionadas:  td_Bairro
   Descrição: Armazena os domínios de carga da denominação dos bairros.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Bairro_Denominacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Bairro_Denominacao, para indicar a relação com a tabela dbo.td_Bairro.',
   'user', 'dbo', 'table', 'td_Bairro_Denominacao', 'column', 'fk_Bairro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Ordem.',
   'user', 'dbo', 'table', 'td_Bairro_Denominacao', 'column', 'nr_Ordem'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome da Denominação.',
   'user', 'dbo', 'table', 'td_Bairro_Denominacao', 'column', 'no_Denominacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Bairro_Denominacao', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Table: td_Bairro_Faixa_Cep                                   */
/*==============================================================*/
create table dbo.td_Bairro_Faixa_Cep (
   fk_Bairro            bigint               not null,
   nr_Cep_Inicial       varchar(8),
   nr_Cep_Final         varchar(8),
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Bairro_Faixa_Cep primary key nonclustered (fk_Bairro)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Bairro_Faixa_Cep
   Esquema: dbo
   Tabelas Relacionadas:  td_Bairro
   Descrição: Armazena os domínios de carga das faixas de CEP dos bairros.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Bairro_Faixa_Cep'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Bairro_Faixa_CEP, para indicar a relação com a tabela dbo.td_Bairro.',
   'user', 'dbo', 'table', 'td_Bairro_Faixa_Cep', 'column', 'fk_Bairro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do inicio da faixa de CEP.',
   'user', 'dbo', 'table', 'td_Bairro_Faixa_Cep', 'column', 'nr_Cep_Inicial'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do final da faixa de CEP.',
   'user', 'dbo', 'table', 'td_Bairro_Faixa_Cep', 'column', 'nr_Cep_Final'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Bairro_Faixa_Cep', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Table: td_Caixa_Postal_Comunitaria                           */
/*==============================================================*/
create table dbo.td_Caixa_Postal_Comunitaria (
   pk_Caixa_Postal_Comunitaria bigint        not null,
   fk_Sigla_Uf          char(2)              not null,
   fk_Localidade        bigint,
   no_Caixa_Postal_Comunitaria varchar(72),
   ed_Endereco          varchar(300),
   nr_Cep               varchar(8),
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Cx_Postal_Comunitaria primary key nonclustered (pk_Caixa_Postal_Comunitaria)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Caixa_Postal_Comunitaria
   Esquema: dbo
   Tabelas Relacionadas:  td_Localidade, td_Uf
   Descrição: Armazena os domínios de carga das caixas postais comunitárias.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.td_Caixa_Postal_Comunitaria.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria', 'column', 'pk_Caixa_Postal_Comunitaria'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Caixa_Postal_Comunitaria, para indicar a relação com a tabela dbo.td_Sigla_Uf.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria', 'column', 'fk_Sigla_Uf'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Caixa_Postal_Comunitaria, para indicar a relação com a tabela dbo.td_Localidade.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria', 'column', 'fk_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome da Caixa Postal Comunitária.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria', 'column', 'no_Caixa_Postal_Comunitaria'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do endereço do Endereço.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria', 'column', 'ed_Endereco'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do CEP.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria', 'column', 'nr_Cep'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Comunitaria', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Index: idxstd_Caixa_Postal_Comunitaria_fk_Localidade         */
/*==============================================================*/
create index idxstd_Caixa_Postal_Comunitaria_fk_Localidade on dbo.td_Caixa_Postal_Comunitaria (
fk_Localidade ASC
)
go

/*==============================================================*/
/* Index: idxstd_Caixa_Postal_Comunitaria_fk_Sigla_Uf           */
/*==============================================================*/
create index idxstd_Caixa_Postal_Comunitaria_fk_Sigla_Uf on dbo.td_Caixa_Postal_Comunitaria (
fk_Sigla_Uf ASC
)
go

/*==============================================================*/
/* Table: td_Caixa_Postal_Faixa_Numero                          */
/*==============================================================*/
create table dbo.td_Caixa_Postal_Faixa_Numero (
   fk_Caixa_Postal_Comunitaria bigint               not null,
   nr_Inicial           varchar(6)           null,
   nr_Final             varchar(6)           null,
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Cx_Postal_Faixa_Numero primary key nonclustered (fk_Caixa_Postal_Comunitaria)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Caixa_Postal_Faixa_Numero
   Esquema: dbo
   Tabelas Relacionadas:  td_Caixa_Postal_Comunitaria
   Descrição: Armazena os domínios de carga das faixas de número das caixas postais comunitárias.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Faixa_Numero'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Caixa_Postal_Faixa_Numero, para indicar a relação com a tabela dbo.td_Caixa_Postal_Comunitaria.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Faixa_Numero', 'column', 'fk_Caixa_Postal_Comunitaria'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número Inicial da faixa.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Faixa_Numero', 'column', 'nr_Inicial'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número Final da faixa.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Faixa_Numero', 'column', 'nr_Final'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Caixa_Postal_Faixa_Numero', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Table: td_Localidade                                         */
/*==============================================================*/
create table dbo.td_Localidade (
   pk_Localidade        bigint               not null,
   fk_Sigla_Uf          char(2)              not null,
   no_Localidade        varchar(72)          not null,
   nr_Cep               varchar(8)           not null,
   fl_Localidade        tinyint              null
      constraint cktd_Localidade_fl_Localidade check (fl_Localidade is null or (fl_Localidade in (1,2,3))),
   tp_Localidade        char(1)              null
      constraint cktd_Localidade_tp_Localidade check (tp_Localidade is null or (tp_Localidade in ('D','M','P','R'))),
   fk_Localidade_Subordinada bigint               null,
   no_Abreviado         varchar(36)          null,
   co_Ibge              varchar(7)           null,
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Localidade primary key nonclustered (pk_Localidade)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Localidade
   Esquema: dbo
   Tabelas Relacionadas:  td_Uf
   Descrição: Armazena os domínios de carga das localidades.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.td_Localidade.',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'pk_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Localidade, para indicar a relação com a tabela dbo.td_Sigla_Uf.',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'fk_Sigla_Uf'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome da Localidade.',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'no_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do CEP.',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'nr_Cep'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o status da localidade. Pode assumir os seguintes valores:
   1 - <definição vazia devido a falta dos documentos com os padrões do DNE>
   2 - <definição vazia devido a falta dos documentos com os padrões do DNE>
   3 - <definição vazia devido a falta dos documentos com os padrões do DNE>',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'fl_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o tipo de Localidade. Pode assumir os seguintes valores:
   D - Distrito
   M - Município
   P - Povoado
   R - Região Administrativa',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'tp_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Localidade, para indicar a relação com a tabela dbo.td_Localidade. Relacionamento hierarquico para a localidade subordinada. ',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'fk_Localidade_Subordinada'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome Abreviado da localidade.',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'no_Abreviado'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do código do IBGE.',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'co_Ibge'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Localidade', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Index: idxstd_Localidade_fk_Localidade_Subordinada           */
/*==============================================================*/
create index idxstd_Localidade_fk_Localidade_Subordinada on dbo.td_Localidade (
fk_Localidade_Subordinada ASC
)
go

/*==============================================================*/
/* Index: idxstd_Localidade_fk_Sigla_Uf                         */
/*==============================================================*/
create index idxstd_Localidade_fk_Sigla_Uf on dbo.td_Localidade (
fk_Sigla_Uf ASC
)
go

/*==============================================================*/
/* Table: td_Localidade_Denominacao                             */
/*==============================================================*/
create table dbo.td_Localidade_Denominacao (
   fk_Localidade        bigint               not null,
   nr_Ordem             numeric(8)           null,
   no_Denominacao       varchar(72)          null,
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Localide_Denominacao primary key nonclustered (fk_Localidade)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Localidade_Denominacao
   Esquema: dbo
   Tabelas Relacionadas:  td_Localidade,
   Descrição: Armazena os domínios de carga da denominação das localidades.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Localidade_Denominacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Localidade_Denominacao, para indicar a relação com a tabela dbo.td_Localidade.',
   'user', 'dbo', 'table', 'td_Localidade_Denominacao', 'column', 'fk_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Ordem.',
   'user', 'dbo', 'table', 'td_Localidade_Denominacao', 'column', 'nr_Ordem'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome da Denominação.',
   'user', 'dbo', 'table', 'td_Localidade_Denominacao', 'column', 'no_Denominacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Localidade_Denominacao', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Table: td_Localidade_Faixa_Cep                               */
/*==============================================================*/
create table dbo.td_Localidade_Faixa_Cep (
   fk_Localidade        bigint               not null,
   nr_Cep_Inicial       varchar(8)           null,
   nr_Cep_Final         varchar(8)           null,
   tp_Faixa             char(1)              null
      constraint cktd_Localidade_Faixa_Cep_tp_Faixa check (tp_Faixa is null or (tp_Faixa in ('T','C'))),
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Localidade_Faixa_Cep primary key nonclustered (fk_Localidade)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Localidade_Faixa_Cep
   Esquema: dbo
   Tabelas Relacionadas:  td_Localidade
   Descrição: Armazena os domínios de carga das faixas de CEP das localidades.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Localidade_Faixa_Cep'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Localidade_Faixa_CEP, para indicar a relação com a tabela dbo.td_Localidade.',
   'user', 'dbo', 'table', 'td_Localidade_Faixa_Cep', 'column', 'fk_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do inicio da faixa de CEP.',
   'user', 'dbo', 'table', 'td_Localidade_Faixa_Cep', 'column', 'nr_Cep_Inicial'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do final da faixa de CEP.',
   'user', 'dbo', 'table', 'td_Localidade_Faixa_Cep', 'column', 'nr_Cep_Final'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o tipo de Faixa. Pode assumir os seguintes valores:
   T - Total do Município
   C - Exclusiva da Sede Urbana',
   'user', 'dbo', 'table', 'td_Localidade_Faixa_Cep', 'column', 'tp_Faixa'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Localidade_Faixa_Cep', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Table: td_Logradouro                                         */
/*==============================================================*/
create table dbo.td_Logradouro (
   pk_Logradouro        bigint               not null,
   fk_Sigla_Uf          char(2)              not null,
   fk_Localidade        bigint               not null,
   fk_Bairro_Inicial    bigint               null,
   fk_Bairro_Final      bigint               null,
   no_Logradouro        varchar(100)         not null,
   ds_Complemento       varchar(100)         null,
   nr_Cep               varchar(8)           not null,
   no_Tipo_Logradouro   varchar(36)          null,
   tp_Status_Logradouro char(1)              null,
   no_Abreviado         varchar(150)         null,
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Logradouro primary key nonclustered (pk_Logradouro)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Logradouro
   Esquema: dbo
   Tabelas Relacionadas: td_Bairro, td_Localidade
   Descrição: Armazena os domínios de carga dos logradouros.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.td_Logradouro.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'pk_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Logradouro, para indicar a relação com a tabela dbo.td_Sigla_Uf.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'fk_Sigla_Uf'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Logradouro, para indicar a relação com a tabela dbo.td_Localidade.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'fk_Localidade'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Logradouro, para indicar a relação com a tabela dbo.td_Bairro_Inicial.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'fk_Bairro_Inicial'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Logradouro, para indicar a relação com a tabela dbo.td_Bairro_Final.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'fk_Bairro_Final'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome do Logradouro.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'no_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação da descrição do Complemento.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'ds_Complemento'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do CEP.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'nr_Cep'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome do Tipo de Logradouro.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'no_Tipo_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Define o tipo de Status do Logradouro. Pode assumir os seguintes valores:
   <definição dos valores não identificada devido a falta dos documentos com os padrões do DNE>',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'tp_Status_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome Abreviado do logradouro.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'no_Abreviado'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Logradouro', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Index: idxstd_Logradouro_fk_Bairro_Final                     */
/*==============================================================*/
create index idxstd_Logradouro_fk_Bairro_Final on dbo.td_Logradouro (
fk_Bairro_Final ASC
)
go

/*==============================================================*/
/* Index: idxstd_Logradouro_fk_Bairro_Inicial                   */
/*==============================================================*/
create index idxstd_Logradouro_fk_Bairro_Inicial on dbo.td_Logradouro (
fk_Bairro_Inicial ASC
)
go

/*==============================================================*/
/* Index: idxstd_Logradouro_fk_Localidade                       */
/*==============================================================*/
create index idxstd_Logradouro_fk_Localidade on dbo.td_Logradouro (
fk_Localidade ASC
)
go

/*==============================================================*/
/* Index: idxstd_Logradouro_fk_Sigla_Uf                         */
/*==============================================================*/
create index idxstd_Logradouro_fk_Sigla_Uf on dbo.td_Logradouro (
fk_Sigla_Uf ASC
)
go

/*==============================================================*/
/* Table: td_Logradouro_Denominacao                             */
/*==============================================================*/
create table dbo.td_Logradouro_Denominacao (
   fk_Logradouro        bigint               not null,
   nr_Ordem             numeric(8)           null,
   no_Tipo_Logradouro   varchar(36)          null,
   no_Denominacao       varchar(150)         null,
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Logradouro_Denominacao primary key nonclustered (fk_Logradouro)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Logradouro_Denominacao
   Esquema: dbo
   Tabelas Relacionadas:  td_Logradouro
   Descrição: Armazena os domínios de carga da denominação dos logradouros.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Logradouro_Denominacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave estrangeira da tabela dbo.td_Logradouro_Denominacao, para indicar a relação com a tabela dbo.td_Logradouro.',
   'user', 'dbo', 'table', 'td_Logradouro_Denominacao', 'column', 'fk_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Ordem.',
   'user', 'dbo', 'table', 'td_Logradouro_Denominacao', 'column', 'nr_Ordem'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome do Tipo de Logradouro.',
   'user', 'dbo', 'table', 'td_Logradouro_Denominacao', 'column', 'no_Tipo_Logradouro'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do nome da Denominação.',
   'user', 'dbo', 'table', 'td_Logradouro_Denominacao', 'column', 'no_Denominacao'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Logradouro_Denominacao', 'column', 'nr_Versao_Dne'
go

/*==============================================================*/
/* Table: td_Uf                                                 */
/*==============================================================*/
create table dbo.td_Uf (
   pk_Sigla_Uf          char(2)              not null,
   nr_Cep_Inicial       varchar(8)           null,
   nr_Cep_Final         varchar(8)           null,
   nr_Versao_Dne        numeric(5)           not null,
   constraint pktb_Uf primary key nonclustered (pk_Sigla_Uf)
)
go

execute sp_addextendedproperty 'MS_Description',
   'Nome: dbo.td_Uf
   Esquema: dbo
   Tabelas Relacionadas:
   Descrição: Armazena os domínios de carga das unidades federativas.
   Sigilo da Informação: Não',
   'user', 'dbo', 'table', 'td_Uf'
go

execute sp_addextendedproperty 'MS_Description',
   'Armazena a chave primária da tabela dbo.td_Uf.',
   'user', 'dbo', 'table', 'td_Uf', 'column', 'pk_Sigla_Uf'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do inicio da faixa de CEP.',
   'user', 'dbo', 'table', 'td_Uf', 'column', 'nr_Cep_Inicial'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número do final da faixa de CEP.',
   'user', 'dbo', 'table', 'td_Uf', 'column', 'nr_Cep_Final'
go

execute sp_addextendedproperty 'MS_Description',
   'Identificação do número da Versão do DNE.',
   'user', 'dbo', 'table', 'td_Uf', 'column', 'nr_Versao_Dne'
go

alter table dbo.td_Bairro
   add constraint fktd_Uf_td_Bairro foreign key (fk_Sigla_Uf)
      references dbo.td_Uf (pk_Sigla_Uf)
go

alter table dbo.td_Bairro
   add constraint fktd_Localidade_td_Bairro foreign key (fk_Localidade)
      references dbo.td_Localidade (pk_Localidade)
go

alter table dbo.td_Bairro_Denominacao
   add constraint fktd_Bairro_td_Bairro_Denominacao foreign key (fk_Bairro)
      references dbo.td_Bairro (pk_Bairro)
go

alter table dbo.td_Bairro_Faixa_Cep
   add constraint fktd_Bairro_td_Bairro_Faixa_Cep foreign key (fk_Bairro)
      references dbo.td_Bairro (pk_Bairro)
go

alter table dbo.td_Caixa_Postal_Comunitaria
   add constraint fktd_Localidade_td_Caixa_Postal_Comunitaria foreign key (fk_Localidade)
      references dbo.td_Localidade (pk_Localidade)
go

alter table dbo.td_Caixa_Postal_Comunitaria
   add constraint fktd_Uf_td_Caixa_Postal_Comunitaria foreign key (fk_Sigla_Uf)
      references dbo.td_Uf (pk_Sigla_Uf)
go

alter table dbo.td_Caixa_Postal_Faixa_Numero
   add constraint fktd_Caixa_Postal_Comunitaria_td_Caixa_Postal_Faixa_Numero foreign key (fk_Caixa_Postal_Comunitaria)
      references dbo.td_Caixa_Postal_Comunitaria (pk_Caixa_Postal_Comunitaria)
go

alter table dbo.td_Localidade
   add constraint fktd_Localidade_td_Localidade foreign key (fk_Localidade_Subordinada)
      references dbo.td_Localidade (pk_Localidade)
go

alter table dbo.td_Localidade
   add constraint fktd_Uf_td_Localidade foreign key (fk_Sigla_Uf)
      references dbo.td_Uf (pk_Sigla_Uf)
go

alter table dbo.td_Localidade_Denominacao
   add constraint fktd_Localidade_td_Localidade_Denominacao foreign key (fk_Localidade)
      references dbo.td_Localidade (pk_Localidade)
go

alter table dbo.td_Localidade_Faixa_Cep
   add constraint fktd_Localidade_td_Localidade_Faixa_Cep foreign key (fk_Localidade)
      references dbo.td_Localidade (pk_Localidade)
go

alter table dbo.td_Logradouro
   add constraint fktd_Localidade_td_Logradouro foreign key (fk_Localidade)
      references dbo.td_Localidade (pk_Localidade)
go

alter table dbo.td_Logradouro
   add constraint fktd_Bairro_Inicial_td_Logradouro foreign key (fk_Bairro_Final)
      references dbo.td_Bairro (pk_Bairro)
go

alter table dbo.td_Logradouro
   add constraint fktd_Bairro_Final_td_Logradouro foreign key (fk_Bairro_Inicial)
      references dbo.td_Bairro (pk_Bairro)
go

alter table dbo.td_Logradouro
   add constraint fktd_Uf_td_Logradouro foreign key (fk_Sigla_Uf)
      references dbo.td_Uf (pk_Sigla_Uf)
go

alter table dbo.td_Logradouro_Denominacao
   add constraint fktd_Logradouro_td_Logradouro_Denominacao foreign key (fk_Logradouro)
      references dbo.td_Logradouro (pk_Logradouro)
go
