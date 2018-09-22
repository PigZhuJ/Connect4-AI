
public class GameIO 
{
	public static void printBoard(Board board)
	{
		ESpaceState[][] data = board.getData();
		
		System.out.println("\n");
		System.out.print(" ");
		//Print top row
		for(int w = 0; w < board.getWidth(); w++)
		{
			System.out.print(" "+ w);
		}
		
		System.out.println("");

		//For body, print left margin, values then right margin
		for(int h = 0; h < board.getHeight(); h++)
		{
			System.out.print(h);
			
			//Print values
			for(int w = 0; w < board.getWidth(); w++)
			{
				ESpaceState e = data[h][w];
				if(e != ESpaceState.Empty)
				{
					System.out.print(" " + e.toString());
				} else 
				{
					System.out.print("  ");
				}
			}
			
			System.out.print(" " + h + "\n");
		}
		
		//Print bottom row

		System.out.print(" ");
		for(int i = 0; i < board.getWidth(); i++)
		{
			System.out.print(" "+ i);
		}
	}
}
