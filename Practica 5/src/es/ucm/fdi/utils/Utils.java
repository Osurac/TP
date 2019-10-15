package es.ucm.fdi.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Utils {

	public static Image loadImage(String imagen) {
		ImageIcon iconLoad=new ImageIcon(imagen);
		Image imagen1=iconLoad.getImage();
		Image otraimg1=imagen1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		return otraimg1;
	}

}
