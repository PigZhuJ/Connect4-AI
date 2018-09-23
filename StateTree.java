import java.util.LinkedList;

public class StateTree 
{
	private Board board;
	private StateTree parent;
	private LinkedList<StateTree> children;
	private boolean isTerminal;
	private int depth;
	private int utility;
	
	public StateTree(Board board, int depth)
	{
		this.board = board;
		this.depth = depth;
		this.children = new LinkedList<StateTree>();
	}
	
	
	public void addChild(StateTree toAdd)
	{
		toAdd.setParent(this);
		children.add(toAdd);
	}
	
	//Used upon tree creation and stores response in variable
	//for faster access during Minimax
	public boolean isTerminal()
	{
		//If the board is not already won
		if(board.CheckWin() != ESpaceState.Empty) 
		{
			//Check that the board isn't full - return nonterminal when we find an empty column
			for(int i = 0; i < board.getWidth()-1; i++)
			{
				if (board.getData()[0][i] == ESpaceState.Empty)
				{
					this.isTerminal = true;
					return true;
				}
			}
		}
		this.isTerminal = false;
		return false;
	}
	
	
	//Getters and Setters
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public StateTree getParent() {
		return parent;
	}

	public void setParent(StateTree parent) {
		this.parent = parent;
	}

	public LinkedList<StateTree> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<StateTree> children) {
		this.children = children;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getUtility() {
		return utility;
	}

	public void setUtility(int utility) {
		this.utility = utility;
	}


	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}
}
