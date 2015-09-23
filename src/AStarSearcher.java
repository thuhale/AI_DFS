import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public double hValue(State s){
		Square goalSq = maze.getGoalSquare();
		return Math.abs(s.getX()-goalSq.X)+ Math.abs(s.getY()-goalSq.Y);
	}
	public boolean search() {

		// FILL THIS METHOD

		// CLOSED list is a Boolean array that indicates if a state associated with a given position in the maze has already been expanded. 
		boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...

		// OPEN list (aka Frontier list)
		PriorityQueue<StateFValuePair> open = new PriorityQueue<StateFValuePair>();
		

		// TODO initialize the root state and add
		// to OPEN list
		State initialState = new State(maze.getPlayerSquare(), null, 0,0);
		StateFValuePair initialF = new StateFValuePair(initialState, this.hValue(initialState));
		open.add(initialF);
		this.noOfNodesExpanded = 1;
		this.cost = 1;

		while (!open.isEmpty()) {
			// TODO return true if a solution has been found
			// TODO maintain the cost, noOfNodesExpanded,
			// TODO update the maze if a solution found

			// use open.poll() to extract the minimum stateFValuePair.
			// use open.add(...) to add stateFValue pairs
			StateFValuePair current = open.poll();
			State currentState = current.getState();
			closed[currentState.getX()][currentState.getY()]=true;
			this.noOfNodesExpanded = this.noOfNodesExpanded+1;
			
			if(currentState.isGoal(maze)){
				State tp = currentState.getParent();
				while(tp.getParent()!=null){
					maze.setOneSquare(tp.getSquare(), '.');
					tp = tp.getParent();
					this.cost=this.cost+1;
				}
				return true;
			}
			
			else{
				ArrayList<State> successor = current.getState().getSuccessors(closed, maze);
				for (int i = 0; i < successor.size(); i++){
					StateFValuePair tempF = new StateFValuePair(successor.get(i), successor.get(i).getGValue()+this.hValue(successor.get(i)));
					//iterate open to see if there already exists that node
					boolean tie = false;
					Iterator<StateFValuePair> itt = open.iterator();
					
					while(itt.hasNext()){
						StateFValuePair pp = itt.next();
						if(pp.getState().getX() == tempF.getState().getX() && pp.getState().getY() == tempF.getState().getY()){
							tie = true;
						}
					}
					
					if (tie == false){
						open.add(tempF);
					}
				}
			}
		}
		return false;
	}

}
