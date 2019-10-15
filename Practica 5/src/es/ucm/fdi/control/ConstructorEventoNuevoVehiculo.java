package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoVehiculo extends ConstructorEventos {

	public ConstructorEventoNuevoVehiculo(){
		this.etiqueta="new_vehicle";
		this.claves=new String[] {"time","id","max_speed","itinerary"};
		this.valoresPorDefecto=new String[]{"","","",""};
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta)||section.getValue("type")!=null){
			return null;
		}else{
			return new EventoNuevoVehiculo(
			//Extrae el valor del campo "time" en la seccion. 0 es el valor por defecto en caso de no especificar el tiempo
			ConstructorEventos.parseaIntNoNegativo(section,"time",0),
			//extrae el valor del campo "id" de la seccion
			ConstructorEventos.identificadorValido(section,"id"),
			ConstructorEventos.parseaIntPositive(section, "max_speed"),
			ConstructorEventos.identificadorValidoA(section,"itinerary"));
		}
	}
	
	@Override
	public String toString(){
		return "New Vehicle";
	}
	
	@Override
	public String template() {
		String template="["+this.etiqueta+"]\n";
		for(int i=0;i<this.claves.length;i++){
			template=template+this.claves[i]+" = "+this.valoresPorDefecto[i]+"\n";
		}
		return template;
	}

}
