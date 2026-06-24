package ar.edu.utn.frba.dds.climalert.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Lugar {
  String nombre;
  String region;
  String pais;
  LocalDateTime hora;
}
