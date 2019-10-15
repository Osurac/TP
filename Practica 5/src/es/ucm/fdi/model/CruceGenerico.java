package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.exception.ErrorDeSimulacion;

abstract public class CruceGenerico<T extends CarreteraEntrante> extends ObjetoSimulacion {
	
	protected int indiceSemaforoVerde;
	protected List<T> carreterasEntrantes;
	protected List<Carreteras> carreterasSalientes;
	protected Map<String,T> mapaCarreterasEntrantes;
	protected Map<CruceGenerico<?>,Carreteras> mapaCarreterasSalientes;
	
	public CruceGenerico(String id){
		super(id);
		this.carreterasEntrantes=new ArrayList<T>();
		this.carreterasSalientes=new ArrayList<Carreteras>();
		this.mapaCarreterasSalientes=new HashMap<CruceGenerico<?>,Carreteras>();
		this.mapaCarreterasEntrantes=new HashMap<String,T>();
		this.indiceSemaforoVerde=-1;
	}
	
	public Carreteras carreteraHaciaCruce(CruceGenerico<?> cruce){
		for(Carreteras carretera: carreterasSalientes){
			CruceGenerico<?> cruceOrigen=carretera.getCruceOrigen();
			CruceGenerico<?> cruceDestino=carretera.getCruceDestino();
			if(this.equals(cruceOrigen)&&cruce.equals(cruceDestino)){
				return carretera;
			}
		}
		return null;
	}
	
	public void addCarreteraEntranteAlCruce(String idCarretera, Carreteras carretera){
		T ri=creaCarreteraEntrante(carretera);
		this.carreterasEntrantes.add(ri);
		this.mapaCarreterasEntrantes.put(idCarretera, ri);
	}
	
	abstract protected T creaCarreteraEntrante(Carreteras carretera);
	
	public void addCarreteraSalienteAlCruce(CruceGenerico<?> destino, Carreteras carr){
		this.carreterasSalientes.add(carr);
		this.mapaCarreterasSalientes.put(destino, carr);
	}
	
	public void entraVehiculoAlCruce(String idCarretera, Vehiculos vehiculo){
		CarreteraEntrante carreteraEntrante=this.mapaCarreterasEntrantes.get(idCarretera);
		for(CarreteraEntrante c: this.carreterasEntrantes){
			if(c.getcarretera().id==carreteraEntrante.getcarretera().getId()){
				c.anadirVehiculoALaCola(vehiculo);//preguntar
			}
		}
	}
	
	@Override
	public void avanza() throws ErrorDeSimulacion{
		CarreteraEntrante carreteraEntrante;
		if(!carreterasEntrantes.isEmpty()){
			if(this.indiceSemaforoVerde!=-1){
				carreteraEntrante=carreterasEntrantes.get(indiceSemaforoVerde);
				carreteraEntrante.avanzaPrimerVehiculo();
			}
			this.actualizaSemaforos();
		}
	}
	
	abstract protected void actualizaSemaforos();
	
	public Carreteras carreteraHaciaCruce(Cruces cruce){
		for(Carreteras carretera: carreterasSalientes){
			CruceGenerico<?> cruceOrigen=carretera.getCruceOrigen();
			CruceGenerico<?> cruceDestino=carretera.getCruceDestino();
			if(this.equals(cruceOrigen)&&cruce.equals(cruceDestino)){
				return carretera;
			}
		}
		return null;
	}
	
	public Carreteras carreteraQueVaCruce(CruceGenerico<?> cruce){
		return this.carreteraHaciaCruce(cruce);
	}
	
	public List<T> getCarreteras(){
		return this.carreterasEntrantes;
	}

	public abstract  String getCarreteraEntranteVerde();

	public abstract String getCarreterasEntranteRojo();
}
