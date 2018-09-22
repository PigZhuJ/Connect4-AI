public class Board 
{
	//Class variables
	private int nToWin;
	private int height;
	private int width;
	private ESpaceState[][] data;
	
	
	//Constructor
	public Board(int nToWin, int height, int width)
	{
		this.nToWin = nToWin;
		this.height = height;
		this.width = width;
		this.data = new ESpaceState[height][width];
		InitBoard();
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
	public boolean DropChip(ESpaceState ChipType, int column)
	{
		/*
		 * Scenarios that fail to place the chip
		 * a.) Program tries to drop a chip of enumerable type "Empty"
		 * b.) The column is full
		 */
		if(ChipType == ESpaceState.Empty || data[0][column] != ESpaceState.Empty) return false;
		
		//Iterate down the column to find first empty space
		for(int i = 0; i < height; i++)
		{
			//First, handle bottom spot scenario
			if(i == (height-1))
			{
				data[i][column] = ChipType;
				return true;
			//Next, check if the next spot down is filled	
			} else if (data[i+1][column] != ESpaceState.Empty)
			{
				data[i][column] = ChipType;
				return true;
			}
			//If neither conditions are satisfied, the loop searches one spot deeper
		}
		//If this code is reached, something logically has gone wrong.
		return false;
	}
	
	//Checks for an 'nToWin' row of chips
	//TODO doesn't work in high numbers
	public ESpaceState CheckWin()
	{
	    int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
	    for (int[] d : directions) {
	        int dx = d[0];
	        int dy = d[1];
	        for (int x = 0; x < width-1; x++) {
	            for (int y = 0; y < height-1; y++) {
	                int lastx = x + (nToWin-1)*dx;
	                int lasty = y + (nToWin-1)*dy;
	                if (0 <= lastx && lastx < width-1 && 0 <= lasty && lasty < height-1) {
	                    ESpaceState e = data[x][y];
	                    if (e != ESpaceState.Empty && e == data[x+dx][y+dy] 
	                                 && e == data[x+2*dx][y+2*dy] 
	                                 && e == data[lastx][lasty]) {
	                        return e;
	                    }
	                }
	            }
	        }
	    }
	    return ESpaceState.Empty;
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

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
