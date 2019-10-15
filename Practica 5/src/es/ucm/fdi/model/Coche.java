package es.ucm.fdi.model;

import java.util.List;
import java.util.Random;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;

public class Coche extends Vehiculos{

	protected int kmUltimaAveria;
	protected int resistenciaKm;
	protected int duracionMaximaAveria;
	protected double probabilidadDeAveria;
	protected Random numAleatorio;
	
	public Coche(String id, int velocidadMaxima, List<CruceGenerico<?>> iti, int resistenciaKm, int duracionMaximaAveria, double probabilidadDeAveria,long semilla) throws ErrorDeSimulacion{
		super(id, velocidadMaxima, iti);
		this.kmUltimaAveria=0;
		this.resistenciaKm=resistenciaKm;
		this.duracionMaximaAveria=duracionMaximaAveria;
		this.probabilidadDeAveria=probabilidadDeAveria;
		this.numAleatorio=new Random(semilla);
	}
	
	@Override
	public void avanza(){
		//Si el coche esta averiado poner "kmUltimaAveria" a "kilometraje"
		//sino el coche no esta averiado y ha recorrido "resistenciakm", y ademas
		//"numAleatorio".nextDouble()<"probabilidadDeAveria", entonces
		//incrementar "tiempoAveria" con "numAleatorio.nextInt("duracionMaximaAveria")+1
		//Llamar a super.avanza();
		if(this.getTiempoDeInfraccion()>0){
			this.kmUltimaAveria=this.kilometraje;
		}else if((this.kilometraje-this.kmUltimaAveria)>this.resistenciaKm && this.numAleatorio.nextDouble()<this.probabilidadDeAveria){
			this.setTiempoAveria(this.numAleatorio.nextInt(this.duracionMaximaAveria)+1);
		}
		super.avanza();
	}

	@Override
	protected void completaDetallesSeccion(IniSection is){
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" : "("+this.carretera.id+","+this.getLocalizacion()+")");
		is.setValue("type", "car");
	}
}
