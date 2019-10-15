package es.ucm.fdi.model;

import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;

public class Vehiculos extends ObjetoSimulacion{
	
	private Carreteras carretera;
	private int velocidadMaxima;
	private int velocidadActual;
	private int kilometraje;
	private int localizacion;
	private int tiempoAveria;
	private List<Cruces> intinerario;
	private boolean estEnCruce;
	private boolean haLlegado;
	
	
	public Vehiculos(String id, int velocidadMaxima, List<Cruces> iti) throws ErrorDeSimulacion{//aqui donde se va a meter el generico de cruces
		super(id);
		//comprobar que la velocidadMaxima es mayor o igual que 0, y
		//que el intinerario tiene al menos dos cruces
		//En caso de no cumplirse lo anterior, lanzar una excepcion
		
		//inicializar los atributos teniendo en cuenta los parametros.
		//al crear un vehiculo su "carretera" sera iicialmente "null".
		if(velocidadMaxima<0){
			throw new ErrorDeSimulacion("La velocidad maxima es negativa.");
		}else{
			this.velocidadMaxima=velocidadMaxima;
		}
		
		if(iti.size()<2){
			throw new ErrorDeSimulacion("El intinerario contiene menos de dos cruces.");
		}else{
			this.intinerario=iti;
		}
		this.carretera=null;
		this.velocidadActual=0;
		this.kilometraje=0;
		this.localizacion=0;
		this.tiempoAveria=0;
		this.estEnCruce=false;
		this.haLlegado=false;
		
	}
	
	public int getLocalizacion(){
		return this.localizacion;
	}
	
	public int getTiempoDeInfraccion(){
		return this.tiempoAveria;
	}
	
	public void setVelocidadActual(int velocidad){
		//Si "velocidad" es negativa, entonces la "velocidadActual" es 0.
		//Si "velocidad" excede a "velocidadMaxima", entonces la
		//"velocidadActual" es "velocidadMaxima"
		//En otro caso, "velocidadActual" es velocidad
		if(velocidad<0){
			this.velocidadActual=0;
		}
		else if(velocidad > this.velocidadMaxima){
			this.velocidadActual = this.velocidadMaxima;
		}else{
			this.velocidadActual = velocidad;
		}
	}

	public void setTiempoAveria(Integer duracionAveria){
		//Comprobar que "carretera" no es null.
		//Se fija el tiempo de averia de acuerdo con el enunciado
		//Si el tiempo de averia es finalmente positivo, entonces
		//la "velocidadActual" se pone a 0
		if(this.carretera!=null){
			this.tiempoAveria=this.tiempoAveria+duracionAveria;
			if(this.tiempoAveria>0){
				setVelocidadActual(0);
			}
		}	
	}
	
	@Override
	protected void completaDetallesSeccion(IniSection is){
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" : this.carretera.id+":"+this.getLocalizacion());
	}
	
	public void avanza(){
		//si el coche esta averiado, decrementar tiempoAveria
		//si el coche esta esperando en un cruce, no se hace nada.
		//en otro caso:
		//	1. Actualizar su "localizacion"
		//	2. Actualizar su "kilometraje".
		//	3. Si el coche ha llegado a un cruce (localizacion >= carretera.getLength())
		//		3.1. Poner la localizacion igual a la longitud de la carretera
		//		3.2. Corregir el kilometraje.
		//		3.3. Indicar a la carretera que el vehiculo entra al cruce.
		//		3.4. Marcar que este vehiculo esta en un cruce(this.estEnCruce=true)
		if(this.tiempoAveria>0){
			this.tiempoAveria--;
		}else if(!this.estEnCruce){
			this.localizacion=this.localizacion+this.velocidadActual;
			this.kilometraje=this.kilometraje+this.velocidadActual;
			if(this.localizacion>=this.carretera.getLength()){
				this.kilometraje=this.kilometraje-(this.localizacion-this.carretera.getLength());
				this.localizacion=this.carretera.getLength();
				carretera.entraVehiculoAlCRuce(this);
				this.estEnCruce=true;
			}
		}
	}
	
	public void moverASiguienteCarretera() throws ErrorDeSimulacion{
		//Si la carretera no es null, sacar el vehiculo de la carretera.
		//Si hemos llegado al ultimo cruce del intinerario, entonces:
		//	1. Se marca que el vehiculo haLlegado (this.haLlegado=true).
		//	2. Se pone su carretera a null.
		//	3. Se pone su "velocidadActual" y "localizacion" a 0.
		//y se marca que el vehiculo esta en un cruce (this.estaEnCRuce=true).
		//En otro caso:
		//	1. Se calcula la siguiente carretera a la que tiene que ir.
		//	2. Si dicha carretera no existe, se lanza excepcion.
		//	3. En otro caso, se introduce el vehiculo en la carretera.
		//	4. Se inicializa su localizacion.
		if(this.carretera!=null){
			carretera.saleVehiculo(this);
		}
		if(this.intinerario.size()==1){
			this.haLlegado=true;
			this.carretera=null;
			setVelocidadActual(0);
			this.localizacion=0;
			this.estEnCruce=true;
		}else{
			Cruces cruce1=this.intinerario.get(0);
			Cruces cruce2=this.intinerario.get(1);
			this.intinerario.remove(0);
			Carreteras carretera=cruce1.carreteraQueVaCruce(cruce2);
			if(carretera!=null){
				carretera.entraVehiculo(this);
				this.carretera=carretera;
				this.localizacion=0;
				this.estEnCruce=false;
			}else{
				throw new ErrorDeSimulacion("No hay ninguna carretera que vaya a ese cruce.");
			}
		}
	}
	
	@Override
	protected String getNombreSeccion(){
		return "vehicle_report";
	}
	
}
