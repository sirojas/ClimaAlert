package ar.edu.utn.frba.dds.climalert.services.clima;

import ar.edu.utn.frba.dds.climalert.dto.WheaterApiResponse;

public interface IClimaService {

  void guardarNuevoClima(WheaterApiResponse climaDto);
}
