package es.ucm.fdi.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelEditorEventos extends PanelAreaTexto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelEditorEventos(String titulo, String texto, boolean editable, VentanaPrincipal mainWindow){
		super(titulo,editable);
		this.setTexto(texto);
		//OPCIONAL
		PopUpMenu popUp=new PopUpMenu(mainWindow);
		this.areaTexto.add(popUp);
		this.areaTexto.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e){
				if(e.isPopupTrigger()&&areaTexto.isEnabled()){
					popUp.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			@Override
			public void mouseReleased(MouseEvent e){
				if(e.isPopupTrigger()&&areaTexto.isEnabled()){
					popUp.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
