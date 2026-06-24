package ar.edu.utn.frba.dds.climalert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrentDto(
    Double temp_c,
    Integer humidity,
    String last_updated,
    ConditionDto condition
) {
}
