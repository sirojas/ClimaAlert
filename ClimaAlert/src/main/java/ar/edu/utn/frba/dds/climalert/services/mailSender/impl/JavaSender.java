package ar.edu.utn.frba.dds.climalert.services.mailSender.impl;

import ar.edu.utn.frba.dds.climalert.services.mailSender.IMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class JavaSender implements IMailSender {

  @Value("${spring.mail.username}")
  private String mailUsername;
  private final JavaMailSender javaMailSender;

  public JavaSender(JavaMailSender javaMailSender ){
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void enviar(String mensaje, String destino) {
    try{

      SimpleMailMessage mail = new SimpleMailMessage();

      mail.setFrom(mailUsername);
      mail.setTo(destino);
      mail.setSubject("Notificacion Climalert");
      mail.setText(mensaje);

      javaMailSender.send(mail);
    } catch (MailException e){
      System.out.println("Error enviando mail a " + destino + ": " + e.getMessage());
    }
  }
}
