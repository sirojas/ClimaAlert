package ar.edu.utn.frba.dds.climalert.services.WheaterApi.impl;

import ar.edu.utn.frba.dds.climalert.dto.WheaterApiResponse;
import ar.edu.utn.frba.dds.climalert.services.WheaterApi.IWheaterApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WheaterApiService implements IWheaterApiService {

  private final RestClient restClient;

  @Value("${weatherapi.key}")
  private String apiKey;

  @Value("${weatherapi.location}")
  private String location;

  public WheaterApiService(RestClient.Builder builder,  @Value("${weatherapi.base-url}") String baseUrl){
    this.restClient = builder.baseUrl(baseUrl).build();
  }

  @Override
  public WheaterApiResponse pedirClima() {
    return null;
  }
}
