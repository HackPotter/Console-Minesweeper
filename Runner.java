import java.util.*;
public class Runner {
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		int rows = 0;
		int bombs = 0;
		int columns = 0;
		int difficulty = 0;
		int guessrow;
		int guesscolumn;
		int turns = 0;
		int again = 1;
		while (again == 1)
		{
		System.out.println("Welcome to MineSweeper!");
		System.out.println("Enter 1 for easy, 2 for medium, 3 for hard, or 4 for custom");
		difficulty = reader.nextInt();
			//difficulties are based on the actual modes in real minesweeper
			if (difficulty == 1)
			{
				rows = 8;
				columns = 8;
				bombs = 10;
			}
			if (difficulty == 2)
			{
				rows = 16;
				columns = 16;
				bombs = 40;
			}
			if (difficulty == 3)
			{
				rows = 30;
				columns = 16;
				bombs = 99;
			}
			if (difficulty == 4)
			{
				
				System.out.println("Enter number of rows:");
				rows = reader.nextInt();
				System.out.println("Enter number of columns");
				columns = reader.nextInt();
				System.out.println("Enter number of bombs:");
				bombs =  reader.nextInt();
			
					if (bombs > rows * columns)
					{
						System.out.println("Error: too many bombs");
						System.exit(0);
					}
			}
			
		Game g = new Game(rows, columns, bombs);
		g.placeBombs();
		g.fillGrid();
		g.fillVisible();
		g.printVisible();
		
		while( g.notLost == true)
		{
			System.out.println("Enter the row, then column of the spot you wish to check (0 being the first row and column)");
			guessrow = reader.nextInt();
			guesscolumn = reader.nextInt();
			g.makeGuess(guessrow, guesscolumn);
			g.printVisible();
			turns++;
			if (g.wonGame() == true)
			{
				System.out.println("You Won in " + turns +  " turns!");
				g.notLost = false;
			}			
		}
		System.out.println("Press 1 to play again or press 0 to stop ");
		again = reader.nextInt();
	}
	}

}

//xkcd 838, 149




// goku > superman


//streaming minesweeper on twitch is an untapped market


//.1 + .2 = .30000000000001
//on the internet, nobody knows you are a dog

//there are only two people on the internet, you and the other guy
//<--if you're having runtime errors I feel bad for you son, I've got 99 problems but a glitch ain't one