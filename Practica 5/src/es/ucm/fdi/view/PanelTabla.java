package es.ucm.fdi.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelTabla<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModeloTabla<T> modelo;
	
	public PanelTabla(String bordeId,ModeloTabla<T> modelo){
		this.setLayout(new GridLayout(1,1));
		this.modelo=modelo;
		JTable tabla = new JTable(this.modelo);
		this.add(new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2),bordeId)));
	}
}
