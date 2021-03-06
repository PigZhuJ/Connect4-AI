import java.util.Scanner;

public class GameMaster 
{
	
	public static void main(String args[]) 
	{
		System.out.println("CONNECT-N: A game by Clay Emmel");
		System.out.println("-------------------------------");
		System.out.println("A: Standard 6x7 Connect-4");
		System.out.println("B: Impossible 3x3 Connect-3");
		System.out.println("-------------------------------");
		System.out.print("Choose your mode: ");
		
		Scanner s = new Scanner(System.in);
		Board board = new Board(3,3,3);
		//Read response
		boolean selectedMode = false;
		while(!selectedMode)
		{
			String input = s.next();
			if(input.equals("A") || input.equals("a"))
			{
				board = new Board(4,6,7);
				selectedMode = true;
				System.out.println("Welcome to Connect-4, enter the column you'd like to drop your chip in.");
			} else if(input.equals("B") || input.equals("b"))
			{
				board = new Board(3,3,3);
				selectedMode = true;
				System.out.println("Welcome to Connect-3, enter the column you'd like to drop your chip in.");
			} else
			{
				System.out.println("Please Enter 'A' or 'B' to select a mode.");
			}
		}	
		
		IAI ai = new AI_Minimax();
				
		while(!board.isTerminal())
		{
			GameIO.printBoard(board);
			if (board.isPlayerTurn())
			{
				//Ask user for their play
				System.out.print("Your Move: ");
				String input = s.next();
				
				try 
				{
					int columnChoice = Integer.parseInt(input);
					
					//Column number is within 0->width
					if (columnChoice < 0 || columnChoice >= board.getWidth())
					{
						System.out.println("Please enter a valid column number.");
					//If board doesn't complain that column is full
					} else if (!board.canPlayColumn(columnChoice))
					{
						System.out.println("That column is full silly!");
					//Otherwise continue
					} else
					{
						board = Board.DropChip(board, columnChoice);
						board.setPlayerTurn(false);
					}
				} catch (NumberFormatException e)
				{
					System.out.println("Please enter a valid column number.");
				}
			} else
			//AI Turn
			{
				Board.DropChip(board, ai.decideTurn(board));
				board.setPlayerTurn(true);
			}
			
			System.out.println("\n------------------");
		}
		
		s.close();
		
		GameIO.printBoard(board);
		if(board.CheckWin() == ESpaceState.O)
		{
			System.out.println("\nYou've won! You're smarter than your CPU!");
		} else if (board.CheckWin() == ESpaceState.X)
		{
			System.out.println("\nThe AI has won! Better luck next time.");
		} else 
		{
			System.out.println("It's a tie!");
		}
	}
	
}
