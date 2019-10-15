package es.ucm.fdi.view;

import java.util.List;

//import javax.swing.SwingUtilities;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Vehiculos;

public class ModeloTablaVehiculos extends ModeloTabla<Vehiculos>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModeloTablaVehiculos(String[] columnIdVehiculos,Controlador ctrl){
		super(columnIdVehiculos,ctrl);
	}
	
	@Override //necesario para que se visualicen los datos
	public Object getValueAt(int indiceFil,int indiceCol){
		Object s=null;
		switch(indiceCol){
			case 0: s=this.lista.get(indiceFil).getId();break;
			case 1: s=this.lista.get(indiceFil).getIdCarretera();break;
			case 2: s=this.lista.get(indiceFil).getLocalizacion();break;
			case 3: s=this.lista.get(indiceFil).getVelocidadActual();break;
			case 4: s=this.lista.get(indiceFil).getKilometraje();break;
			case 5: s=this.lista.get(indiceFil).getTiempoDeInfraccion();break;
			case 6: s=this.lista.get(indiceFil).getIntinerario();break;
			default:assert (false);
		}
		return s;
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map,List<Evento> eventos, ErrorDeSimulacion e) {

		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				lista=null;
				fireTableStructureChanged();
			}
		});*/
		lista=null;
		fireTableStructureChanged();
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				lista=mapa.getVehiculos();
				fireTableStructureChanged();
			}
		});*/
		lista=mapa.getVehiculos();
		fireTableStructureChanged();
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				lista=null;
				fireTableStructureChanged();
			}
		});*/
		lista=null;
		fireTableStructureChanged();
	}
}
