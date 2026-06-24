package ar.edu.utn.frba.dds.climalert.schedulers;

import ar.edu.utn.frba.dds.climalert.dto.WheaterApiResponse;
import ar.edu.utn.frba.dds.climalert.services.WheaterApi.IWheaterApiService;
import ar.edu.utn.frba.dds.climalert.services.clima.IClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RequesterScheduler {

  private final IWheaterApiService wheatherApi;

  private final IClimaService climaService;

  public RequesterScheduler(IWheaterApiService wheatherApi, IClimaService climaService){
    this.wheatherApi = wheatherApi;
    this.climaService = climaService;
  }

  @Scheduled(fixedRate = 30000)
  void pedirClima(){
    try{
      WheaterApiResponse respuesta = this.wheatherApi.pedirClima();
      this.climaService.guardarNuevoClima(respuesta);
    } catch (Exception e) {
      System.out.println("fallo la peticion a weather api: " + e.getMessage());
    }
  }

}
