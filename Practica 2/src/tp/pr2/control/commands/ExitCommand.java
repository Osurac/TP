package tp.pr2.control.commands;

import tp.pr2.Controller;
import tp.pr2.Game;

public class ExitCommand extends Command{

	public ExitCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		// TODO Auto-generated method stub
		return null;
	}

}
