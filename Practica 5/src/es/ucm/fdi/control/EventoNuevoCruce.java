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
		mapa.addCruce(id, this.creaCruce());//lo sobreescriben las clases que heredan
	}
	
	protected CruceGenerico<?> creaCruce(){
		return new Cruces(id);
	}
	
	
	@Override
	public String toString(){
		return id;
	}

	@Override
	public String getType() {
		return "New Junction "+this.id;
	}
}
