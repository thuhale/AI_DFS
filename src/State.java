import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State {

	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the DFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth) {
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            closed[i][j] is true if (i,j) is already expanded
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] closed, Maze maze) {
		// FILL THIS METHOD

		// TODO check all four neighbors (up, right, down, left)
		// TODO return all unvisited neighbors
		// TODO remember that each successor's depth and gValue are
		// +1 of this object.
		ArrayList<State> successor = new ArrayList<State>();
		//up square:
		if (!closed[square.X+1][square.Y]){
			if (maze.getSquareValue(square.X+1, square.Y) != '%'){
				Square upSquare = new Square(square.X+1, square.Y);
				State upState = new State(upSquare, this, this.gValue+1, this.depth+1);
				successor.add(upState);	
			}
		}
		//right
		if (!closed[square.X][square.Y+1]){
			if (maze.getSquareValue(square.X, square.Y+1) != '%'){
				Square rightSquare = new Square(square.X, square.Y+1);
				State rightState = new State(rightSquare, this, this.gValue+1, this.depth+1);
				successor.add(rightState);	
			}
		}
		//down
		if (!closed[square.X-1][square.Y]){
			if (maze.getSquareValue(square.X-1, square.Y) != '%'){
				Square downSquare = new Square(square.X-1, square.Y);
				State downState = new State(downSquare, this, this.gValue+1, this.depth+1);
				successor.add(downState);	
			}
		}
		//left
		if (!closed[square.X][square.Y-1]){
			if (maze.getSquareValue(square.X, square.Y-1) != '%'){
				Square leftSquare = new Square(square.X, square.Y-1);
				State leftState = new State(leftSquare, this, this.gValue+1, this.depth+1);
				successor.add(leftState);	
			}
		}
		return successor;
	}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}
	
	

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the DFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() {
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}
}
