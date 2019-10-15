package es.ucm.fdi.control;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.*;

public class EventoNuevaCarretera extends Evento{
	
	protected String id;
	protected Integer velocidadMaxima;
	protected Integer longitud;
	protected String cruceOrigenId;
	protected String cruceDestinoId;
	
	public EventoNuevaCarretera(int tiempo, String id, String origen, String destino, int velocidadMaxima, int longitud){
		super(tiempo);
		this.id=id;
		this.cruceOrigenId=origen;
		this.cruceDestinoId=destino;
		this.velocidadMaxima=velocidadMaxima;
		this.longitud=longitud;
	}
	
	@Override
	public void ejecuta(MapaCarreteras mapa) throws ErrorDeSimulacion{
		//obten cruce origen y cruce destino utilizando el mapa
		//crea la carretera
		//añade al mapa la carretera
		//Carreteras carretera=new Carreteras(id, longitud, velocidadMaxima, mapa.getCruce(cruceOrigenId), mapa.getCruce(cruceDestinoId));
		//mapa.addCarretera(id, mapa.getCruce(cruceOrigenId), carretera, mapa.getCruce(cruceDestinoId));
		CruceGenerico<?> cruceOrigen=mapa.getCruce(cruceOrigenId);
		CruceGenerico<?> cruceDestino=mapa.getCruce(cruceDestinoId);
		Carreteras c=creaCarretera(cruceOrigen, cruceDestino);  //lo sobreescriben
		mapa.addCarretera(id, cruceOrigen, c, cruceDestino);
	}
	
	protected Carreteras creaCarretera(CruceGenerico<?> cruceOrigen, CruceGenerico<?> cruceDestino){
		return new Carreteras(this.id,this.longitud, this.velocidadMaxima, cruceOrigen, cruceDestino);
	}
	
	@Override
	public String toString(){
		return id;
	}

	@Override
	public String getType() {
		return "New Road "+this.id;
	}
}
