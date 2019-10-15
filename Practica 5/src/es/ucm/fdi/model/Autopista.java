package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class Autopista  extends Carreteras{

	private int numCarriles;
	
	public Autopista(String id, int length, int maxSpeed, CruceGenerico<?> cruceOrigenId, CruceGenerico<?> cruceDestinoId, int numCarriles) {
		super(id, length, maxSpeed, cruceOrigenId, cruceDestinoId);
		this.numCarriles=numCarriles;
	}
	
	@Override
	protected int calcularVelocidadBase(){
		return Math.min(this.getVelocidadMaxima(),(this.getVelocidadMaxima()*this.numCarriles/Math.max(this.getVehiculos().size(),1))+1/1);
	}

	@Override
	protected int calcularFactorReduccion(int obstacles){
		return obstacles <this.numCarriles ? 1 : 2;
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
		is.setValue("type","lanes");
	}
}
