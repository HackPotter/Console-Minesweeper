import java.util.*;
public class Game {
int r;
int c;
int b;
boolean notLost = true;

//first grid is used in calculations, second is returned to the user
int[][] grid;
Object[][] visible;

	public Game (int rows, int columns, int bombs)
	{
		r = rows;
		c = columns;
		b = bombs;
		grid = new int[r][c];
		visible = new Object[r][c];
	}
	
	//places the bombs in  the grid, represented as 9s
	public void placeBombs()
	{
		Random generator = new Random();
		int count = 0;
		while (count < b)
		{
			int rr = generator.nextInt(r);
			int cc = generator.nextInt(c);
			if (this.grid[rr][cc] != 9)
			{
				this.grid[rr][cc] = 9;
				count++;
			}
		}
		
	}
	//fills the rest of the spots in the grid with values equal to how many bombs are in the 8 adjacent areas
	public void fillGrid()
	{
		for (int countrow = 0; countrow < grid.length; countrow++)
		{
			for (int countcolumn = 0; countcolumn < grid[0].length; countcolumn++)
			{
				if (grid [countrow][countcolumn] != 9)
				{
					int bombcount = 0;
					//check spot above
					if (countcolumn > 0)
					{
						if (grid[countrow][countcolumn-1] == 9)
							bombcount++;
					}
					//check spot below
					if (countcolumn < grid[0].length - 1)
					{
						if (grid[countrow][countcolumn+1] == 9)
							bombcount++;
					}
					//check spot to the right
					if (countrow < grid.length - 1)
					{
						if (grid[countrow + 1][countcolumn] == 9)
							bombcount++;
					}
					//check spot to the left
					if (countrow > 0)
					{
						if (grid[countrow - 1][countcolumn] == 9)
							bombcount++;
					}
					//check spot upper left
					if (countcolumn > 0 && countrow > 0)
					{
						if (grid[countrow - 1][countcolumn-1] == 9)
							bombcount++;
					}
					//check spot upper right
					if (countcolumn > 0 && countrow < grid.length-1)
					{
						if (grid[countrow + 1][countcolumn-1] == 9)
							bombcount++;
					}
					//check spot bottom left
					if (countcolumn < grid[0].length - 1 && countrow > 0)
					{
						if (grid[countrow-1][countcolumn+1] == 9)
							bombcount++;
					}
					//check spot bottom right
					if (countcolumn < grid[0].length - 1 && countrow < grid.length - 1)
					{
						if (grid[countrow+1][countcolumn+1] == 9)
							bombcount++;
					}
					grid[countrow][countcolumn] = bombcount;
					
				}
			}
		}
		
		//Commented out, but can be used for debugging because it prints out the answer
		//for (int countrow = 0; countrow <grid.length; countrow++)
			//{
			//for (int countcolumn=0; countcolumn < grid[0].length; countcolumn++)
			//	System.out.print(grid[countrow][countcolumn] + " ");
			//System.out.println();
		//}
	}
	//fills a grid that is returned to the user, using - marks to show unexplored, dangerous areas
	public void fillVisible()
	{
		for (int countrow = 0; countrow <visible.length; countrow++)
		{
			for (int countcolumn=0; countcolumn < visible[0].length; countcolumn++)
				visible[countrow][countcolumn] = '-';
		}
	}
	//prints the grid so their adventure can begin
	public void printVisible()
	{
		for (int countrow = 0; countrow <visible.length; countrow++)
		{
			for (int countcolumn=0; countcolumn < visible[0].length; countcolumn++)
				System.out.print(visible[countrow][countcolumn] + " ");
			System.out.println();
		}
	}
	
	//user guesses where a bomb isn't. this game is more unforgiving so there can be a bomb in the first square
	public void makeGuess(int row, int col)
	{
		if (grid[row][col] == 9)
		{
			System.out.println("You hit a bomb, you lose");
			notLost = false;
			
		}else if (grid[row][col] ==0)
			this.revealZeroes(row, col);
		else	
			visible[row][col] = grid[row][col];
		
	}
	//a lot of code for this method is copy-pasted and modified from the fillGrid method. when a user guesses a spot with 0 adjacent bomb
	//s, the 8 adjacent spaces around it are also guess automatically. can be used recursively if necessary.
	public void revealZeroes(int row, int col)
	{
		visible[row][col] = " ";
		if (col > 0)
		{
			if ((grid[row][col-1] == 0 && (visible[row][col-1].equals('-')) || (grid[row][col-1] != 0)))
				this.makeGuess(row, col-1);
		}
		//check spot below
		if (col < grid[0].length - 1)
		{
			if ((grid[row][col+1] == 0 && (visible[row][col+1].equals('-')) || (grid[row][col+1] != 0)))
				this.makeGuess(row, col+1);
		}
		//check spot to the right
		if (row < grid.length - 1)
		{
			if ((grid[row+1][col] == 0 && (visible[row+1][col].equals('-')) || (grid[row+1][col] != 0)))
				this.makeGuess(row+1, col);
		}
		//check spot to the left
		if (row > 0)
		{
			if ((grid[row-1][col] == 0 && (visible[row-1][col].equals('-')) || (grid[row-1][col] != 0)))
				this.makeGuess(row-1, col);
		}
		//check spot upper left
		if (col > 0 && row > 0)
		{
			if ((grid[row-1][col-1] == 0 && (visible[row-1][col-1].equals('-')) || (grid[row-1][col-1] != 0)))
				this.makeGuess(row-1, col-1);
		}
		//check spot upper right
		if (col > 0 && row < grid.length-1)
		{
			if ((grid[row+1][col-1] == 0 && (visible[row+1][col-1].equals('-')) || (grid[row+1][col-1] != 0)))
				this.makeGuess(row+1, col-1);
		}
		//check spot bottom left
		if (col < grid[0].length - 1 && row > 0)
		{
			if ((grid[row-1][col+1] == 0 && (visible[row-1][col+1].equals('-')) || (grid[row-1][col+1] != 0)))
				this.makeGuess(row-1, col+1);
		}
		//check spot bottom right
		if (col < grid[0].length - 1 && row < grid.length - 1)
		{
			if ((grid[row+1][col+1] == 0 && (visible[row+1][col+1].equals('-')) || (grid[row+1][col+1] != 0)))
				this.makeGuess(row+1, col+1);
		}
	}
	//if the user wonnered, the game stops. checks for a wonner everytime the player makes a move
	public boolean wonGame()
	{
		for (int countrow = 0; countrow <visible.length; countrow++)
		{
			for (int countcolumn=0; countcolumn < visible[0].length; countcolumn++)
			{
				if(visible[countrow][countcolumn].equals('-') && grid[countrow][countcolumn] != 9)
					return false;
				
			}
		}
		return true;
	}
}
