package ar.edu.utn.frba.dds.climalert.services.clima.impl;

import ar.edu.utn.frba.dds.climalert.dto.WheaterApiResponse;
import ar.edu.utn.frba.dds.climalert.exceptions.NoClimaException;
import ar.edu.utn.frba.dds.climalert.models.*;
import ar.edu.utn.frba.dds.climalert.repositories.clima.IClimaRepository;
import ar.edu.utn.frba.dds.climalert.services.clima.IClimaService;
import ar.edu.utn.frba.dds.climalert.services.notificador.INotificador;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ClimaService implements IClimaService {

  private final IClimaRepository climaRepository;
  private final INotificador notificador;

  public ClimaService(IClimaRepository climaRepository, INotificador notificador){
    this.climaRepository = climaRepository;
    this.notificador = notificador;
  }

  @Override
  public void guardarNuevoClima(WheaterApiResponse climaDto) {
    Clima climaNuevo = this.parsearClima(climaDto);
    this.climaRepository.guardar(climaNuevo);
  }

  private Clima parsearClima(WheaterApiResponse climaDto) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    Condicion condicion = new Condicion(
        climaDto.current().condition().text()
    );

    Actual actual = new Actual(
        climaDto.current().temp_c(),
        climaDto.current().humidity(),
        climaDto.current().last_updated(),
        condicion
    );

    Lugar lugar = new Lugar(
        climaDto.location().name(),
        climaDto.location().region(),
        climaDto.location().country(),
        LocalDateTime.parse(climaDto.location().localtime(), formatter)
    );

    return new Clima(actual, lugar);
  }

  @Override
  public Clima climaMasReciente(){
    return this.climaRepository.obtenerUltimo().orElseThrow(() -> new NoClimaException("No se encontro clima"));
  }

  @Override
  public void analizarAlertaMeteorologica() {
    Clima masReciente = this.climaMasReciente();
    if(this.hayAlerta(masReciente)){
      String mensaje = generarMensajeAlerta(masReciente);
      this.notificador.enviarMensaje(mensaje);
    }
  }

  private String generarMensajeAlerta(Clima clima) {
    return "¡Alerta meteorológica!\n"
        + "La temperatura excede los 35°C y la humedad excede el 60%.\n"
        + "Temperatura actual: " + clima.getActual().getTemperatura() + "°C\n"
        + "Humedad actual: " + clima.getActual().getHumedad() + "%\n"
        + "Condición: " + clima.getActual().getCondicion().getInfo() + "\n"
        + "Lugar: " + clima.getLugar().getNombre() + "\n";
  }

  private boolean hayAlerta(Clima clima) {
    return clima.getActual().getTemperatura() > 35 && clima.getActual().getHumedad() > 60;
  }
}
