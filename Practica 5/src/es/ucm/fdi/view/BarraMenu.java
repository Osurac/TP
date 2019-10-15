package es.ucm.fdi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
//import javax.swing.SwingUtilities;

import es.ucm.fdi.control.Controlador;

public class BarraMenu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu menuFicheros;
	private JMenu menuSimulador;
	private JMenu menuReport;
	public BarraMenu(VentanaPrincipal mainWindow,Controlador controlador){
		super();
		//MNEJO DE FICHEROS
		menuFicheros=new JMenu("Ficheros");
		this.add(menuFicheros);
		this.creaMenuFicheros(menuFicheros,mainWindow);
		//SIMULADOR
		menuSimulador=new JMenu("Simulador");
		this.add(menuSimulador);
		this.creaMenuSimulador(menuSimulador,controlador,mainWindow);
		//INFORMES
		menuReport=new JMenu("Informes");
		this.add(menuReport);
		this.creaMenuInforme(menuReport,mainWindow);
	}
	
	private void creaMenuFicheros(JMenu menu, VentanaPrincipal mainWindow){
		JMenuItem cargar=new JMenuItem("Carga Eventos");
		cargar.setMnemonic(KeyEvent.VK_L);
		cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.ALT_MASK));
		cargar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.cargaFichero();
			}
		});
		
		JMenuItem salvar=new JMenuItem("Guardar Eventos");
		salvar.setMnemonic(KeyEvent.VK_S);
		salvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
		salvar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.guardarFichero();
			}
		});
		
		menu.addSeparator();
		
		JMenuItem salvarInformes=new JMenuItem("Guardar Informes");
		salvarInformes.setMnemonic(KeyEvent.VK_R);
		salvarInformes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
		salvarInformes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.guardarFicheroInformes();
			}
		});
		
		menu.addSeparator();
		
		JMenuItem salir=new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_E);
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		salir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.salir();
			}
		});
		
		menu.add(cargar);
		menu.add(salvar);
		menu.addSeparator();
		menu.add(salvarInformes);
		menu.addSeparator();
		menu.add(salir);
	}
	
	private void creaMenuSimulador(JMenu menuSimulador,Controlador controlador, VentanaPrincipal mainWindow){
		JMenuItem ejecuta=new JMenuItem("Ejecuta");
		ejecuta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.ejecutarSimulacion();
			}
		});
		
		JMenuItem reinicia=new JMenuItem("Reinicia");
		reinicia.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				controlador.reinicia();
			}
		});
		
		menuSimulador.add(ejecuta);
		menuSimulador.add(reinicia);
		
		
	}
	
	private void creaMenuInforme(JMenu menuReport, VentanaPrincipal mainWindow){
		JMenuItem generaInformes=new JMenuItem("Generar");
		generaInformes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//OPCIONAL
				mainWindow.generaInformes();
			}
		});
		
		JMenuItem limpiarAreaInformes=new JMenuItem("Limpiar");
		limpiarAreaInformes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//OPCIONAL
				mainWindow.limpiarInformes();
			}
		});
		
		menuReport.add(generaInformes);
		menuReport.add(limpiarAreaInformes);
		
	}
	
	/*public void habilitarMenu(Boolean habilitar){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				menuFicheros.setEnabled(habilitar);
				menuSimulador.setEnabled(habilitar);
				menuReport.setEnabled(habilitar);
			}
		});
		
	}*/
	
	
}
