package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class Camino extends Carreteras{

	public Camino(String id, int longitud, int velocidadMaxima, CruceGenerico<?> cruceOrigen, CruceGenerico<?> cruceDestino){
		super(id, longitud, velocidadMaxima, cruceOrigen, cruceDestino);
	}
	
	@Override
	protected int calcularVelocidadBase(){
		return this.getVelocidadMaxima();
	}
	
	@Override
	protected int calcularFactorReduccion(int obstacles){
		return obstacles +1;
	}
	
	@Override
	protected void completaDetallesSeccion(IniSection is){
		//crea "vehicles = (v1,10),(v2,10)"
		String values="";
		for(Vehiculos vehiculo: vehiculos){
			if(values!=""){
				values=values+",";
			}
			values=values+"("+vehiculo.getId()+","+vehiculo.getLocalizacion()+")";
		}
		is.setValue("state",values);
		is.setValue("type","dirt");
	}
}
