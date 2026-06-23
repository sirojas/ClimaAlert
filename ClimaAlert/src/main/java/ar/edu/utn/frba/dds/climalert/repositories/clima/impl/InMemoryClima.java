package ar.edu.utn.frba.dds.climalert.repositories.clima.impl;

import ar.edu.utn.frba.dds.climalert.models.Clima;
import ar.edu.utn.frba.dds.climalert.repositories.clima.IClimaRepository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClima implements IClimaRepository {

  private List<Clima> climasHistoricos = new ArrayList<>()
  @Override
  public void guardar(Clima clima) {
    this.climasHistoricos.add(clima);
  }

  public List<Clima> obtenerTodos(){
    return this.climasHistoricos;
  }

  public Optional<Clima> obtenerUltimo(){
    if(this.climasHistoricos.isEmpty()){
      return Optional.empty();
    }
    return Optional.of(this.climasHistoricos.get(this.climasHistoricos.size() -1));
  }

}
