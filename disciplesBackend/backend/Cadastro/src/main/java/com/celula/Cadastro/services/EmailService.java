package com.celula.Cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    public void enviarEmailContatoLider(
        String emailLider,
        String nomeCelula,
        String nomeInteressado,
        String emailInteressado,
        String mensagem
    ) {
        String assunto = "Novo interessado na célula " + nomeCelula;

        String corpo = """
            Olá!

            Você recebeu um novo contato para a célula %s.

            Nome do interessado: %s
            E-mail do interessado: %s

            Mensagem:
            %s

            Deus abençoe! :)
            """.formatted(
                nomeCelula,
                nomeInteressado,
                emailInteressado,
                mensagem
            );

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(emailLider);
        mailMessage.setSubject(assunto);
        mailMessage.setText(corpo);

        try {
            mailSender.send(mailMessage);
            System.out.println("E-mail de contato enviado para: " + emailLider);
        } catch (Exception e) {
            System.out.println("ERRO AO ENVIAR E-MAIL:");
            e.printStackTrace();
        }
    }
}
