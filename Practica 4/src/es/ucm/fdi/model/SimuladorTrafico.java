package es.ucm.fdi.model;

import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;

public class SimuladorTrafico {
	private MapaCarreteras mapa;
	private List<Evento> eventos;
	private int contadorTiempo;
	
	public SimuladorTrafico(){
		this.mapa= new MapaCarreteras();
		this.contadorTiempo=0;
		Comparator<Evento> cmp= new Comparator<Evento>(){
			@Override
			public int compare(Evento e1, Evento e2) {
				if(e1.getTimepo()==e2.getTimepo()){
					return 0;
				}else if(e1.getTimepo()<e2.getTimepo()){
					return -1;
				}else{
					return 1;
				}
			}
		};
		this.eventos=new SortedArrayList<Evento>(cmp);//estructura ordenada por "tiempo"
	}
	
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) throws ErrorDeSimulacion {
		int limiteTiempo=this.contadorTiempo+pasosSimulacion-1;
		while(this.contadorTiempo<=limiteTiempo){
			//Ejecuta todos los eventos correspondientes a "this.contadorTiempo"
			//actualizar "mapa"
			//escribir el informe en "ficheroSalida", controlando que no sea null.
			while(!eventos.isEmpty()&&eventos.get(0).getTimepo()==this.contadorTiempo){
				Evento evento=eventos.get(0);
				eventos.remove(0);
				evento.ejecuta(mapa);
			}
			if(this.contadorTiempo>0){
				mapa.actualizar();
				String informe=mapa.generateReport(contadorTiempo);
				System.out.println(informe);
			}
			this.contadorTiempo++;
		}
	}

	public void insertaEvento(Evento e) throws ErrorDeSimulacion {
		//inserta un evento en "eventos", controlando que el tiempo de
		//ejecucion del evento sea menor que "contadorTiempo"
		if(e.getTimepo()>=this.contadorTiempo){
			this.eventos.add(e);
		}else{
			throw new ErrorDeSimulacion("Tiempo de evento superior al contador de tiempo.");
		}
	}

}
