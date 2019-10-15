package es.ucm.fdi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class PanelBotones extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PanelBotones(DialogoInformes dialogoInformes) {
		super();
		
		JButton botonCancelar=new JButton("Cancel");
		botonCancelar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dialogoInformes.setVisible(false);
			}
		});
		this.add(botonCancelar);
		
		JButton botonGenerar=new JButton("Generate");
		botonGenerar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dialogoInformes.generaInformesConcretos();
				dialogoInformes.setVisible(false);
			}
		});
		this.add(botonGenerar);
	}
	
}
