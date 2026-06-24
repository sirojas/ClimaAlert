package ar.edu.utn.frba.dds.climalert.services.notificador.impl;

import ar.edu.utn.frba.dds.climalert.services.mailSender.IMailSender;
import ar.edu.utn.frba.dds.climalert.services.notificador.INotificador;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class Notificador implements INotificador {

  private final List<String> destinatarios = new ArrayList<>(List.of(
      "admin@clima.com",
      "emergencias@clima.com",
      "meteorologia@clima.com"
  ));

  private final IMailSender sender;

  public Notificador(IMailSender sender){
    this.sender = sender;
  }
  @Override
  public void enviarMensaje(String mensaje) {
    for(String destinatario : this.destinatarios){
      this.sender.enviar(mensaje,destinatario);
      System.out.println("Enviando alerta a: " + destinatario);
    }
  }
}
