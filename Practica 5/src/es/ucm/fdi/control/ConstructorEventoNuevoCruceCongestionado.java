package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCruceCongestionado extends ConstructorEventos{

	public ConstructorEventoNuevoCruceCongestionado(){
		this.etiqueta="new_junction";
		this.claves=new String[] {"time","id","type"};
		this.valoresPorDefecto=new String[]{"","","mc"};
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta)||!section.getValue("type").equals("mc")){
			return null;
		}else{
			return new EventoNuevoCruceCongestionado(
			//Extrae el valor del campo "time" en la seccion. 0 es el valor por defecto en caso de no especificar el tiempo
			ConstructorEventos.parseaIntNoNegativo(section,"time",0),
			//extrae el valor del campo "id" de la seccion
			ConstructorEventos.identificadorValido(section,"id"));
		}
	}
	
	@Override
	public String toString(){
		return "New MC Junction";
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
