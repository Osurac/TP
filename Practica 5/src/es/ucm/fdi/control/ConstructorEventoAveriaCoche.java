package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public class ConstructorEventoAveriaCoche extends ConstructorEventos{

	public ConstructorEventoAveriaCoche(){
		this.etiqueta="make_vehicle_faulty";
		this.claves=new String[] {"time","vehicles","duration"};
		this.valoresPorDefecto=new String[]{"","",""};
	}
	
	@Override
	public Evento parser(IniSection section){
		if(!section.getTag().equals(this.etiqueta)||section.getValue("type")!=null){
			return null;
		}else{
			return new EventoAveriaCoche(
			//Extrae el valor del campo "time" en la seccion. 0 es el valor por defecto en caso de no especificar el tiempo
			ConstructorEventos.parseaIntNoNegativo(section,"time",0),
			//extrae el valor del campo "id" de la seccion
			ConstructorEventos.identificadorValidoA(section,"vehicles"),
			ConstructorEventos.parseaIntPositive(section, "duration"));
		}
	}
	
	@Override
	public String toString(){
		return "New Make Vehicle Faulty";
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
