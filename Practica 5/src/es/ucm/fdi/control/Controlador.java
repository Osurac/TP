package es.ucm.fdi.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.ucm.fdi.model.ObservadorSimuladorTrafico;
import es.ucm.fdi.model.SimuladorTrafico;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.ini.*;

public class Controlador {
	private SimuladorTrafico simulador;
	private Integer limiteTiempo;
	private InputStream is;
	private OutputStream os;
	
	
	
	public Controlador(SimuladorTrafico sim, Integer limiteTiempo, InputStream is, OutputStream os){
		this.simulador=sim;
		this.limiteTiempo=limiteTiempo;
		this.is=is;
		this.os=os;
	}
	
	public void ejecuta() throws ErrorDeSimulacion, IOException{
		this.cargaEventos(this.is);
		this.simulador.ejecuta(limiteTiempo,this.os);
	}
	
	public void ejecuta(int pasos) throws ErrorDeSimulacion, IOException{
		this.simulador.ejecuta(pasos, this.os);
	}
	
	public void cargaEventos(InputStream inStream) throws ErrorDeSimulacion{
		Ini ini;
		try{
			//lee el fichero y carga su atributo iniSections
			ini=new Ini(inStream);//Devuelve una lista de secciones 
		}catch(IOException e){
			ErrorDeSimulacion err= new ErrorDeSimulacion("Error en la lectura de eventos: "+e);
			this.simulador.error(err);
			throw err;
			
		}
		//Recorremos todos los elementos de iniSection para generar el evento correspondiente
		for(IniSection sec: ini.getSections()){
			//parseamos la seccion para ver a que evento corresponde
			try{
				Evento e=ParserEventos.parseaEvento(sec);
				if(e != null){
					this.simulador.insertaEvento(e);
				}
				else{
					ErrorDeSimulacion err=new ErrorDeSimulacion("Evento desconocido: "+sec.getTag());
					this.simulador.error(err);
					throw err;
				}
			}catch (IllegalArgumentException e){
				ErrorDeSimulacion err= new ErrorDeSimulacion(e.getMessage());
				this.simulador.error(err);
				throw err;
			}
		}
	}
	
	public void reinicia(){
		this.simulador.reinicia();
	}
	
	public void addObserver(ObservadorSimuladorTrafico o){
		this.simulador.addObservador(o);
	}
	
	public void removeObserver(ObservadorSimuladorTrafico o){
		this.simulador.removeObservador(o);
	}
}
