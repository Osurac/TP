package tp.pr3.control.commands;

import tp.pr3.logic.multigames.*;

public enum GameType {
	//Clase que contiene los enumerados con los distintos tipos de juegos con sus reglas
	ORIG{
		@Override
		public GameRules createRules(){
			return new Rules2048();
		}
	}, FIB{
		@Override
		public GameRules createRules(){
			return new RulesFib();
		}
		
	}, INV{
		@Override
		public GameRules createRules(){
			return new RulesInverse();
		}
	};
	public abstract GameRules createRules();
	
}
