package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevoCamino extends ConstructorEventos{

	public ConstructorEventoNuevoCamino(){
		this.etiqueta="new_road";
		this.claves=new String[] {"time","id","src","dest","max_speed","length","type"};
		this.valoresPorDefecto=new String[] {"","","","","","","dirt"};
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta)||!section.getValue("type").equals("dirt")){
			return null;
		}else{
			return new EventoNuevoCamino(
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
		return "New Dirt Road";
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
