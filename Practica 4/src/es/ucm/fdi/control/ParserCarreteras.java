package es.ucm.fdi.control;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.Cruces;
import es.ucm.fdi.model.MapaCarreteras;

public class ParserCarreteras {

	public static List<Cruces> parseListaCruces(String[] intinerario,MapaCarreteras mapa) throws ErrorDeSimulacion {
		List<Cruces> listaCruces=new ArrayList<Cruces>();
		for(int i=0;i<intinerario.length;i++){
			listaCruces.add(mapa.getCruce(intinerario[i]));
		}
		return listaCruces;
	}

}
