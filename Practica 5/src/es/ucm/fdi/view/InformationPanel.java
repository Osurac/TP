package es.ucm.fdi.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InformationPanel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel linea1;
	JLabel linea2;
	JLabel linea3;
	public InformationPanel(){
		super();
		JPanel panelInformacion=new JPanel();
		panelInformacion.setLayout(new GridLayout(3,1));
		this.linea1=new JLabel("Select items for whitch you want to generate report.",SwingConstants.LEFT);
		this.linea2=new JLabel("Use 'c' to deselect all.",SwingConstants.LEFT);
		this.linea3=new JLabel("Use Ctrl+A to select all",SwingConstants.LEFT);
		panelInformacion.add(linea1);
		panelInformacion.add(linea2);
		panelInformacion.add(linea3);
		this.add(panelInformacion);
	}
	

}
