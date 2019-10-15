package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;

public class Cruces extends ObjetoSimulacion{
	private int indiceSemaforoVerde;//lleva el indice de la carretera entrante con el semaforo en verde
	private List<CarreteraEntrante> carreterasEntrantes;
	private List<Carreteras> carreterasSalientes;
	//para optimizar las busquedas de las carreterasEntrantes (IdCarretera, CarreteraEntrante
	private Map<String,CarreteraEntrante> mapaCarreterasEntrantes;
	private Map<Cruces, Carreteras> CarreterasSalientes;
	
	public Cruces(String id){
		super(id);
		this.carreterasEntrantes=new ArrayList<CarreteraEntrante>();
		this.carreterasSalientes=new ArrayList<Carreteras>();
		this.CarreterasSalientes=new HashMap<Cruces,Carreteras>();
		this.mapaCarreterasEntrantes=new HashMap<String,CarreteraEntrante>();
		this.indiceSemaforoVerde=-1;
	}
	
	public void addCarreteraEntranteAlCruce(String idCarretera, Carreteras carretera){
		//añade una carretera entrante al "mapaCarreterasEntrantes" y a las "carreterasEntrantes"
		if(!this.carreterasEntrantes.isEmpty()){
			this.mapaCarreterasEntrantes.put(idCarretera, new CarreteraEntrante(carretera));
			this.carreterasEntrantes.add(new CarreteraEntrante(carretera));
		}else{
			this.mapaCarreterasEntrantes.put(idCarretera, new CarreteraEntrante(carretera));
			this.carreterasEntrantes.add(new CarreteraEntrante(carretera));
			this.indiceSemaforoVerde=0;
			CarreteraEntrante carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
			carreteraEntrante.ponSemaforo(true);
		}
	}
	
	public void addCarreteraSalienteAlCruce(Cruces destino, Carreteras carretera){
		//añade una carretera saliente
		this.CarreterasSalientes.put(destino, carretera);
		this.carreterasSalientes.add(carretera);
	}
	
	public void entraVehiculoAlCruce(String idCarretera,Vehiculos vehiculo){
		//añade el "vehiculo" a la carretera entrante "idCarretera"
		CarreteraEntrante carreteraEntrante=this.mapaCarreterasEntrantes.get(idCarretera);
		for(CarreteraEntrante c: this.carreterasEntrantes){
			if(c.getcarretera().id==carreteraEntrante.getcarretera().getId()){
				c.anadirVehiculoALaCola(vehiculo);//preguntar
			}
		}
		
	}
	
	protected void actualizaSemaforos(){
		//pone el semaforo de la carretera actual a "rojo", y busca la siguiente
		//carretera entrante para ponerlo a "verde"
		CarreteraEntrante carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
		carreteraEntrante.ponSemaforo(false);
		indiceSemaforoVerde=indiceSemaforoVerde+1;
		if(indiceSemaforoVerde==carreterasEntrantes.size()){
			indiceSemaforoVerde=0;//corregir
		}
		carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
		carreteraEntrante.ponSemaforo(true);
	}
	
	@Override
	public void avanza() throws ErrorDeSimulacion{
		//Si "carreterasEntrantes" es vacio, no hace nada
		//en otro caso "avanzaPrimerVehiculo" de la carretera con el semaforo verde.
		//Posteriormente actualiza los semaforos.
		if(!carreterasEntrantes.isEmpty()){
			CarreteraEntrante carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
			carreteraEntrante.avanzaPrimerVehiculo();
			actualizaSemaforos();
		}
	}
	
	public Carreteras carreteraHaciaCruce(Cruces cruce){
		for(Carreteras carretera: carreterasSalientes){
			Cruces cruceOrigen=carretera.getCruceOrigen();
			Cruces cruceDestino=carretera.getCruceDestino();
			if(this.equals(cruceOrigen)&&cruce.equals(cruceDestino)){
				return carretera;
			}
		}
		return null;
	}
	
	public Carreteras carreteraQueVaCruce(Cruces cruce){
		return this.carreteraHaciaCruce(cruce);
	}
	
	@Override
	protected String getNombreSeccion(){
		return "junction_report";
	}
	
	@Override
	protected void completaDetallesSeccion(IniSection is){
		//genera la seccion queues= (r2,green,[]),...
		String values="";
		for(CarreteraEntrante  carreteraEntrante: carreterasEntrantes){
			values=values+"("+carreteraEntrante.getcarretera().getId()+","+carreteraEntrante.getEstadoSmaforo()+",["+carreteraEntrante.getColaVehiculos()+"]),";
		}
		is.setValue("queues", values);
	}
}
