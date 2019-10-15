package es.ucm.fdi.control;

import es.ucm.fdi.ini.IniSection;

public abstract class ConstructorEventos {
	//cada clase dara los valores correspondientes a estos atributos en la constructora
	protected String etiqueta; //etiqueta de la entrada ("new road", etc...)
	protected String[] claves; //campos de la entrada ("time","vehicles",etc.)
	protected String[] valoresPorDefecto;
	
	
	public ConstructorEventos(){
		this.etiqueta=null;
		this.claves=null;
		this.valoresPorDefecto=null;
	}
	
	public abstract Evento parser(IniSection section);
	
	protected static String identificadorValido(IniSection seccion, String clave){
		String s=seccion.getValue(clave);
		if(!esIdentificadorValido(s)){
			throw new IllegalArgumentException("El valor "+s+" para "+clave+" no es un ID valido");
		}
		else{
			return s;
		}
	}
	
	protected static String[] identificadorValidoA(IniSection seccion, String clave){
		String s=seccion.getValue(clave);
		String[] sec=s.split(",");
		for(int i=0;i<sec.length;i++){
			if(!esIdentificadorValido(sec[i])){
				throw new IllegalArgumentException("El valor "+sec[i]+" para "+clave+" no es un ID valido");
			}
		}
		return sec;
	}
	
	private static boolean esIdentificadorValido(String id){
		return id!=null && id.matches("[a-z0-9_]+");
	}
	
	protected static int parseaInt(IniSection seccion,String clave){
		String v=seccion.getValue(clave);
		if(v==null){
			throw new IllegalArgumentException("Valor inexistente para la clave: "+ clave);
		}else{
			return Integer.parseInt(seccion.getValue(clave));
		}
	}
	
	protected static double parseaDouble(IniSection seccion,String clave){
		String v=seccion.getValue(clave);
		if(v==null){
			throw new IllegalArgumentException("Valor inexistente para la clave: "+ clave);
		}else{
			return Double.parseDouble(seccion.getValue(clave));
		}
	}
	
	protected static long parseaLong(IniSection seccion,String clave){
		String v=seccion.getValue(clave);
		if(v==null){
			throw new IllegalArgumentException("Valor inexistente para la clave: "+ clave);
		}else{
			return Long.parseLong(seccion.getValue(clave));
		}
	}
	
	protected static int parseaInt(IniSection seccion,String clave,int valorPorDefecto){
		String v=seccion.getValue(clave);
		return (v!=null)? Integer.parseInt(seccion.getValue(clave)):valorPorDefecto;
	}
	
	protected static int parseaIntNoNegativo(IniSection seccion, String clave, int valorPorDefecto){
		int i = ConstructorEventos.parseaInt(seccion, clave, valorPorDefecto);
		if( i < 0 ){
			throw new IllegalArgumentException("El valor " + i + " para " + clave + " no es válido");
		}
		else return i;
	}
	
	protected static int parseaIntNoNegativo(IniSection seccion, String clave){
		int i = ConstructorEventos.parseaInt(seccion, clave);
		if( i < 0 ){
			throw new IllegalArgumentException("El valor " + i + " para " + clave + " no es válido");
		}
		else return i;
	}
	
	protected static int parseaIntPositive(IniSection seccion, String clave){
		int i = ConstructorEventos.parseaInt(seccion, clave);
		if( i <= 0 ){
			throw new IllegalArgumentException("El valor " + i + " para " + clave + " no es válido");
		}
		else return i;
	}
	
	protected static double parseaDoubleNoNegativo(IniSection seccion, String clave){
		double i = ConstructorEventos.parseaDouble(seccion, clave);
		if( i < 0 ){
			throw new IllegalArgumentException("El valor " + i + " para " + clave + " no es válido");
		}
		else return i;
	}
	
	protected static double parseaDoublePositive(IniSection seccion, String clave){
		double i = ConstructorEventos.parseaDouble(seccion, clave);
		if( i <= 0 ){
			throw new IllegalArgumentException("El valor " + i + " para " + clave + " no es válido");
		}
		else return i;
	}
	
	protected static long parseaLongNoNegativo(IniSection seccion, String clave){
		long i = ConstructorEventos.parseaLong(seccion, clave);
		if( i < 0 ){
			throw new IllegalArgumentException("El valor " + i + " para " + clave + " no es válido");
		}
		else return i;
	}
	
	protected static long parseaLongPositive(IniSection seccion, String clave){
		long i = ConstructorEventos.parseaLong(seccion, clave);
		if( i <= 0 ){
			throw new IllegalArgumentException("El valor " + i + " para " + clave + " no es válido");
		}
		else return i;
	}

	public abstract String template();
}
