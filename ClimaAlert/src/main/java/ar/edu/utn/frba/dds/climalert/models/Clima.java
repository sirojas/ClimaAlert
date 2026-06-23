package ar.edu.utn.frba.dds.climalert.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Clima {
  private Lugar lugar;

  private Actual actual;
}
