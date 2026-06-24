package ar.edu.utn.frba.dds.climalert.schedulers;

import ar.edu.utn.frba.dds.climalert.models.Clima;
import ar.edu.utn.frba.dds.climalert.services.clima.IClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DetecterScheduler {

  private final IClimaService climaService;

  public DetecterScheduler(IClimaService climaService){
    this.climaService = climaService;
  }

  @Scheduled (fixedRate = 60000)
  void analizarAlertaMeteorologica(){
    try{
      this.climaService.analizarAlertaMeteorologica();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
