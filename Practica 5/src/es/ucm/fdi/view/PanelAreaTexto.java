package es.ucm.fdi.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

abstract public class PanelAreaTexto extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextArea areaTexto;
	
	public PanelAreaTexto(String titulo, boolean editable){
		this.setLayout(new GridLayout(1,1));
		this.areaTexto=new JTextArea(40,30);
		this.areaTexto.setEditable(editable);
		this.add(new JScrollPane(areaTexto,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.setBorde(titulo);
	}
	
	public void setBorde(String titulo){
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2),titulo));
	}
	
	public String getTexto(){
		return areaTexto.getText();
	}
	
	public void setTexto(String texto){
		areaTexto.setText(texto);
	}
	
	public void limpiar(){
		areaTexto.setText(null);
	}
	
	public void inserta(String valor){
		this.areaTexto.insert(valor, this.areaTexto.getCaretPosition());
	}
	

}
