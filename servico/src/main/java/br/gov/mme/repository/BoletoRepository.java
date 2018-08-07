package br.gov.mme.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mme.domain.Boleto;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long>, JpaSpecificationExecutor<Boleto> {

    @Query("from Boleto" + 
            " where pessoaJuridica.id in (:#{#filtro.idsPessoasJuridicas})" + 
                 " or valorBoleto = :#{#filtro.valor}" + 
                 " or mesReferencia = :#{#filtro.mesReferencia}" + 
                 " or dataVencimento = :#{#filtro.dataVencimento}" + 
                 " or tpBoleto = :#{#filtro.tpBoleto}")
    Set<Boleto> listarPagamentosRelatorioExport(@Param("filtro") BoletoRelatorioPagamentoFiltroDTO filtro);

}