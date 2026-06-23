package ar.edu.utn.frba.dds.climalert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrentDto(
    String name,
    String region,
    String country,
    String localtime
) {
}
