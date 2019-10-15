package es.ucm.fdi.model;

import java.util.Comparator;

public class ComparadorVehiculos implements Comparator<Vehiculos> {

	@Override
	public int compare(Vehiculos v1, Vehiculos v2) {
		if(v1.getLocalizacion()==v2.getLocalizacion()){
			return 0;
		}else if(v1.getLocalizacion()<v2.getLocalizacion()){
			return 1;
		}else{
			return -1;
		}
	}

}
