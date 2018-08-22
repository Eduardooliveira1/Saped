package br.gov.mme.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailUtil {


	public void enviar(String email, String assunto, String mensagem, JavaMailSender mailSender) {
		SimpleMailMessage simpleEmail = new SimpleMailMessage();

		simpleEmail.setTo(email);
		simpleEmail.setFrom("sapedteste@gmail.com");
		simpleEmail.setSubject(assunto);
		simpleEmail.setText(mensagem);

		mailSender.send(simpleEmail);

	}
}
