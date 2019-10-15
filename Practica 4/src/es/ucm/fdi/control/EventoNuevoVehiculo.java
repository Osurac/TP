package es.ucm.fdi.control;

import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.*;

public class EventoNuevoVehiculo extends Evento {

	protected String id;
	protected Integer velocidadMaxima;
	protected String[] intinerario;
	
	public EventoNuevoVehiculo(int tiempo, String id, int velocidadMaxima, String[] intinerario){
		super(tiempo);
		this.id=id;
		this.velocidadMaxima=velocidadMaxima;
		this.intinerario=intinerario;
	}
	
	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorDeSimulacion{
		//si iti es null o tiene menos de dos cruces lanzar excepcion
		//en otro caso crear el vehiculo y añadirlo al mapa.
		List<Cruces> iti=ParserCarreteras.parseListaCruces(this.intinerario,mapa);
		if(iti==null||iti.size()<2){
			throw new ErrorDeSimulacion("El intinerario del vehiculo es nulo o tiene menos de dos cruces.");
		}else{
			Vehiculos vehiculo=new Vehiculos(id, velocidadMaxima, iti);
			mapa.addVehiculo(id, vehiculo);
		}
		
	}
	
	@Override
	public String toString(){
		return id;
	}
}
