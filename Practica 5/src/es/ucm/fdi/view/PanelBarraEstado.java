package es.ucm.fdi.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.SwingUtilities;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.ObservadorSimuladorTrafico;

public class PanelBarraEstado extends JPanel implements ObservadorSimuladorTrafico{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel infoEjecucion;
	
	public PanelBarraEstado(String mensaje, Controlador controlador){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.infoEjecucion=new JLabel(mensaje);
		this.add(this.infoEjecucion);
		this.setBorder(BorderFactory.createBevelBorder(1));
		controlador.addObserver(this);
	}

	public void setMensaje(String mensaje){
		this.infoEjecucion.setText(mensaje);
	}
	
	@Override
	public void avanza(int tiempo, MapaCarreteras mapa,List<Evento>evetos){
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				infoEjecucion.setText("Simulador ejecutado "+tiempo+" pasos.");
			}
		});*/
		infoEjecucion.setText("Simulador ejecutado "+tiempo+" pasos.");
	}
	
	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa,List<Evento>eventos){
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				infoEjecucion.setText("Simulador reiniciado");
			}
		});*/
		infoEjecucion.setText("Simulador reiniciado");
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map,List<Evento> eventos, ErrorDeSimulacion e) {
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				infoEjecucion.setText(e.getMessage());
			}
		});*/
		infoEjecucion.setText(e.getMessage());
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				infoEjecucion.setText("Evento añadidos al simulador");
			}
		});*/
		infoEjecucion.setText("Evento añadidos al simulador");
	}
}
