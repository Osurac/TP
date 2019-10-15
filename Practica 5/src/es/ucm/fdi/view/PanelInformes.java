package es.ucm.fdi.view;

import java.util.List;

//import javax.swing.SwingUtilities;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.ObservadorSimuladorTrafico;

public class PanelInformes extends PanelAreaTexto implements ObservadorSimuladorTrafico{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelInformes(String titulo, boolean editable, Controlador ctrl){
		super(titulo,editable);
		ctrl.addObserver(this);//se añade como observador
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map,List<Evento> eventos, ErrorDeSimulacion e) {
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				limpiar();
			}
		});*/
		limpiar();
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				String informe=mapa.generateReport(tiempo);
				setTexto(getTexto()+informe);
			}
		});*/
		String informe=mapa.generateReport(tiempo);
		setTexto(getTexto()+informe);
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				limpiar();
			}
		});*/
		limpiar();
	}
}
