package es.ucm.fdi.exception;

public class ErrorDeSimulacion extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorDeSimulacion(String string) {
		System.out.println(string);
	}

}
