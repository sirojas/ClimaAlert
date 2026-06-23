package ar.edu.utn.frba.dds.climalert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LocationDto(
    String temp_c,
    String humidity,
    String last_updated,
    ConditionDto condition
) {
}
