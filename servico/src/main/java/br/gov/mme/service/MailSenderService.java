package br.gov.mme.service;

public interface MailSenderService {

    void enviar(String email, String assunto, String mensagem);

}
