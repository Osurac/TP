package es.ucm.fdi.view;

import java.util.List;

//import javax.swing.SwingUtilities;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.Carreteras;
import es.ucm.fdi.model.MapaCarreteras;

public class ModeloTablaCarreteras extends ModeloTabla<Carreteras>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ModeloTablaCarreteras(String[] columnIdCarreteras,Controlador ctrl){
		super(columnIdCarreteras,ctrl);
	}
	

	@Override //necesario para que se visualicen los datos
	public Object getValueAt(int indiceFil,int indiceCol){
		Object s=null;
		switch(indiceCol){
			case 0: s=this.lista.get(indiceFil).getId();break;
			case 1: s=this.lista.get(indiceFil).getCruceOrigen().getId();break;
			case 2: s=this.lista.get(indiceFil).getCruceDestino().getId();break;
			case 3: s=this.lista.get(indiceFil).getLongitud();break;
			case 4: s=this.lista.get(indiceFil).getVelocidadMaxima();break;
			case 5: s=this.lista.get(indiceFil).getListaVehiculos();break;
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
				lista=mapa.getCarreteras();
				fireTableStructureChanged();
			}
		});*/
		lista=mapa.getCarreteras();
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
