package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.control.Controller;
import tp.pr3.logic.multigames.Game;

public class PlayCommand extends Command {
	//Clase que representa el comando play
	private final static String commandName="play";
	private final static String helpCommand="start a new game of one of the game types: original, fib, inverse.";
	protected int randomSeed, boardSize, initialCells;
	protected GameType gameType;
	
	public PlayCommand(){
		super(commandName, helpCommand);
	}
	
	public PlayCommand(String[] commandInfo) {
		//Crea el comando con el tipo de juego
		super(commandInfo[0], helpCommand);
		if(commandInfo[1].equalsIgnoreCase("original")){
			gameType=GameType.ORIG;
		}
		else if(commandInfo[1].equalsIgnoreCase("fib")){
			gameType=GameType.FIB;
		}
		else if(commandInfo[1].equalsIgnoreCase("inverse")){
			gameType=GameType.INV;
		}
	}

	@Override
	public boolean execute(Game game) {
		//Ejecuta el comando play pidiendo el tamaño, numero de celdas y la semilla y crea una nueva partida
		String read;
		String[] number;
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the size of the board: ");
		read= scan.nextLine();
		number= read.split(" ");
		
		while(number.length>1){
			System.out.println(" Please provide a single positive integer or press return.");
			System.out.print("Please enter the size of the board: ");
			read= scan.nextLine();
			number= read.split(" ");
		}
		
		while(!(number[0].equals(""))&&Integer.parseInt(number[0])<1){
			System.out.println(" The size of the board must be positive.");
			System.out.print("Please enter the size of the board: ");
			read= scan.nextLine();
			number= read.split(" ");
		}
		
		if(!(number[0].equals(""))){
			boardSize=Integer.parseInt(number[0]);
		}
		else{
			boardSize=4;
			System.out.println(" Using the default size of the board: "+boardSize);
		}
		
		System.out.print("Please enter the number of initial cells: ");
		read= scan.nextLine();
		number= read.split(" ");
		
		while(number.length>1){
			System.out.println(" Please provide a single positive integer or press return.");
			System.out.print("Please enter the number of initial cells: ");
			read= scan.nextLine();
			number= read.split(" ");
		}
		
		while(!(number[0].equals(""))&&Integer.parseInt(number[0])<1){
			System.out.println(" The size of the board must be positive.");
			System.out.print("Please enter the number of initial cells: ");
			read= scan.nextLine();
			number= read.split(" ");
		}
		
		if(!(number[0].equals(""))){
			initialCells=Integer.parseInt(number[0]);
		}
		else{
			initialCells=2;
			System.out.println(" Using the default number of initial cells: "+initialCells);
		}
		
		System.out.print("Please enter the seed for the pseudo-random number generator: ");
		read= scan.nextLine();
		number= read.split(" ");
		
		while(number.length>1){
			System.out.println(" Please provide a single positive integer or press return.");
			System.out.print("Please enter the seed for the pseudo-random number generator: ");
			read= scan.nextLine();
			number= read.split(" ");
		}
		
		while(!(number[0].equals(""))&&Integer.parseInt(number[0])<1){
			System.out.println(" The size of the board must be positive.");
			System.out.print("Please enter the seed for the pseudo-random number generator: ");
			read= scan.nextLine();
			number= read.split(" ");
		}
		
		if(!(number[0].equals(""))){
			randomSeed=Integer.parseInt(number[0]);
		}
		else{
			randomSeed=924;
			System.out.println(" Using the default seed for the pseudo-random number generator: "+randomSeed);
		}
		
		if(boardSize<initialCells){
			System.out.println("The number of initial cells must be less than the number of cells on the board.");
			/*controller.setPrintGameState(false);
			controller.run();*/
			return false;
		}
		else{
			game = new Game(gameType.createRules(), randomSeed, boardSize, initialCells);
			/*controller.setGame(game);
			controller.setPrintGameState(true);
			controller.run();*/
			return true;
		}
		
					/*Scanner scanner = null;
			try {
			    scanner = new Scanner(System.in);
			    //rest of the code
			}
			finally {
			    if(scanner!=null)
			        scanner.close();
			}*/
		
	}

	@Override
	public Command parse(String[] commandWords) {
		//Parsea el comando para saber si es suyo y devuelve uno con el tipo de juego
		Command command=null;
		if(commandWords[0].equalsIgnoreCase(this.commandName)){
			if(commandWords.length>1){
				if(commandWords[1].equalsIgnoreCase("original") || commandWords[1].equalsIgnoreCase("fib") || commandWords[1].equalsIgnoreCase("inverse")){
					command=new PlayCommand(commandWords);
				}
				else{
					System.out.println("Unknown game type for play command.");
				}
			}
			else{
				System.out.println("Play must be followed by a game type: original, fib, inverse.");
			}
		}
		return command;
	}
}
