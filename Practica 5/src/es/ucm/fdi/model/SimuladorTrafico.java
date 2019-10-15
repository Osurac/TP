package es.ucm.fdi.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;

public class SimuladorTrafico implements Observador<ObservadorSimuladorTrafico>{
	private MapaCarreteras mapa;
	private List<Evento> eventos;
	private int contadorTiempo;
	private List<ObservadorSimuladorTrafico> observadores;
	
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
		this.observadores=new ArrayList<ObservadorSimuladorTrafico>();
	}
	
	public void ejecuta(int pasosSimulacion, OutputStream ficheroSalida) throws ErrorDeSimulacion, IOException {
		try{
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
				mapa.actualizar();
				this.contadorTiempo++;
				this.notificaAvanza();
				String informe=mapa.generateReport(contadorTiempo);
				if(ficheroSalida!=null){
					ficheroSalida.write(informe.getBytes());
				}else{
					System.out.println(informe);
				}
			}
		}catch(ErrorDeSimulacion e){
				ErrorDeSimulacion err=new ErrorDeSimulacion(e.getMessage());
				this.error(err);
				throw err;
		}catch(IOException e){
				ErrorDeSimulacion err=new ErrorDeSimulacion(e.getMessage());
				this.error(err);
				throw err;
		}
	}
	
	public void insertaEvento(Evento e) throws ErrorDeSimulacion{
		if(e!=null){
			if(e.getTimepo()<this.contadorTiempo){
				ErrorDeSimulacion err=new ErrorDeSimulacion("Tiempo de evento inferior al contador de tiempo.");
				this.notificaError(err);
				throw err;
			}else{
				this.eventos.add(e);
				this.notificaNuevoEvento();
			}
		}else{
			ErrorDeSimulacion err=new ErrorDeSimulacion("El evento es nulo");
			this.notificaError(err);//Se notifica a los observadores
			throw err;
		}
	}

	private void notificaNuevoEvento(){
		for(ObservadorSimuladorTrafico o : this.observadores){
			o.addEvento(contadorTiempo, mapa, eventos);
		}
	}
	
	private void notificaError(ErrorDeSimulacion e){
		for(ObservadorSimuladorTrafico o : this.observadores){
			o.errorSimulador(contadorTiempo, mapa, eventos,e);
		}
	}
	
	private void notificaAvanza(){
		for(ObservadorSimuladorTrafico o : this.observadores){
			o.avanza(contadorTiempo, mapa, eventos);
		}
	}
	
	private void notificaReinicia(){
		for(ObservadorSimuladorTrafico o : this.observadores){
			o.reinicia(contadorTiempo, mapa, eventos);
		}
	}
	
	@Override
	public void addObservador(ObservadorSimuladorTrafico o){
		if(o!=null && !this.observadores.contains(o)){
			this.observadores.add(o);
		}
	}
	
	@Override
	public void removeObservador(ObservadorSimuladorTrafico o){
		if(o!=null && this.observadores.contains(o)){
			this.observadores.remove(o);
		}
	}

	public void reinicia() {
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
		this.notificaReinicia();
	}

	public void error(ErrorDeSimulacion err) {
		this.mapa=new MapaCarreteras();
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
		this.notificaError(err);
	}
}
