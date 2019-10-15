package es.ucm.fdi.control;

import es.ucm.fdi.model.Camino;
import es.ucm.fdi.model.Carreteras;
import es.ucm.fdi.model.CruceGenerico;

public class EventoNuevoCamino extends EventoNuevaCarretera{

	public EventoNuevoCamino(int tiempo, String id, String origen,String destino, int velocidadMaxima, int longitud) {
		super(tiempo, id, origen, destino, velocidadMaxima, longitud);
	}

	@Override
	protected Carreteras creaCarretera(CruceGenerico<?> cruceOrigen, CruceGenerico<?> cruceDestino){
		return new Camino(this.id, this.longitud, this.velocidadMaxima, cruceOrigen, cruceDestino);
	}
}
