import java.util.Stack;

public class Board 
{
	//Class variables
	private int nToWin;
	private int height;
	private int width;
	private ESpaceState[][] data;
	private boolean playerTurn;
	
	
	//Constructor
	public Board(int nToWin, int height, int width)
	{
		this.playerTurn = false;
		this.nToWin = nToWin;
		this.height = height;
		this.width = width;
		this.data = new ESpaceState[height][width];
		InitBoard();
	}
	
	public Board(int nToWin, int height, int width, ESpaceState[][] data)
	{
		this.playerTurn = true;
		this.nToWin = nToWin;
		this.height = height;
		this.width = width;
		this.data = data;
	}
	
	//Initializes board to all empty states
	private void InitBoard()
	{
		for(int h = 0; h < height; h++)
		{
			for(int w = 0; w < width; w++)
			{
				data[h][w] = ESpaceState.Empty;
			}
		}
	}
	
	//Attempts to drop a chip into a given column - returns whether or not it succeeded
	public static Board DropChip(Board b, int column)
	{
		ESpaceState ChipType = b.isPlayerTurn() ? ESpaceState.O : ESpaceState.X;
		/*
		 * Scenarios that fail to place the chip
		 * a.) Program tries to drop a chip of enumerable type "Empty"
		 * b.) The column is full
		 */
		
		//Iterate down the column to find first empty space
		for(int i = 0; i < b.getHeight(); i++)
		{
			//Handle bottom spot scenario
			if(i == (b.getHeight()-1))
			{
				b.data[i][column] = ChipType;
				b.setPlayerTurn(!b.playerTurn);
				return b;
			//Next, check if the next spot down is filled	
			} else if (b.data[i+1][column] != ESpaceState.Empty)
			{
				b.data[i][column] = ChipType;
				b.setPlayerTurn(!b.playerTurn);
				return b;
			}
			//If neither conditions are satisfied, the loop searches one spot deeper
		}
		b.setPlayerTurn(!b.playerTurn);
		return b; //<-- Shouldn't be reached
	}
	
	public boolean isTerminal()
	{
		//If the board is not already won
		if(CheckWin() == ESpaceState.Empty) 
		{
			//Check that the board isn't full - return nonterminal when we find an empty column
			for(int i = 0; i < width; i++)
			{
				if (data[0][i] == ESpaceState.Empty)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public Stack<Integer> possibleActions()
	{
		Stack<Integer> actions = new Stack<Integer>();
		for(int c = 0; c < width; c++)
		{
			if(data[0][c] == ESpaceState.Empty)
			{
				actions.add(c);
			}
		}
		return actions;
	}
	
	public static Board copyBoard(Board b)
	{
		//Loop thru array and copy ints
		Board newB = new Board(b.getnToWin(),b.getHeight(),b.getWidth());
		newB.setPlayerTurn(b.getPlayerTurn());
		for(int h = 0; h < b.getHeight(); h++)
		{
			for(int w = 0; w < b.getWidth(); w++)
			{
				newB.setDataVal(h, w, b.getData()[h][w]);
			}
		}
		return newB;
	}
	
	//Checks for an 'nToWin' row of chips
	public ESpaceState CheckWin()
	{
	    int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
	    for (int[] d : directions) 
	    {
	        int dx = d[1];
	        int dy = d[0];
	        for (int x = 0; x < width; x++) 
	        {
	            for (int y = 0; y < height; y++) 
	            {
	                //Current 'color' being checked
	                ESpaceState e = data[y][x];
	                
	                if (e != ESpaceState.Empty) {
						//Loop through derivatives
						int count = 0;
						for (int i = 0; i < nToWin; i++) {
							int xCheck = (i * dx) + x;
							int yCheck = (i * dy) + y;

							//Not out of bounds
							if (0 <= xCheck && xCheck < width && 0 <= yCheck && yCheck < height) {
								//If we find another same piece
								if (e == data[yCheck][xCheck]) {
									count++;
									if (count == nToWin) {
										return e;
									}
								} else {
									//If it's a different color, effectively end the loop
									i = nToWin;
								}
							} else {
								//If we've gone out of bounds, end the loop
								i = nToWin;
							}
						} 
					}
	            }
	        }
	    }
	    return ESpaceState.Empty;
	}
	
	public boolean canPlayColumn(int n)
	{
		if(n >= 0 && n < width && data[0][n] == ESpaceState.Empty)
		{
			return true;
		}
		return false;
	}
	
	//Returns data of board
	public ESpaceState[][] getData()
	{
		return data;
	}
	
	//Getters
	public int getnToWin() {
		return nToWin;
	}
	
	public void setnToWin(int n)
	{
		this.nToWin = n;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public boolean getPlayerTurn()
	{
		return playerTurn;
	}
	
	public void setDataVal(int h, int w, ESpaceState e)
	{
		data[h][w] = e;
	}

}
