package tp.pr3.control.exceptions;

public class CommandNoMatch extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandNoMatch(String mensaje){
		
		super(mensaje);
	}

}
