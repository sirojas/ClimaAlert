package ar.edu.utn.frba.dds.climalert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WheaterApiResponse(
    LocationDto location,
    CurrentDto current
) {
}
