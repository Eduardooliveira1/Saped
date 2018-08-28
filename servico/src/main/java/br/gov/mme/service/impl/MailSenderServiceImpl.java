package br.gov.mme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.gov.mme.service.MailSenderService;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
public class MailSenderServiceImpl implements MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    @Override
    @Async
    public void enviar(String email, String assunto, String mensagem) {
        SimpleMailMessage simpleEmail = new SimpleMailMessage();

        simpleEmail.setTo(email);
        simpleEmail.setFrom(remetente);
        simpleEmail.setSubject(assunto);
        simpleEmail.setText(mensagem);

        mailSender.send(simpleEmail);

    }

}
