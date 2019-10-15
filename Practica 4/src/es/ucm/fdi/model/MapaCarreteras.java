package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.exception.ErrorDeSimulacion;

public class MapaCarreteras {
	private List<Carreteras> carreteras;
	private List<Cruces> cruces;
	private List<Vehiculos> vehiculos;
	
	//Estructura para agilizar la busqueda (id,valor)
	private Map<String,Carreteras> mapaDeCarreteras;
	private Map<String, Cruces> mapaDeCruces;
	private Map<String,Vehiculos> mapaDeVehiculos;
	
	public MapaCarreteras(){
		//inicializa los atributos a sus constructoras por defecto.
		//Para carreteras, cruces y vehiculos puede usarse ArrayList.
		//Para los mapas puede usarse HashMap
		this.carreteras=new ArrayList<Carreteras>();
		this.cruces=new ArrayList<Cruces>();
		this.vehiculos=new ArrayList<Vehiculos>();
		this.mapaDeCarreteras=new HashMap<String,Carreteras>();
		this.mapaDeCruces=new HashMap<String,Cruces>();
		this.mapaDeVehiculos=new HashMap<String,Vehiculos>();
	}
	
	public void addCruce(String idCruce, Cruces cruce) throws ErrorDeSimulacion{
		//comprueba que "idCruce" no existe en el map.
		//Si no existe, lo añade a "cruces" y a "mapaDeCruces".
		//Si existe lanza una excepcion.
		if(!mapaDeCruces.containsKey(idCruce)){
			cruces.add(cruce);
			mapaDeCruces.put(idCruce, cruce);
		}else{
			throw new ErrorDeSimulacion("El cruce ya existe en el mapa.");
		}
	}
	
	public void addVehiculo(String idVehiculo, Vehiculos vehiculo) throws ErrorDeSimulacion{
		//comprueba que "idVehiculo" no existe en el mapa.
		//Si no existe, lo añade a "vehiculos" y a "mapaDeVehiculos",
		//y posteriormente solicita al vehiculo que se mueva a la siguiente
		//carretera de su intinerario (moveASiguienteCarretera).
		//Si existe lanza una excepcion.
		if(!mapaDeVehiculos.containsKey(idVehiculo)){
			vehiculos.add(vehiculo);
			mapaDeVehiculos.put(idVehiculo, vehiculo);
			vehiculo.moverASiguienteCarretera();
		}else{
			throw new ErrorDeSimulacion("El vehiculo ya existe en el mapa.");
		}
	}
	
	public void addCarretera(String idCarretera, Cruces origen, Carreteras carretera, Cruces destino) throws ErrorDeSimulacion{
		//Comprueba que "idCarretera" no existe en el mapa.
		//Si no existe, lo añade a "carreteras" y a "mapaDeCarreteras",
		// y posteriormente actualiza los cruces origen y destino como sigue:
		//	- Añade al cruce origen la carretera, como "carretera saliente"
		//	- Añade al cruce destino la carretera como "carretera entrante"
		//Si existe lanza una excepcion
		if(!mapaDeCarreteras.containsKey(idCarretera)){
			carreteras.add(carretera);
			mapaDeCarreteras.put(idCarretera, carretera);
			origen.addCarreteraSalienteAlCruce(origen, carretera);
			destino.addCarreteraEntranteAlCruce(idCarretera, carretera);
		}else{
			throw new ErrorDeSimulacion("La carretera ya existe en el mapa.");
		}
	}
	
	public String generateReport(int time){
		String report="";
		//genera informe para cruce
		//genera informe para carreteras
		//genera informe para vehiculos
		for(Cruces cruce: cruces){
			report=report+cruce.generaInforme(time)+"\n";
		}
		
		for(Carreteras carretera: carreteras){
			report=report+carretera.generaInforme(time)+"\n";
		}
		
		for(Vehiculos vehiculo: vehiculos){
			report=report+vehiculo.generaInforme(time)+"\n";
		}
		return report;
	}
	
	public void actualizar() throws ErrorDeSimulacion{
		//llama al metodo avanza de cada cruce
		//llama al metodo avanza de cada carretera
		for(Carreteras carretera: carreteras){
			carretera.avanza();
		}
		
		for(Cruces cruce: cruces){
			cruce.avanza();
		}
	}
	
	public Cruces getCruce(String id) throws ErrorDeSimulacion{
		//devuelve el cruce con ese "id" utilizando el mapaDeCruces
		//sino existe el cruce lanza excepcion.
		if(this.mapaDeCruces.containsKey(id)){
			return mapaDeCruces.get(id);
		}else{
			throw new ErrorDeSimulacion("El cruce no existe en el mapa.");
		}
	}
	
	public Vehiculos getVehiculo(String id)throws ErrorDeSimulacion{
		//devuelve el vehiculo con ese "id" utilizando el mapaDeVehiculos
		//sino existe el vehiculo lanza excepcion.
		if(this.mapaDeVehiculos.containsKey(id)){
			return mapaDeVehiculos.get(id);
		}else{
			throw new ErrorDeSimulacion("El vehiculo no existe en el mapa.");
		}
	}
	
	public Carreteras getCarreteras(String id) throws ErrorDeSimulacion{
		//devuelve la carretera con ese "id" utilizando el mapaDeCarreters
		//sino existe la carretera lanza excepcion.
		if(this.mapaDeCarreteras.containsKey(id)){
			return mapaDeCarreteras.get(id);
		}else{
			throw new ErrorDeSimulacion("La carretera no existe en el mapa.");
		}
	}
}
