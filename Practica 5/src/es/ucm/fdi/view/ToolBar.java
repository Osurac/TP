package es.ucm.fdi.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
//import javax.swing.SwingUtilities;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.ObservadorSimuladorTrafico;
import es.ucm.fdi.utils.Utils;

public class ToolBar extends JToolBar implements ObservadorSimuladorTrafico{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JSpinner delay;
	private JSpinner steps;
	private JTextField time;
	private JButton botonCargar;
	private JButton botonSalvar;
	private JButton botonLimpiar;
	private JButton botonCheckIn;
	private JButton botonEjecutar;
	//private JButton stop;
	private JButton botonReiniciar;
	private JButton botonGeneraReports;
	private JButton botonBorrarReports;
	private JButton botonGuardarReports;
	private JButton botonSalir;
	
	public ToolBar(VentanaPrincipal mainWindow,Controlador controlador){
		super();
		controlador.addObserver(this);
		
		botonCargar=new JButton();
		botonCargar.setToolTipText("Carga un fichero de eventos");
		botonCargar.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/open.png")));
		botonCargar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.cargaFichero();
			}
		});
		this.add(botonCargar);
		
		botonSalvar=new JButton();
		botonSalvar.setToolTipText("Salvar un fichero de eventos");
		botonSalvar.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/save.png")));
		botonSalvar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.guardarFichero();
			}
		});
		this.add(botonSalvar);
		
		botonLimpiar=new JButton();
		botonLimpiar.setToolTipText("Limpiar la zona de eventos");
		botonLimpiar.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/clear.png")));
		botonLimpiar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.limpiarEventos();
			}
		});
		this.add(botonLimpiar);
		
		this.addSeparator();
		
		botonCheckIn=new JButton();
		botonCheckIn.setToolTipText("Carga los eventos al simulador");
		botonCheckIn.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/events.png")));
		botonCheckIn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					controlador.reinicia();
					byte[] contenido=mainWindow.getTextoEditorEventos().getBytes();
					controlador.cargaEventos(new ByteArrayInputStream(contenido));
					mainWindow.setMensaje("Eventos cargados al simulador!");
				}catch(ErrorDeSimulacion err){
					
				}
			}
		});
		this.add(botonCheckIn);
		
		botonEjecutar=new JButton();
		botonEjecutar.setToolTipText("Ejecutar el simulador");
		botonEjecutar.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/play.png")));
		botonEjecutar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.ejecutarSimulacion();
			}
		});
		this.add(botonEjecutar);
		
		
		/*stop=new JButton();
		stop.setToolTipText("Parar la simulacion");
		stop.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/stop.png")));
		stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.pararSimulacion();
			}
		});
		this.add(stop);*/
		
		botonReiniciar=new JButton();
		botonReiniciar.setToolTipText("Reiniciarlo");
		botonReiniciar.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/reset.png")));
		botonReiniciar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				controlador.reinicia();
			}
		});
		this.add(botonReiniciar);
		
		/*this.add(new JLabel("Delay: "));
		this.delay=new JSpinner(new SpinnerNumberModel(5,1,1000,1));
		this.delay.setToolTipText("Retraso en el tiempo");
		this.delay.setMaximumSize(new Dimension(70,70));
		this.delay.setMinimumSize(new Dimension(70,70));
		this.delay.setValue(1);
		this.add(delay);*/
		
		this.add(new JLabel("Pasos: "));
		this.steps=new JSpinner(new SpinnerNumberModel(5,1,1000,1));
		this.steps.setToolTipText("pasos a ejecutar: 1-1000");
		this.steps.setMaximumSize(new Dimension(70,70));
		this.steps.setMinimumSize(new Dimension(70,70));
		this.steps.setValue(1);
		this.add(steps);
		
		this.add(new JLabel("Tiempo: "));
		this.time=new JTextField("0",5);
		this.time.setToolTipText("Tiempo actual");
		this.time.setMaximumSize(new Dimension(70,70));
		this.time.setMinimumSize(new Dimension(70,70));
		this.time.setEditable(false);
		this.add(this.time);
		
		this.addSeparator();
		
		//OPCIONAL
		botonGeneraReports=new JButton();
		botonGeneraReports.setToolTipText("Generate informes");
		botonGeneraReports.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/report.png")));
		botonGeneraReports.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.generaInformes();
			}
		});
		this.add(botonGeneraReports);
		
		botonBorrarReports=new JButton();
		botonBorrarReports.setToolTipText("Limpiar el area de informes");
		botonBorrarReports.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/delete_report.png")));
		botonBorrarReports.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.limpiarInformes();
			}
		});
		this.add(botonBorrarReports);
		
		botonGuardarReports=new JButton();
		botonGuardarReports.setToolTipText("Guardar los informes");
		botonGuardarReports.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/save_report.png")));
		botonGuardarReports.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.guardarFicheroInformes();
			}
		});
		this.add(botonGuardarReports);
		
		botonSalir=new JButton();
		botonSalir.setToolTipText("Salir");
		botonSalir.setIcon(new ImageIcon(Utils.loadImage("src/resources/iconos/exit.png")));
		botonSalir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mainWindow.salir();
			}
		});
		this.add(botonSalir);
	}
	
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras mapa,List<Evento>eventos,ErrorDeSimulacion e){
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				time.setText("0");
			}
		});*/
		time.setText("0");
	}
	
	@Override
	public void avanza(int tiempo,MapaCarreteras mapa, List<Evento> eventos){
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				time.setText(""+tiempo);
			}
		});*/
		time.setText(""+tiempo);
	}
	
	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos){}
	
	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos){
		
		/*SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				steps.setValue(1);
				delay.setValue(1);
				time.setText("0");
			}
		});*/
		steps.setValue(1);
		time.setText("0");
	}

	public int getPasos() {
		return (int) this.steps.getValue();
	}
	
	/*public int getRetraso() {
		return (int) this.delay.getValue()*1000;
	}*/
	
	public int getTime(){
		return Integer.parseInt(this.time.getText());
	}
	
	/*public void habilitarBotones(boolean habititar){
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				botonCargar.setEnabled(habititar);
				botonSalvar.setEnabled(habititar);
				botonLimpiar.setEnabled(habititar);
				botonCheckIn.setEnabled(habititar);
				botonEjecutar.setEnabled(habititar);
				botonReiniciar.setEnabled(habititar);
				botonGeneraReports.setEnabled(habititar);
				botonBorrarReports.setEnabled(habititar);
				botonGuardarReports.setEnabled(habititar);
				botonSalir.setEnabled(habititar);
			}
		});
		
	}*/
}
