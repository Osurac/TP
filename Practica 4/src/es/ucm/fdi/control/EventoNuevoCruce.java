package es.ucm.fdi.control;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.*;

public class EventoNuevoCruce extends Evento{

	protected String id;
	 
	public EventoNuevoCruce(int time, String id) {
		super(time);
		this.id=id;
	}

	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorDeSimulacion{
		//crea el cruce y se lo añade al mapa
		Cruces cruce=new Cruces(id);
		mapa.addCruce(id, cruce);
	}
	
	@Override
	public String toString(){
		return id;
	}
}
