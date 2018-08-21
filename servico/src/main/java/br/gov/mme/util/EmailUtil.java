package br.gov.mme.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailUtil {
	@Autowired
	private JavaMailSender mailSender;

	public void enviar(String email, String assunto, String mensagem) {
		SimpleMailMessage simpleEmail = new SimpleMailMessage();

		simpleEmail.setTo(email);

		simpleEmail.setSubject(assunto);
		simpleEmail.setText(mensagem);

		mailSender.send(simpleEmail);

	}
}
