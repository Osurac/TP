package tp.pr3.control.commands;

import tp.pr3.control.exceptions.CommandNoMatch;
import tp.pr3.logic.multigames.*;

public class MoveCommand extends Command {
	//Clase que representa el comando move
	private final static String commandName="move";
	private final static String helpCommand="execute a move in one of the directions: up, down, left, right.";
	private Direction dir;
	
	public MoveCommand(){
		super(commandName, helpCommand);
	}
	
	public MoveCommand(String[] commandInfo) {
		//Crea el comando con el tipo de direccion
		super(commandInfo[0], helpCommand);
		if(commandInfo[1].equalsIgnoreCase("up")){
			dir=Direction.UP;
		}
		else if(commandInfo[1].equalsIgnoreCase("down")){
			dir=Direction.DOWN;
		}
		else if(commandInfo[1].equalsIgnoreCase("right")){
			dir=Direction.RIGHT;
		}
		else if(commandInfo[1].equalsIgnoreCase("left")){
			dir=Direction.LEFT;
		}
	}

	@Override
	public boolean execute(Game game) {
		//Ejecuta el movimiento con la direccion indicada
		game.move(dir);
		return true;
	}

	
	@Override
	public Command parse(String[] commandWords)throws CommandNoMatch  {
		//Parsea el comando para saber si es suyo y le devuelve con el tipo de direccion
		Command command=null;
		try{
		if(commandWords[0].equalsIgnoreCase(this.getCommandName())){
			if(commandWords.length>1){
				if(commandWords[1].equalsIgnoreCase("up") || commandWords[1].equalsIgnoreCase("down") || commandWords[1].equalsIgnoreCase("left") || commandWords[1].equalsIgnoreCase("right")){
					command=new MoveCommand(commandWords);
				}
				else{
					System.out.println("Unknown direction for move command.");
				}
			}
			else{
				System.out.println("Move must be followed by a direction: up, down, left, right.");
			}
		}else {
			CommandNoMatch exception = new CommandNoMatch("Unknown command. Use 'help' to see the available commands.");
			 throw exception;
		}
		}catch(CommandNoMatch e){
			 throw e;
		}
		return command;
		
	}
}