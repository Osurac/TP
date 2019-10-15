package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

public class HelpCommand extends NoParamsCommand {
	//Clase que representa el comando help
	private final static String commandName="help";
	private final static String helpCommand="print this help message.";
	public HelpCommand(){
		super(commandName, helpCommand);
	}

	@Override
	public boolean execute(Game game) {
		//Presenta un texto con la ayuda de los comandos
		CommandParser.commandHelp();
		return false;
	}

	
}
