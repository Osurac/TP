package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;

public class CarreteraEntrante {
	private Carreteras carretera;
	private List<Vehiculos> colaVehiculos;
	private boolean semaforo;//true=verde, false=rojo
	
	public CarreteraEntrante(Carreteras carretera){
		//inicia los atributos
		//el semaforo a rojo
		this.carretera=carretera;
		this.colaVehiculos=new ArrayList<Vehiculos>();
		this.semaforo=false;
	}
	
	void ponSemaforo(boolean color){
		this.semaforo=color;
	}
	
	public void avanzaPrimerVehiculo() throws ErrorDeSimulacion{
		//coge el primer vehiculo de la cola, lo elimina,
		//y le manda que se mueva a su siguiente carretera.
		if(!colaVehiculos.isEmpty()){
			Vehiculos vehiculo= colaVehiculos.get(0);
			colaVehiculos.remove(0);
			vehiculo.moverASiguienteCarretera();
		}
	}
	
	@Override
	public String toString(){
		return null;
	}
	
	public Carreteras getcarretera(){
		return this.carretera;
	}

	public String getEstadoSmaforo() {
		if(semaforo){
			return "green";
		}else{
			return "red";
		}
	}
	
	public String getColaVehiculos(){
		String value="";
		for(Vehiculos vehiculo:colaVehiculos){
			value=value+vehiculo.getId()+",";
		}
		return value;
	}

	public void anadirVehiculoALaCola(Vehiculos vehiculo) {
		this.colaVehiculos.add(vehiculo);
	}
}
