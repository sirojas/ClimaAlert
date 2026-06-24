package ar.edu.utn.frba.dds.climalert.services.mailSender;

public interface IMailSender {

  void enviar(String mensaje, String destino);
}
