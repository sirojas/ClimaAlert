package ar.edu.utn.frba.dds.climalert.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Actual {
  String nombre;
  String region;
  String pais;
  LocalDateTime hora;
}
