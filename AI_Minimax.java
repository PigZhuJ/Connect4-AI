import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class AI_Minimax implements IAI
{
	public int decideTurn(Board board)
	{
		return mm(board);
	}
	
	public static int mm(Board b)
	{
		Stack<Integer> pActions = b.possibleActions();
		int utility = -100;
		int move = 0;
		
		while(!pActions.isEmpty())
		{
			int i = pActions.pop();
			Board cb = b.copyBoard();
			cb = Board.DropChip(cb, i);
			int val = minV(cb);
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
		int minimum = Integer.MIN_VALUE;
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
		int maximum = Integer.MAX_VALUE;
		while(!pActions.isEmpty())
		{
			maximum = Math.max(maximum, minV(Board.DropChip(b, pActions.pop())));
		}
		return maximum;
	}
	
	private static int utility(Board b)
	{
		switch (b.CheckWin())
		{
		case O : return 100;
		case X : return -100;
		default : return 0;
		}
	}
}
