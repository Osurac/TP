package es.ucm.fdi.control;

import es.ucm.fdi.model.CruceCongestionado;
import es.ucm.fdi.model.CruceGenerico;

public class EventoNuevoCruceCongestionado extends EventoNuevoCruce{

	public EventoNuevoCruceCongestionado(int time, String id) {
		super(time, id);
	}
	
	@Override
	protected CruceGenerico<?> creaCruce(){
		return new CruceCongestionado(id);
	}

}
