package es.ucm.fdi.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.control.Evento;
import es.ucm.fdi.exception.ErrorDeSimulacion;
import es.ucm.fdi.model.Carreteras;
import es.ucm.fdi.model.CruceGenerico;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.ObservadorSimuladorTrafico;
import es.ucm.fdi.model.Vehiculos;

public class DialogoInformes extends JDialog implements ObservadorSimuladorTrafico{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelBotones panelBotones;
	private PanelObjSim<Vehiculos> panelVehiculos;
	private PanelObjSim<Carreteras> panelCarreteras;
	private PanelObjSim<CruceGenerico<?>> panelCruces;
	public static char TECLALIMPIAR='c';
	private VentanaPrincipal mainWindow;
	
	public DialogoInformes(VentanaPrincipal mainWindow, Controlador ctrl){
		super();
		this.setTitle("Gererate Reports");
		this.mainWindow=mainWindow;
		ctrl.addObserver(this);
		this.initGUI();
	}
	
	private void initGUI(){
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		
		
		JPanel panelPrincipal=this.creaPanelPrincipal();
		this.setContentPane(panelPrincipal);
		
		JPanel panelCentral = this.createPanelCentral();
		panelPrincipal.add(panelCentral,BorderLayout.CENTER);
		this.createPanelSuperior(panelCentral);
		this.panelBotones = new PanelBotones(this);
		panelPrincipal.add(this.panelBotones,BorderLayout.SOUTH);
		
		InformationPanel panelInfo=new InformationPanel();
		panelPrincipal.add(panelInfo,BorderLayout.PAGE_START);
		this.setVisible(false);
		this.pack();
	}
	
	private JPanel creaPanelPrincipal(){
		JPanel panelPrincipal=new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		return panelPrincipal;
	}
	
	private JPanel createPanelCentral(){
		JPanel panelCentral=new JPanel();
		panelCentral.setLayout(new GridLayout(1,3));
		return panelCentral;
	}
	
	private void createPanelSuperior(JPanel panelCentral){
		JPanel panelSuperior=new JPanel();
		panelSuperior.setLayout(new BoxLayout(panelSuperior,BoxLayout.X_AXIS));
		this.panelVehiculos=new PanelObjSim<Vehiculos>("Vehiculos");
		panelSuperior.add(panelVehiculos);
		this.panelCarreteras=new PanelObjSim<Carreteras>("Carreteras");
		panelSuperior.add(panelCarreteras);
		this.panelCruces=new PanelObjSim<CruceGenerico<?>>("Cruces");
		panelSuperior.add(panelCruces);
		panelCentral.add(panelSuperior);
	}
	
	public void mostrar(){
		this.setVisible(true);
	}
	
	private void setMapa(MapaCarreteras mapa){
		this.panelVehiculos.setList(mapa.getVehiculos());
		this.panelCarreteras.setList(mapa.getCarreteras());
		this.panelCruces.setList(mapa.getCruces());
	}
	
	public List<Vehiculos> getVehiculosSeleccionados(){
		return this.panelVehiculos.getSelectedItems();
	}
	
	public List<Carreteras> getCarreterasSeleccionados(){
		return this.panelCarreteras.getSelectedItems();
	}
	
	public List<CruceGenerico<?>> getCrucesSeleccionados(){
		return this.panelCruces.getSelectedItems();
	}
	
	public void generaInformesConcretos(){
			List<CruceGenerico<?>> listaCruces=this.getCrucesSeleccionados();
			List<Carreteras> listaCarreteras=this.getCarreterasSeleccionados();
			List<Vehiculos> listaVehiculos=this.getVehiculosSeleccionados();
			String informes="";
			for(CruceGenerico<?> cruce:listaCruces){
				informes=informes+cruce.generaInforme(mainWindow.getTime())+"\n";
			}
			for(Carreteras carretera:listaCarreteras){
				informes=informes+carretera.generaInforme(mainWindow.getTime())+"\n";
			}
			for(Vehiculos vehiculo:listaVehiculos){
				informes=informes+vehiculo.generaInforme(mainWindow.getTime())+"\n";
			}
			mainWindow.guardarInformesConcretos(informes);
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		this.setMapa(map);
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.setMapa(mapa);
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.setMapa(mapa);
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.setMapa(mapa);
	}
}
