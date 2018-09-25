import java.util.Stack;

public class AI_Minimax implements IAI
{
	public int decideTurn(Board board)
	{
		int ret = mm(board);
		return ret;
	}
		
	public static int mm(Board b)
	{
		Stack<Integer> pActions = b.possibleActions();
		int utility = -100;
		int move = 0;
		
		while(!pActions.isEmpty())
		{
			int i = pActions.pop();
			Board cb = Board.copyBoard(b);
			cb = Board.DropChip(cb, i);
			//Start with minV because we aren't evaluating current state, but it's children
			int val = minV(cb);
			//Here we max what we get with the move variable
			if(val >= utility)
			{
				utility = val;
				move = i;
			}
		}
		return move;
	}
	
	public static int minV(Board b)
	{
		if(b.isTerminal())
		{
			return utility(b);
		}
		
		Stack<Integer> pActions = b.possibleActions();
		int minimum = Integer.MAX_VALUE;
		while(!pActions.isEmpty())
		{
			minimum = Math.min(minimum, maxV(Board.DropChip(b, pActions.pop())));
		}
		return minimum;
	}
	
	public static int maxV(Board b)
	{
		if(b.isTerminal())
		{
			return utility(b);
		}
		
		Stack<Integer> pActions = b.possibleActions();
		int maximum = Integer.MIN_VALUE;
		while(!pActions.isEmpty())
		{
			maximum = Math.max(maximum, minV(Board.DropChip(b, pActions.pop())));
		}
		return maximum;
	}
	
	private static int utility(Board b)
	{
		ESpaceState e = b.CheckWin();
		if(e == ESpaceState.X)
		{
			return 1;
		} else if(e == ESpaceState.O)
		{
			return -1;
		} else 
		{
			return 0;
		}
	}
}
