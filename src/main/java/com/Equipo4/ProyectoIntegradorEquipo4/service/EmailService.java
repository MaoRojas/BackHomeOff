package com.Equipo4.ProyectoIntegradorEquipo4.service;


import com.Equipo4.ProyectoIntegradorEquipo4.entities.MailHtml;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailRegister(String toEmail) {

        MimeMessage message= mailSender.createMimeMessage();
        String contenido = MailHtml.correoRegistro;

        try {
            message.setSubject("Bienvenido a HomeOff tu oficina donde quieras");
            MimeMessageHelper helper= new MimeMessageHelper(message, true);
            helper.setFrom("homeoff.noreply@gmail.com");
            helper.setTo(toEmail);
            helper.setText(contenido, true);
            mailSender.send(message);

        }catch (MessagingException e) {
                throw new RuntimeException(e);
        }

        System.out.println("Correo enviado con exito...");
    }

}