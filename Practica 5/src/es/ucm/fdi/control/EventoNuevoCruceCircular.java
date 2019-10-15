package es.ucm.fdi.control;

import es.ucm.fdi.model.CruceCircular;
import es.ucm.fdi.model.CruceGenerico;

public class EventoNuevoCruceCircular extends EventoNuevoCruce{
	protected Integer maxValorIntervalo;
	protected Integer minValorIntervalo;
	
	public EventoNuevoCruceCircular(int time, String id, int minValorIntervalo, int maxValorIntervalo){
		super(time,id);
		this.maxValorIntervalo=maxValorIntervalo;
		this.minValorIntervalo=minValorIntervalo;
	}
	
	@Override
	protected CruceGenerico<?> creaCruce(){
		return new CruceCircular(this.id,this.minValorIntervalo,this.maxValorIntervalo);
	}

}
