package es.ucm.fdi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.Carreteras;
import es.ucm.fdi.model.CruceGenerico;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.ObservadorSimuladorTrafico;
import es.ucm.fdi.model.Vehiculos;
import es.ucm.fdi.view.swing.grafico.ComponenteMapa;

public class VentanaPrincipal extends JFrame implements ObservadorSimuladorTrafico {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private Controlador controlador;
	public static Border bordePorDefecto=BorderFactory.createLineBorder(Color.BLACK, 2);
	
	//SUPERIOR PANEL
	static private final String[] columnIdEventos={"#","Tiempo","Tipo"};
	private PanelAreaTexto panelEditorEventos;
	private PanelAreaTexto panelInformes;
	private PanelTabla<Evento> panelColaEventos;
	
	//MENU AND TOOL BAR
	private JFileChooser fc;
	private ToolBar toolbar;
	private BarraMenu menubar;
	
	//GRAPHIC PANEL
	private ComponenteMapa componenteMapa;
	
	//STATUS BAR (INFO AT THE BOTTOM OF THE WINDOW)
	private PanelBarraEstado panelBarraEstado;
	
	//INFERIOR PANEL
	static private final String[] columnIdVehiculo ={"ID","Carretera","Localizacion","Vel.","Km","Tiempo Ave.","Itinerario"};
	static private final String[] columnIdCarretera={"ID","Origen","Destino","Longitud","Vel. Max","Vehiculos"};
	static private final String[] columnIdCruce={"ID","Verde","Rojo"};
	
	private PanelTabla<Vehiculos> panelVehiculos;
	private PanelTabla<Carreteras> panelCarreteras;
	private PanelTabla<CruceGenerico<?>> panelCruces;
	
	//REPORT DIALOG
	private DialogoInformes dialogoInformes;//opcional
	
	//MODEL PART - VIEW CONTROLLER MODEL
	private File ficheroActual;
	private Controlador controlador;
	//private Thread hilo=null;
	
	public VentanaPrincipal(String ficheroEntrada,Controlador ctrl)throws FileNotFoundException{
		super("Simulador de Trafico");
		this.controlador=ctrl;
		this.ficheroActual=ficheroEntrada !=null ? new File(ficheroEntrada):null;
		this.initGUI();
		//añadimos la ventana principal como observadora
		ctrl.addObserver(this);
	}
	
	private void initGUI(){
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener(){
			//al salir pide confirmacion
			@Override
			public void windowClosing(WindowEvent arg0){
				salir();
			}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowOpened(WindowEvent e) {}
		});
		
		JPanel panelPrincipal=this.creaPanelPrincipal();
		this.setContentPane(panelPrincipal);
		
		//BARRA DE ESTADO INFERIOR
		//(contiene una JLabel para mostrar el estado del simulador)
		this.addBarraEstado(panelPrincipal);
		
		//PANEL QUE CONTIENE EL RESTO DE COMPONENTES
		//(Lo dividimos en dos paneles (superior e inferior)
		JPanel panelCentral = this.createPanelCentral();
		panelPrincipal.add(panelCentral,BorderLayout.CENTER);
		
		//PANEL SUPERIOR
		this.createPanelSuperior(panelCentral);
		
		//MENU
		menubar =new BarraMenu(this,this.controlador);
		this.setJMenuBar(menubar);
		
		//PANEL INFERIOR
		this.createPanelInferior(panelCentral);
		
		//BARRA DE HERRAMIENTAS
		this.addToolBar(panelPrincipal);
		
		//FILE CHOOOSER
		this.fc=new JFileChooser("src/resources");
		
		//REPORT DIALOG (OPCIONAL)
		this.dialogoInformes=new DialogoInformes(this,this.controlador);
		this.pack();
		this.setVisible(true);
	}
	
	public void salir() {
		int n=JOptionPane.showOptionDialog(this, "Estas seguro de que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(n==0){
			System.exit(0);
		}
		
	}
	
	private JPanel creaPanelPrincipal(){
		JPanel panelPrincipal=new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		return panelPrincipal;
	}
	
	private JPanel createPanelCentral(){
		JPanel panelCentral=new JPanel();
		//para colocar el panel superior e inferior
		panelCentral.setLayout(new GridLayout(2,1));
		return panelCentral;
	}
	
	private void createPanelSuperior(JPanel panelCentral){
		JPanel panelSuperior=new JPanel();
		panelSuperior.setLayout(new BoxLayout(panelSuperior,BoxLayout.X_AXIS));
		
		String texto="";
		String titulo="Eventos: ";
		
		if(this.ficheroActual!=null){
			try{
				texto=this.leeFichero(this.ficheroActual);
				titulo=this.ficheroActual.getName();
				this.panelBarraEstado.setMensaje("Fichero "+ficheroActual.getName()+" de eventos cargado dentro del editor de eventos.");
			}catch(FileNotFoundException e){
				this.ficheroActual=null;
				this.muestraDialogoError("Error durante la lectura del fichero: "+e.getMessage());
			}
		}
		
		//event editor
		this.panelEditorEventos=new PanelEditorEventos(titulo,texto,true,this);
		panelSuperior.add(panelEditorEventos);
		
		//event queue editor
		this.panelColaEventos=new PanelTabla<Evento>("Cola Eventos:",new ModeloTablaEventos(columnIdEventos,this.controlador));
		panelSuperior.add(panelColaEventos);
		
		//report area
		this.panelInformes=new PanelInformes("Informes: ",false,this.controlador);
		panelSuperior.add(panelInformes);
		
		panelCentral.add(panelSuperior);
	}
	
	private void createPanelInferior(JPanel panelCentral){
		JPanel panelInferior=new JPanel();
		panelInferior.setLayout(new GridLayout(1,2));
		panelInferior.setLayout(new BoxLayout(panelInferior,BoxLayout.X_AXIS));
		JPanel panelTablas=new JPanel();
		panelTablas.setLayout(new GridLayout(3,1));
		panelTablas.setLayout(new BoxLayout(panelTablas,BoxLayout.Y_AXIS));
		
		
		this.panelVehiculos=new PanelTabla<Vehiculos>("Vehiculos", new ModeloTablaVehiculos(VentanaPrincipal.columnIdVehiculo,this.controlador));
		panelTablas.add(this.panelVehiculos);
		this.panelCarreteras=new PanelTabla<Carreteras>("Carreteras", new ModeloTablaCarreteras(VentanaPrincipal.columnIdCarretera,this.controlador));
		panelTablas.add(this.panelCarreteras);
		this.panelCruces=new PanelTabla<CruceGenerico<?>>("Cruces", new ModeloTablaCruces(VentanaPrincipal.columnIdCruce,this.controlador));
		panelTablas.add(this.panelCruces);
		
		panelInferior.add(panelTablas);
		
		this.componenteMapa=new ComponenteMapa(this.controlador);
		//añadir un scroolPane al panel inferior donde se coloca la componente.
		panelInferior.add(new JScrollPane(this.componenteMapa,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		panelCentral.add(panelInferior);
	}
	
	private void addBarraEstado(JPanel panelPrincipal){
		this.panelBarraEstado=new PanelBarraEstado("Bienvenido al simulador!",this.controlador);
		panelPrincipal.add(this.panelBarraEstado,BorderLayout.PAGE_END);
	}
	
	private void addToolBar(JPanel panelPrincipal){
		this.toolbar=new ToolBar(this,this.controlador);
		panelPrincipal.add(toolbar,BorderLayout.PAGE_START);
	}
	
	public void cargaFichero(){
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(".ini","ini");
		this.fc.setFileFilter(filtro);
		int returnVal=this.fc.showOpenDialog(null);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File fichero=this.fc.getSelectedFile();
			try{
				String s=leeFichero(fichero);
				this.controlador.reinicia();
				this.ficheroActual=fichero;
				this.panelEditorEventos.setTexto(s);
				this.panelEditorEventos.setBorde(this.ficheroActual.getName());
				this.panelBarraEstado.setMensaje("Fichero "+fichero.getName()+" de eventos cargado dentro del editor de eventos.");			
			}catch(FileNotFoundException e){
				this.muestraDialogoError("Error durante la lectura del fichero: "+e.getMessage());
			}
		}
	}
	
	public void guardarFichero() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(".ini","ini");
		this.fc.setFileFilter(filtro);
		int returnVal=this.fc.showSaveDialog(null);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File file=fc.getSelectedFile();
			writeFile(file,this.panelEditorEventos.getTexto());
			this.panelBarraEstado.setMensaje("Fichero de eventos guardado dentro del fichero "+file.getName());
		}
	}
	
	public void guardarFicheroInformes() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(".out","out");
		this.fc.setFileFilter(filtro);
		int returnVal=this.fc.showSaveDialog(null);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File file=fc.getSelectedFile();
			writeFile(file,this.panelInformes.getTexto());
			this.panelBarraEstado.setMensaje("Informes de simulacion guardados dentro del fichero "+file.getName());
		}
	}
	
	public void guardarInformesConcretos(String informes) {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(".out","out");
		this.fc.setFileFilter(filtro);
		int returnVal=this.fc.showSaveDialog(null);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File file=fc.getSelectedFile();
			writeFile(file,informes);
			this.panelBarraEstado.setMensaje("Informes de simulacion concretos guardados dentro del fichero "+file.getName());
		}
	}
	
	
	private String leeFichero(File fichero)throws FileNotFoundException{
		String s="";
		try{
			s=new Scanner(fichero).useDelimiter("\\A").next();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return s;
	}
	
	private void writeFile(File file,String content){
		try{
			PrintWriter pw=new PrintWriter(file);
			pw.print(content);
			pw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void limpiarInformes() {
		this.panelInformes.limpiar();
		this.panelBarraEstado.setMensaje("Area de informes limpiada.");
	}
	
	public void limpiarEventos() {
		this.panelEditorEventos.limpiar();
		this.panelEditorEventos.setBorde("Eventos: ");
		this.panelBarraEstado.setMensaje("Area de editor de eventos limpiada.");
	}
	
	private void muestraDialogoError(String mensaje){
		JOptionPane.showMessageDialog(this,mensaje,"Error", DO_NOTHING_ON_CLOSE, null);
	}
	
	public void generaInformes() {
		this.dialogoInformes.mostrar();
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map,List<Evento> eventos, ErrorDeSimulacion e) {
		this.muestraDialogoError(e.getMessage());
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {}

	public int getSteps() {
		return this.toolbar.getPasos();
	}
	
	/*public int getDelay() {
		return this.toolbar.getRetraso();
	}*/
	
	public int getTime(){
		return this.toolbar.getTime();
	}

	public String getTextoEditorEventos() {
		return this.panelEditorEventos.getTexto();
	}

	public void setMensaje(String texto) {
		this.panelBarraEstado.setMensaje(texto);
		
	}

	public void inserta(String string) {
		this.panelEditorEventos.inserta("\n"+string);
	}

	public void ejecutarSimulacion(){
		/*if(hilo==null){
			hilo=new Thread(){
				@Override
				public void run() {
					toolbar.habilitarBotones(false);
					menubar.habilitarMenu(false);
					try{
						for(int i=0;i<(int) getSteps()&& !Thread.interrupted();i++){
							controlador.ejecuta(1);
							Thread.sleep((int)getDelay());
						}
						hilo=null;
						toolbar.habilitarBotones(true);
						menubar.habilitarMenu(true);
					}catch(ErrorDeSimulacion e){
						hilo=null;
						toolbar.habilitarBotones(true);
						menubar.habilitarMenu(true);
					} catch (IOException e) {
						hilo=null;
						toolbar.habilitarBotones(true);
						menubar.habilitarMenu(true);
					} catch (InterruptedException e) {
						hilo=null;
						toolbar.habilitarBotones(true);
						menubar.habilitarMenu(true);
					}
				}
			};
			hilo.start();
		}*/
		try {
			controlador.ejecuta((int) getSteps());
		} catch (ErrorDeSimulacion | IOException e) {
			
		}
	}
	
	/*public void pararSimulacion(){
		if(hilo!=null){
			hilo.interrupt();
		}
	}*/
	

	
}
