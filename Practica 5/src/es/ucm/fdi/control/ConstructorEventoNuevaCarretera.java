package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaCarretera extends ConstructorEventos {

	public ConstructorEventoNuevaCarretera(){
		this.etiqueta="new_road";
		this.claves=new String[] {"time","id","src","dest","max_speed","length"};
		this.valoresPorDefecto=new String[]{"","","","","",""};
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
			ConstructorEventos.parseaIntPositive(section, "max_speed"),
			ConstructorEventos.parseaIntPositive(section, "length"));
		}
	}
	
	@Override
	public String toString(){
		return "New Road";
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
