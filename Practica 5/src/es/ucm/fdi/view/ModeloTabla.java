package es.ucm.fdi.view;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.model.ObservadorSimuladorTrafico;

public abstract class ModeloTabla<T> extends DefaultTableModel implements ObservadorSimuladorTrafico {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String[] columnIds;
	protected List<T> lista;
	
	public ModeloTabla(String [] columnIdEventos,Controlador ctrl){
		this.lista=null;
		this.columnIds=columnIdEventos;
		ctrl.addObserver(this);
	}

	@Override
	public String getColumnName(int col){
		return this.columnIds[col];
	}
	
	@Override
	public int getColumnCount(){
		return this.columnIds.length;
	}
	
	@Override
	public int getRowCount(){
		return this.lista==null? 0:lista.size();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	public void setValueAt(int value){
		
	}
}
