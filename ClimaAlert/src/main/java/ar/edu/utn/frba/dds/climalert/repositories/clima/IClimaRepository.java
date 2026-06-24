package ar.edu.utn.frba.dds.climalert.repositories.clima;

import ar.edu.utn.frba.dds.climalert.models.Clima;
import java.util.List;
import java.util.Optional;

public interface IClimaRepository {

  void guardar(Clima clima);

  List<Clima> obtenerTodos();

  Optional<Clima> obtenerUltimo();
}
