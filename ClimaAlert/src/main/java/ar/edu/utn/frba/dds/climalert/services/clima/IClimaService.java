package ar.edu.utn.frba.dds.climalert.services.clima;

import ar.edu.utn.frba.dds.climalert.dto.WheaterApiResponse;
import ar.edu.utn.frba.dds.climalert.models.Clima;

public interface IClimaService {

  void guardarNuevoClima(WheaterApiResponse climaDto);

  Clima climaMasReciente();

  void analizarAlertaMeteorologica();
}
