package es.ucm.fdi.control;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;

public abstract class Evento {

	protected Integer time;
	
	public Evento(int tiempo){
		this.time=tiempo;
	}
	
	public int getTimepo(){
		return time;
	}
	
	//Cada clase que hereda implementa su metodo ejecuta, que creara
	//el correspondiente objeto de la simulacion.
	public abstract void ejecuta(MapaCarreteras mapa) throws ErrorDeSimulacion;
}
