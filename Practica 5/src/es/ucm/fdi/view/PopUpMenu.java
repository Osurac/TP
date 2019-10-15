package es.ucm.fdi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import es.ucm.fdi.control.ConstructorEventos;
import es.ucm.fdi.control.ParserEventos;

public class PopUpMenu extends JPopupMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUpMenu(VentanaPrincipal mainWindow){
		JMenu plantillas=new JMenu("Nueva plantilla");
		this.add(plantillas);
		//añadir las opciones con sus listeners
		
		for(ConstructorEventos ce:ParserEventos.getConstructoresEventos()){
			JMenuItem mi=new JMenuItem(ce.toString());
			mi.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					mainWindow.inserta(ce.template()+System.lineSeparator());
				}
			});
			plantillas.add(mi);
		}
		
		this.addSeparator();
		
		JMenuItem cargar=new JMenuItem("Cargar");
		cargar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.cargaFichero();
			}
			
		});
		this.add(cargar);
		
		JMenuItem guardar=new JMenuItem("Guardar");
		guardar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.guardarFichero();;
			}
			
		});
		this.add(guardar);
		
		JMenuItem limpiar=new JMenuItem("Limpiar");
		limpiar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.limpiarEventos();
			}
			
		});
		this.add(limpiar);
		
	}
}
