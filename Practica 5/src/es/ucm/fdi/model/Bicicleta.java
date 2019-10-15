package es.ucm.fdi.model;

import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.ini.IniSection;

public class Bicicleta extends Vehiculos{

	public Bicicleta(String id, int velocidadMaxima, List<CruceGenerico<?>> iti)throws ErrorDeSimulacion {
		super(id, velocidadMaxima, iti);
	}
	
	@Override
	public void setTiempoAveria(Integer duracionAveria){
		if(this.getCarretera()!=null){
			if(this.getVelocidadActual() >= this.getVelocidadMaxima()/2){
				this.tiempoAveria=this.tiempoAveria+duracionAveria;
				this.setVelocidadActual(0);
			}
		}	
	}

	@Override
	protected void completaDetallesSeccion(IniSection is){
		is.setValue("speed", this.velocidadActual);
		is.setValue("kilometrage", this.kilometraje);
		is.setValue("faulty", this.tiempoAveria);
		is.setValue("location", this.haLlegado ? "arrived" : "("+this.carretera.id+","+this.getLocalizacion()+")");
		is.setValue("type", "bike");
	}
}
