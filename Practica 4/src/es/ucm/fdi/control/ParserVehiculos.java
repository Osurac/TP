package es.ucm.fdi.control;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Vehiculos;

public class ParserVehiculos {

	public static List<Vehiculos> parseListaVehiculos(String[] vehiculos, MapaCarreteras mapa) throws ErrorDeSimulacion {
		List<Vehiculos> listaVehiculos=new ArrayList<Vehiculos>();
		for(int i=0;i<vehiculos.length;i++){
			listaVehiculos.add(mapa.getVehiculo(vehiculos[i]));
		}
		return listaVehiculos;
	}

}
