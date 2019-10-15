package es.ucm.fdi.control;

import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.Coche;
import es.ucm.fdi.model.CruceGenerico;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Vehiculos;

public class EventoNuevoCoche extends EventoNuevoVehiculo{

	protected int resistenciaKM;
	protected double probabilidadDeAveria;
	protected int duracionMaxima;
	protected long semilla;
	
	public EventoNuevoCoche(int tiempo, String id, int velocidadMaxima,String[] intinerario, int resistenciaKM,double probabilidadDeAveria, int duracionMaxima, long semilla ) {
		super(tiempo, id, velocidadMaxima, intinerario);
		this.resistenciaKM=resistenciaKM;
		this.probabilidadDeAveria=probabilidadDeAveria;
		this.duracionMaxima=duracionMaxima;
		this.semilla=semilla;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorDeSimulacion{
		//si iti es null o tiene menos de dos cruces lanzar excepcion
		//en otro caso crear el vehiculo y añadirlo al mapa.
		List<CruceGenerico<?>> iti=ParserCarreteras.parseListaCruces(this.intinerario,mapa);
		if(iti==null||iti.size()<2){
			throw new ErrorDeSimulacion("El intinerario del vehiculo es nulo o tiene menos de dos cruces.");
		}else{
			Vehiculos vehiculo=new Coche(id, velocidadMaxima, iti, resistenciaKM, duracionMaxima, probabilidadDeAveria, semilla);
			mapa.addVehiculo(id, vehiculo);
		}
		
	}
	
	@Override
	public String toString(){
		return id;
	}
}
