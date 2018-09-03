package br.gov.mme.service.dto;

import br.gov.mme.util.SapedUtil;

import java.time.LocalDateTime;
import java.util.List;

public class NotificacaoCadastroDTO {

    private Long id;

    private String assunto;

    private String conteudo;

    private List<ComunicacaoRepresentantelistaDTO> representantes;

    private LocalDateTime dataCadastro;

    public NotificacaoCadastroDTO(Long id,
                                  String assunto,
                                  String conteudo,
                                  LocalDateTime dataCadastro) {
        this.id = id;
        this.assunto = assunto;
        this.conteudo = conteudo;
        this.dataCadastro = dataCadastro;
    }

    public NotificacaoCadastroDTO() {
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<ComunicacaoRepresentantelistaDTO> getRepresentantes() {
        return SapedUtil.instanciarLista(representantes);
    }

    public void setRepresentantes(List<ComunicacaoRepresentantelistaDTO> representantes) {
        this.representantes =  SapedUtil.instanciarLista(representantes);
    }

}
