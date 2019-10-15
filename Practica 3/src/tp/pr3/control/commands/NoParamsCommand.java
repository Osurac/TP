package tp.pr3.control.commands;

public abstract class NoParamsCommand extends Command {
	//Clase que parsea el comando sin parametros
	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}
	
	@Override
	public Command parse(String[] commandWords) {
		//Comprueba que el comando introducido es correcto
		Command command=null;
		if(!(commandWords.length > 2)){
			if(commandWords[0].equalsIgnoreCase(this.commandName)){
				command = this;
			}
		}
		return command;
	}
}
