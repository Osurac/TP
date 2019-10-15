package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaCarretera extends ConstructorEventos {

	public ConstructorEventoNuevaCarretera(){
		this.etiqueta="new_road";
		this.claves=new String[] {"time","id","src","dest","max_speed","length"};
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta)||section.getValue("type")!=null){
			return null;
		}else{
			return new EventoNuevaCarretera(
			//Extrae el valor del campo "time" en la seccion. 0 es el valor por defecto en caso de no especificar el tiempo
			ConstructorEventos.parseaIntNoNegativo(section,"time",0),
			//extrae el valor del campo "id" de la seccion
			ConstructorEventos.identificadorValido(section,"id"),
			ConstructorEventos.identificadorValido(section, "src"),
			ConstructorEventos.identificadorValido(section, "dest"),
			ConstructorEventos.parseaIntNoNegativo(section, "max_speed"),
			ConstructorEventos.parseaIntNoNegativo(section, "length"));
		}
	}
	
	@Override
	public String toString(){
		return "New Road";
	}

}
