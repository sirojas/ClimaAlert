package ar.edu.utn.frba.dds.climalert.services.clima.impl;

import ar.edu.utn.frba.dds.climalert.dto.WheaterApiResponse;
import ar.edu.utn.frba.dds.climalert.models.Clima;
import ar.edu.utn.frba.dds.climalert.repositories.clima.IClimaRepository;
import ar.edu.utn.frba.dds.climalert.services.clima.IClimaService;
import org.springframework.stereotype.Service;

@Service
public class ClimaService implements IClimaService {

  private final IClimaRepository climaRepository;

  public ClimaService(IClimaRepository climaRepository){
    this.climaRepository = climaRepository;
  }

  @Override
  public void guardarNuevoClima(WheaterApiResponse climaDto) {
    Clima climaNuevo = this.parsearClima(climaDto);
    this.climaRepository.guardar(climaNuevo);
  }

  private Clima parsearClima(WheaterApiResponse climaDto) {
  }
}
