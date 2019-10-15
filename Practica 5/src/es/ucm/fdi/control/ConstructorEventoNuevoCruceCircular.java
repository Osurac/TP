package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCircular extends ConstructorEventos{

	public ConstructorEventoNuevoCruceCircular(){
		this.etiqueta="new_junction";
		this.claves=new String[] {"time","id","type","max_time_slice","min_time_slice"};
		this.valoresPorDefecto=new String[]{"","","rr","",""};
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta)||!section.getValue("type").equals("rr")){
			return null;
		}else{
			return new EventoNuevoCruceCircular(
			//Extrae el valor del campo "time" en la seccion. 0 es el valor por defecto en caso de no especificar el tiempo
			ConstructorEventos.parseaIntNoNegativo(section,"time",0),
			//extrae el valor del campo "id" de la seccion
			ConstructorEventos.identificadorValido(section,"id"),
			ConstructorEventos.parseaIntPositive(section,"min_time_slice"),
			ConstructorEventos.parseaIntPositive(section,"max_time_slice"));
		}
	}
	
	@Override
	public String toString(){
		return "New RR Junction";
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
