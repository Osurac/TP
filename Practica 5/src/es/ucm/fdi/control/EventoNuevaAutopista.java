package es.ucm.fdi.control;

import es.ucm.fdi.model.Autopista;
import es.ucm.fdi.model.Carreteras;
import es.ucm.fdi.model.CruceGenerico;

public class EventoNuevaAutopista  extends EventoNuevaCarretera{

	protected Integer numCarriles;
	
	public EventoNuevaAutopista(int tiempo, String id, String origen,String destino, int velocidadMaxima, int longitud, int lanes) {
		super(tiempo, id, origen, destino, velocidadMaxima, longitud);
		this.numCarriles=lanes;
	}
	
	@Override
	protected Carreteras creaCarretera(CruceGenerico<?> cruceOrigen, CruceGenerico<?> cruceDestino){
		return new Autopista(this.id,this.longitud,this.velocidadMaxima,cruceOrigen,cruceDestino,numCarriles);
	}

}
