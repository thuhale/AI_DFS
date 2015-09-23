import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Depth-First Search (DFS)
 * 
 * You should fill the search() method of this class.
 */
public class DepthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public DepthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main depth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	// method traces back to parents of the node and turns true if one of the ancestors repeats
	public boolean cycle(State st){
		State temp = st;
		while(temp.getParent()!=null){
			State parent = temp.getParent();
			if(parent.getX()==st.getX() && parent.getY()==st.getY()){
				return true;
			}
			else{
				temp = temp.getParent();
			}
		}
		return false;
		
	}
	public boolean search() {
		// FILL THIS METHOD

		// CLOSED list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been expanded.
		boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// ...

		// Stack implementing the Frontier list
	
		LinkedList<State> stack = new LinkedList<State>();
		State initialState = new State(maze.getPlayerSquare(), null, 0,0);
		stack.push(initialState);
		this.cost=1;
		boolean findResult = false;

		while (!stack.isEmpty()) {
			// TODO return true if find a solution
			// TODO maintain the cost, noOfNodesExpanded
			// TODO update the maze if a solution found

			// use stack.pop() to pop the stack.
			// use stack.push(...) to elements to stack
			
			State current = stack.pop();
			closed[current.getX()][current.getY()]=true;
			this.noOfNodesExpanded = this.noOfNodesExpanded+1;

			if (current.isGoal(maze)){
				State tp = current.getParent();
				while(tp.getParent()!=null){
					maze.setOneSquare(tp.getSquare(), '.');
					tp = tp.getParent();
					this.cost=this.cost+1;
				}
				findResult = true;
			}
			else{
				ArrayList<State> successor = current.getSuccessors(closed, this.maze);
				for (int i = successor.size()-1; i>=0; i--){
					if(!this.cycle(successor.get(i))){
						stack.push(successor.get(i));
					}
				}
			}
		}
		//number of nodes expanded not counting the goal and starting point
		this.noOfNodesExpanded = this.noOfNodesExpanded-2;
		return findResult;
	}
}
