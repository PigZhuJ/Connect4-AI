import java.util.PriorityQueue;
import java.util.Queue;

public class AI_Minimax implements IAI
{
	public int decideTurn(Board board)
	{
		generateTree(board);
		return 1;
	}
	
	
	private StateTree generateTree(Board board)
	{
		StateTree tree = new StateTree(board, 0);
		
		//First, generate the tree
		PriorityQueue<StateTree> sQueue = new PriorityQueue<StateTree>();
		sQueue.add(tree);
		
		int endings = 0;
		//Generate and explore all children
		//(This loop stops when all terminal)
		while(!sQueue.isEmpty())
		{
			StateTree current = sQueue.poll();
			
			//Check if state is terminal
			if (!current.isTerminal()) {
				
				//For each column I can drop a piece
				for (int i = 0; i < board.getWidth() - 1; i++) {
					
					//Check if that column is playable
					//If depth is even, it's our turn, if it's odd, it's the player's turn
					if (current.getBoard().canPlayColumn(i)) {
						endings++;
						StateTree newChild = new StateTree(current.getBoard(), current.getDepth() + 1);

						//Our turn
						if (current.getDepth() % 2 == 0) {
							//Since it's our turn we would drop an X
							newChild.getBoard().DropChip(ESpaceState.X, i);

							//Player Turn
						} else {
							//Since it's our their we would drop an 0
							newChild.getBoard().DropChip(ESpaceState.O, i);

						}
						newChild.setParent(current);
						current.addChild(newChild);
						sQueue.add(newChild);
					}
				} 
			}
		}
		System.out.println("Possible endings: " + endings);
		return tree;
	}
}
