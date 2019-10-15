package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class Cruces extends CruceGenerico<CarreteraEntrante>{
	
	public Cruces(String id){
		super(id);
	}
	
	protected void actualizaSemaforos(){
		//pone el semaforo de la carretera actual a "rojo", y busca la siguiente
		//carretera entrante para ponerlo a "verde"
		CarreteraEntrante carreteraEntrante;
		if(this.indiceSemaforoVerde==-1){
			this.indiceSemaforoVerde=0;
			carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
			carreteraEntrante.ponSemaforo(true);
		}else{
			carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
			carreteraEntrante.ponSemaforo(false);
			this.indiceSemaforoVerde=this.indiceSemaforoVerde+1;
			this.indiceSemaforoVerde=indiceSemaforoVerde%this.carreterasEntrantes.size();
			carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
			carreteraEntrante.ponSemaforo(true);
		}
	}
	
	@Override
	protected String getNombreSeccion(){
		return "junction_report";
	}
	
	@Override
	protected void completaDetallesSeccion(IniSection is){
		//genera la seccion queues= (r2,green,[]),...
		String values="";
		for(CarreteraEntrante  carreteraEntrante: carreterasEntrantes){
			if(values!=""){
				values=values+",";
			}
			values=values+"("+carreteraEntrante.getcarretera().getId()+","+carreteraEntrante.getEstadoSmaforo()+",["+carreteraEntrante.getColaVehiculos()+"])";
		}
		is.setValue("queues", values);
	}

	@Override
	protected CarreteraEntrante creaCarreteraEntrante(Carreteras carretera) {
		return new CarreteraEntrante(carretera);
	}

	@Override
	public String getCarreteraEntranteVerde() {
		String carretera="[";
		if(!this.carreterasEntrantes.isEmpty()){
			CarreteraEntrante r=this.carreterasEntrantes.get(indiceSemaforoVerde);
			carretera=carretera+"("+r.carretera.getId()+",green,["+r.getColaVehiculos()+"])";
		}
		carretera=carretera+"]";
		return carretera;
	}
	
	@Override
	public String getCarreterasEntranteRojo() {
		String carretera="[";
		if(!this.carreterasEntrantes.isEmpty()){
			for(CarreteraEntrante  carreteraEntrante: carreterasEntrantes){
				if(!carreteraEntrante.tieneSemaforoVerde()){
					carretera=carretera+"("+carreteraEntrante.carretera.getId()+",red,["+carreteraEntrante.getColaVehiculos()+"])";
				}
			}
		}
		carretera=carretera+"]";
		return carretera;
	}
}
