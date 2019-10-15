package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoNuevaAutopista extends ConstructorEventos{

	public ConstructorEventoNuevaAutopista(){
		this.etiqueta="new_road";
		this.claves=new String[] {"time","id","src","dest","max_speed","length","type","lanes"};
		this.valoresPorDefecto=new String[]{"","","","","","","lanes",""};
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta)||!section.getValue("type").equals("lanes")){
			return null;
		}else{
			return new EventoNuevaAutopista(
			//Extrae el valor del campo "time" en la seccion. 0 es el valor por defecto en caso de no especificar el tiempo
			ConstructorEventos.parseaIntNoNegativo(section,"time",0),
			//extrae el valor del campo "id" de la seccion
			ConstructorEventos.identificadorValido(section,"id"),
			ConstructorEventos.identificadorValido(section, "src"),
			ConstructorEventos.identificadorValido(section, "dest"),
			ConstructorEventos.parseaIntPositive(section, "max_speed"),
			ConstructorEventos.parseaIntPositive(section, "length"),
			ConstructorEventos.parseaIntPositive(section, "lanes"));
		}
	}
	
	@Override
	public String toString(){
		return "New Lanes Road";
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
