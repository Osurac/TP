package es.ucm.fdi.control;

import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Vehiculos;

public class EventoAveriaCoche extends Evento {
	
	protected String[] vehiculos;
	protected Integer duracion;
	
	public EventoAveriaCoche(int tiempo,String[] vehiculos,int duracion){
		super(tiempo);
		this.vehiculos=vehiculos;
		this.duracion=duracion;
	}
	
	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorDeSimulacion {
		List<Vehiculos> iti=ParserVehiculos.parseListaVehiculos(this.vehiculos,mapa);
		for(Vehiculos v:iti){
			v.setTiempoAveria(duracion);
		}
	}

}
