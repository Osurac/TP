package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class CruceCongestionado extends CruceGenerico<CarreteraEntranteConIntervalo>{

	public CruceCongestionado(String id) {
		super(id);
	}

	@Override
	protected void actualizaSemaforos(){
		//Si no hay carretera con semaforo en verde (indiceSemaforoVerde=-1) entonces
		//se selecciona la carretera que tenga mas vehiculos en su cola de vehiculos.
		//Si hay carretera entrante "ri" con su semaforo en verde (indiceSemaforoVerde!=-1) entonces:
		//1. Si ha consumdo su intervalo de tiempo en verde ("ri.timepoConsumido()"):
		// 1.1 Se pone el semaforo de "ri" a rojo.
		// 1.2 Se inicializan los atributos de "ri".
		// 1.3 Se busca la posicion "max" que ocupa la primera carretera entrante distinta de "ri" con el mayor numero de vehiculos e su cola.
		// 1.4 "indiceSemaforVerde" se pone a "max".
		// 1.5 Se pone el semaforo de la carretera entrante en la posicion "max" ("rj") a
		//     verde y se inicializan los atributos de "rj", entre ellos el
		//     "intervaloTiempo" a Math.max(rj.numVehiculosEnCola()/2,1).
		if(this.indiceSemaforoVerde==-1){
			CarreteraEntranteConIntervalo carreteraSeleccionada=this.carreterasEntrantes.get(0);
			for(CarreteraEntranteConIntervalo c:this.carreterasEntrantes){
				if(c.colaVehiculos.size()>carreteraSeleccionada.colaVehiculos.size()){
					carreteraSeleccionada=c;
				}
			}
			this.indiceSemaforoVerde=this.carreterasEntrantes.indexOf(carreteraSeleccionada);
			carreteraSeleccionada.ponSemaforo(true);
			carreteraSeleccionada.setIntervaloDeTiempo(Math.max(carreteraSeleccionada.colaVehiculos.size()/2, 1));
			carreteraSeleccionada.setUnidadesDeTiempoUsadas(0);
			carreteraSeleccionada.setUsoCompleto(false);
			carreteraSeleccionada.setUsadaPorUnVehiculo(false);
		}else if(this.indiceSemaforoVerde!=-1){
			CarreteraEntranteConIntervalo ri=this.carreterasEntrantes.get(indiceSemaforoVerde);
			if(ri.tiempoConsumido()){
				ri.ponSemaforo(false);
				ri.setIntervaloDeTiempo(0);
				ri.setUnidadesDeTiempoUsadas(0);
				ri.setUsoCompleto(false);
				ri.setUsadaPorUnVehiculo(false);
				CarreteraEntranteConIntervalo rj=this.carreterasEntrantes.get(0);
				for(CarreteraEntranteConIntervalo c:this.carreterasEntrantes){
					if(!c.equals(ri)){
						if(c.colaVehiculos.size()>=rj.colaVehiculos.size()){
							rj=c;
						}
					}
				}
				this.indiceSemaforoVerde=this.carreterasEntrantes.indexOf(rj);//ver
				rj.ponSemaforo(true);
				rj.setIntervaloDeTiempo(Math.max(rj.colaVehiculos.size()/2, 1));
				rj.setUnidadesDeTiempoUsadas(0);
				rj.setUsoCompleto(false);
				rj.setUsadaPorUnVehiculo(false);
			}
		}
	}

	@Override
	protected CarreteraEntranteConIntervalo creaCarreteraEntrante(Carreteras carretera) {
		return new CarreteraEntranteConIntervalo(carretera,0);
	}

	@Override
	protected void completaDetallesSeccion(IniSection is) {
		String values="";
		for(CarreteraEntranteConIntervalo carreteraEntrante: carreterasEntrantes){
			if(values!=""){
				values=values+",";
			}
			if(carreteraEntrante.getIntervaloTiempo()!=0){
				values=values+"("+carreteraEntrante.getcarretera().getId()+","+carreteraEntrante.getEstadoSmaforo()+":"+carreteraEntrante.getIntervaloTiempo()+",["+carreteraEntrante.getColaVehiculos()+"])";
			}else{
				values=values+"("+carreteraEntrante.getcarretera().getId()+","+carreteraEntrante.getEstadoSmaforo()+",["+carreteraEntrante.getColaVehiculos()+"])";
			}
			
		}
		is.setValue("queues", values);
		is.setValue("type", "mc");
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
