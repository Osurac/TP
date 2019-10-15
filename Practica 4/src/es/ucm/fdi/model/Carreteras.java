package es.ucm.fdi.model;

import java.util.Comparator;
import java.util.List;

import es.ucm.fdi.ini.IniSection;

public class Carreteras extends ObjetoSimulacion{
	private int longitud;
	private int velocidadMaxima;
	private Cruces cruceOrigen;
	private Cruces cruceDestino;
	private List<Vehiculos> vehiculos;
	
	private Comparator<Vehiculos> comparadorVehiculo;
	
	public Carreteras(String id, int length, int maxSpeed, Cruces src, Cruces dest){
		super(id);
		//se inicializan los atributos de acuerdo con los parametros.
		//se fija el orden entre los vehiculos: (inicia comparadorVehiculo)
		//	- la localizacion 0 es la menor
		this.longitud=length;
		this.velocidadMaxima=maxSpeed;
		this.cruceOrigen=src;
		this.cruceDestino=dest;
		this.comparadorVehiculo=new ComparadorVehiculos();
		this.vehiculos=new SortedArrayList<Vehiculos>(comparadorVehiculo);
	}
	
	@Override
	public void avanza(){
		//calcular velocidad base de la carretera
		//inicializar obstaculos a 0
		//Para cada vehiculo de la lista "vehiculos":
		//	1. Si el vehiculo esta averiado se incrementa el numero de obstaculos.
		//	2. Se fija la velocidad actual del vehiculo.
		//	3. Se pide al vehiculo que avance.
		//ordena la lista de vehiculos
		int velocidadBase=calcularVelocidadBase();
		int obstaculos=0;
		for(Vehiculos v: vehiculos){
			if(v.getTiempoDeInfraccion()>0){
				obstaculos++;
				v.avanza();
			}else{
				int factorReduccion=calcularFactorReduccion(obstaculos);
				v.setVelocidadActual(velocidadBase/factorReduccion);
				v.avanza();
			}
		}
		vehiculos.sort(comparadorVehiculo);
	}
	
	public void entraVehiculo(Vehiculos vehiculo){
		//Si el vehiculo no existe en la carretera, se añade a la lista de vehiculos y
		//se ordena la lista.
		//Si existe no se hace nada.
		if(!vehiculos.contains(vehiculo)){
			vehiculos.add(vehiculo);
			vehiculos.sort(comparadorVehiculo);
		}
	}
	
	public void saleVehiculo(Vehiculos vehiculo){
		//Elimina el vehiculo de la lista de vehiculos
		vehiculos.remove(vehiculo);
	}
	
	public void entraVehiculoAlCRuce(Vehiculos v){
		// añade el vehiculo al "cruceDestino" de la "carretera"
		cruceDestino.entraVehiculoAlCruce(this.id, v);
	}
	
	protected int calcularVelocidadBase(){
		return Math.min(this.velocidadMaxima,(this.velocidadMaxima/Math.max(this.vehiculos.size(),1))+1/1);
	}
	
	protected int calcularFactorReduccion(int obstaculos){
		if(obstaculos==0){
			return 1;
		}else{
			return 2;
		}
	}
	
	@Override
	protected String getNombreSeccion(){
		return "road_report";
	}
	
	@Override
	protected void completaDetallesSeccion(IniSection is){
		//crea "vehicles = (v1,10),(v2,10)"
		String values="";
		for(Vehiculos vehiculo: vehiculos){
			values=values+"("+vehiculo.getId()+","+vehiculo.getLocalizacion()+"),";
		}
		is.setValue("state",values);
	}

	public int getLength() {
		return this.longitud;
	}

	public Cruces getCruceOrigen() {
		return this.cruceOrigen;
	}

	public Cruces getCruceDestino() {
		return this.cruceDestino;
	}
}
