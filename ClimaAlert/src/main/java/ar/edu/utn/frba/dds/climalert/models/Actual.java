package ar.edu.utn.frba.dds.climalert.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Actual {
  Double temperatura;
  Integer humedad;
  String UltimaUpdate;
  Condicion condicion;
}
