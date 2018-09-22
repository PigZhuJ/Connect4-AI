
public class Initializer 
{
	
	public static void main(String args[]) 
	{
		Board board = new Board(3,3,3);
		board.DropChip(ESpaceState.X, 1);
		board.DropChip(ESpaceState.X, 1);
		board.DropChip(ESpaceState.X, 2);
		board.DropChip(ESpaceState.X, 0);
		GameIO.printBoard(board);
	}
	
}
