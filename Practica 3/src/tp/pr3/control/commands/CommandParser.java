package tp.pr3.control.commands;


public class CommandParser {
	//Clase que contiene el listado de comandos disponibles y los parsea
	private static Command[] availablerCommands = { new HelpCommand(), new ResetCommand(), new ExitCommand(), new MoveCommand(), new UndoCommand(),new RedoCommand(), new PlayCommand()};
	
	public static Command parseCommand(String[] commandWords) throws Exception{
		//Parsea el comando introducido con los de la lista
		Command command=null;
		for(Command com: availablerCommands){
			command=com.parse(commandWords);
			if(command != null){
				return command;
			}
		}
		return command;
	}
	public static void commandHelp(){
		//Imprime una linea con la informacion de cada comando
		for(Command com: availablerCommands){
			System.out.println(com.helpText());
		}
	}
}
