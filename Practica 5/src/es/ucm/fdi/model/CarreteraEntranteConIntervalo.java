package es.ucm.fdi.model;

import es.ucm.fdi.exception.ErrorDeSimulacion;

public class CarreteraEntranteConIntervalo extends CarreteraEntrante{
	
	private int intervaloDeTiempo; //Tiempo que ha de transcurrir para poner el semaforo de la carretera en rojo
	private int unidadesDeTiempoUsadas; //Se incrementa cada vez que avanza un vehiculo
	private boolean usoCompleto; //Controla que en cada paso con el semaforo en verde, ha pasado un vehiculo
	private boolean usadaPorUnVehiculo; //Controla que al menos ha pasado un vehiculo mientras el semaforo estaba en verde
	private int contadorVehiculos; //Controle el numero de vehiculos que han salido de la carretera entrante
	
	protected CarreteraEntranteConIntervalo(Carreteras carretera, int intervaloTiempo){
		super(carretera);
		this.intervaloDeTiempo=intervaloTiempo;
		this.unidadesDeTiempoUsadas=0;
		this.usoCompleto=false;
		this.usadaPorUnVehiculo=false;
		this.contadorVehiculos=0;
	}
	
	@Override
	public void avanzaPrimerVehiculo() throws ErrorDeSimulacion{
		//Incrementa unidadesDeTiempoUsadas
		//Actualiza usoCompleto:
		// Si "colaVehiculos" es vacia, entonces "usoCompleto=false"
		// En otro caso saca el primerVehiculo "v" de la "colaVehiculos"
		// y le mueve a la siguiente carretera ("v.moverASiguienteCarretera()")
		// Pone "usadaPorUnVehiculo" a true.
		this.unidadesDeTiempoUsadas++;
		if(this.getColaVehiculos().isEmpty()){
			this.usoCompleto=false;
		}else{
			Vehiculos v= colaVehiculos.get(0);
			colaVehiculos.remove(0);
			v.moverASiguienteCarretera();
			this.usadaPorUnVehiculo=true;
			this.contadorVehiculos++;
		}
		if(this.unidadesDeTiempoUsadas==this.contadorVehiculos){
			this.usoCompleto=true;
		}else{
			this.usoCompleto=false;
		}
	}
	
	public boolean tiempoConsumido(){
		//comprueba si se ha agotado el intervalo de tiempo.
		//"unidadesDeTiempoUsadas >= "intervaloDeTiempo"
		return this.unidadesDeTiempoUsadas>=this.intervaloDeTiempo;
	}
	
	public boolean usoCompleto(){
		return this.usoCompleto;
	}
	
	public boolean usada(){
		return this.usadaPorUnVehiculo;
	}

	public void setIntervaloDeTiempo(int i) {
		this.intervaloDeTiempo=i;
	}

	public void setUnidadesDeTiempoUsadas(int i) {
		this.unidadesDeTiempoUsadas=i;
	}

	public void setUsoCompleto(boolean b) {
		this.usoCompleto=b;
		
	}

	public void setUsadaPorUnVehiculo(boolean b) {
		this.usadaPorUnVehiculo=b;
		
	}

	public int getIntervaloTiempo() {
		return this.intervaloDeTiempo;
	}
	
	@Override
	public String toString(){
		return this.carretera.id;
	}

	public int getUnidadesDeTiempoUsadas() {
		return this.unidadesDeTiempoUsadas;
	}

	public void setContadorVehiculos(int contadorVehiculos) {
		this.contadorVehiculos=contadorVehiculos;
	}

}
