package es.ucm.fdi.view;

import java.util.List;

//import javax.swing.SwingUtilities;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;

public class ModeloTablaEventos extends ModeloTabla<Evento>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModeloTablaEventos(String[] columnIdEventos,Controlador ctrl){
		super(columnIdEventos,ctrl);
	}

	@Override //necesario para que se visualicen los datos
	public Object getValueAt(int indiceFil,int indiceCol){
		Object s=null;
		switch(indiceCol){
			case 0: s=indiceFil;break;
			case 1: s=this.lista.get(indiceFil).getTimepo();break;
			case 2: s=this.lista.get(indiceFil).getType();break;
			default:assert (false);
		}
		return s;
	}
	
	@Override
	public void avanza(int tiempo, MapaCarreteras mapa,List<Evento> eventos){
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				lista=eventos;
				fireTableStructureChanged();
			}
		});*/
		lista=eventos;
		fireTableStructureChanged();
	}
	
	@Override
	public void addEvento(int tiempo,MapaCarreteras mapa,List<Evento>eventos){
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				lista=eventos;
				fireTableStructureChanged();
			}
		});*/
		lista=eventos;
		fireTableStructureChanged();
	}
	
	@Override
	public void reinicia(int tiempo,MapaCarreteras mapa,List<Evento> eventos){
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
}
