package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class CruceCircular extends CruceGenerico<CarreteraEntranteConIntervalo>{

	protected int minValorIntervalo;
	protected int maxValorIntervalo;
	
	public CruceCircular(String id, int minValorIntervalo, int maxValorIntervalo) {
		super(id);
		this.minValorIntervalo=minValorIntervalo;
		this.maxValorIntervalo=maxValorIntervalo;
	}

	@Override
	protected void actualizaSemaforos(){
		//Si no hay carretera con semaforo en verde (indiceSemaforoVerde==-1) entonces se
		//selecciona la primera carretera entrante (la de la posicion 0) y se pone su semaforo en verde.
		
		//Si hay carretera entrante "ri" con su semaforo en verde, (indiceSemaforoVerde!=-1) entonces:
		// 1. Si ha consumido su intervalo de tiempo en verde ("ri.timepoConsumido()"):
		//  1.1 Se pone el semaforo de "ri" a rojo.
		//  1.2 Si ha sido usada en todos los pasos ("ri.usoCompleto()"), se fija
		//		el intervalo de tiempo a ... .Sino, si no ha sido usada
		//      ("!ri.usada()") se fija el intervalo de tiempo a ...
		//  1.3 Se coge como nueva carretera con semaforo a verde la inmediatamente Posterior a "ri".
		if(this.indiceSemaforoVerde==-1){
			this.indiceSemaforoVerde=0;
			CarreteraEntranteConIntervalo carretera=this.carreterasEntrantes.get(indiceSemaforoVerde);
			carretera.ponSemaforo(true);
			carretera.setUnidadesDeTiempoUsadas(0);
			carretera.setContadorVehiculos(0);
			carretera.setUsadaPorUnVehiculo(false);
			carretera.setUsoCompleto(false);
		}else if(this.indiceSemaforoVerde!=-1){
			CarreteraEntranteConIntervalo ri=this.carreterasEntrantes.get(indiceSemaforoVerde);
			if(ri.tiempoConsumido()){
				ri.ponSemaforo(false);
				if(ri.usoCompleto()){
					ri.setIntervaloDeTiempo(Math.min(ri.getIntervaloTiempo()+1, maxValorIntervalo));
				}else if(!ri.usada()){
					ri.setIntervaloDeTiempo(Math.max(ri.getIntervaloTiempo()-1, minValorIntervalo));
				}
				ri.setUnidadesDeTiempoUsadas(0);
				ri.setContadorVehiculos(0);
				ri.setUsadaPorUnVehiculo(false);
				ri.setUsoCompleto(false);
				this.indiceSemaforoVerde++;
				this.indiceSemaforoVerde=indiceSemaforoVerde%this.carreterasEntrantes.size();
				CarreteraEntranteConIntervalo rj=this.carreterasEntrantes.get(indiceSemaforoVerde);
				rj.ponSemaforo(true);
			}
		}
	}



	@Override
	protected CarreteraEntranteConIntervalo creaCarreteraEntrante(Carreteras carretera) {
		return new CarreteraEntranteConIntervalo(carretera,this.maxValorIntervalo);
	}



	@Override
	protected void completaDetallesSeccion(IniSection is) {
		String values="";
		for(CarreteraEntranteConIntervalo  carreteraEntrante: carreterasEntrantes){
			if(values!=""){
				values=values+",";
			}
			if(carreteraEntrante.getEstadoSmaforo()=="green"){
				values=values+"("+carreteraEntrante.getcarretera().getId()+","+carreteraEntrante.getEstadoSmaforo()+":"+(carreteraEntrante.getIntervaloTiempo()-carreteraEntrante.getUnidadesDeTiempoUsadas())+",["+carreteraEntrante.getColaVehiculos()+"])";
			}else{
				values=values+"("+carreteraEntrante.getcarretera().getId()+","+carreteraEntrante.getEstadoSmaforo()+",["+carreteraEntrante.getColaVehiculos()+"])";
			}
			
		}
		is.setValue("queues", values);
		is.setValue("type", "rr");
	}

	@Override
	protected String getNombreSeccion() {
		return "junction_report";
	}
	
	@Override
	public String getCarreteraEntranteVerde() {
		String carretera="[";
		if(!this.carreterasEntrantes.isEmpty()){
			CarreteraEntranteConIntervalo r=this.carreterasEntrantes.get(indiceSemaforoVerde);
			carretera=carretera+"("+r.carretera.getId()+",green:"+r.getIntervaloTiempo()+",["+r.getColaVehiculos()+"])";
		}
		carretera=carretera+"]";
		return carretera;
	}
	
	@Override
	public String getCarreterasEntranteRojo() {
		String carretera="[";
		if(!this.carreterasEntrantes.isEmpty()){
			for(CarreteraEntranteConIntervalo  carreteraEntrante: carreterasEntrantes){
				if(!carreteraEntrante.tieneSemaforoVerde()){
					carretera=carretera+"("+carreteraEntrante.carretera.getId()+",red,["+carreteraEntrante.getColaVehiculos()+"])";
				}
			}
		}
		carretera=carretera+"]";
		return carretera;
	}
}
