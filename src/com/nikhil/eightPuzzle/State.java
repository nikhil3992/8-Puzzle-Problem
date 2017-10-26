package com.nikhil.eightPuzzle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* State class stores the data of a particular state. 
 * An object of this class has f-cost,g-cost,h-cost,tile positions and previous state. 
 * This class is mainly used to generate successor states from a given state. 
 * The square blocks of a state are represented in the form of a two dimensional array.
 * The blank square is represented as having value 0.
 * 
 * */

public class State {

	int[][] tiles;			//Square blocks labeled 1-8 and blank square block labeled 0 is represented as 2-D array.
	int h_value;			//Heuristic cost of a state.
	int g_value;			//path cost of a state
	int f_value;			//actual cost of a state
	State previousState;    //Contains previous state

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(tiles);
		return result;
	}

	/*Overriding equals() method which is called by the system when two States need to be compared*/
	@Override		
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		State other = (State) obj;
		if (!Arrays.deepEquals(tiles, other.tiles)) {
			return false;
		}
		return true;
	}  

	/* Constructor of State class */
	State(int[][] tiles) {
		this.tiles = tiles;
		this.h_value = 0;
		this.g_value = 0;
		this.f_value = 0;
		this.previousState = null; 
	}
	
	/* Method: 		moveNorth(int,int)
	 * Arguments:   Two integers which are row and column numbers of blank square
	 * Returns:		-
	 * */
	public void moveNorth(int row,int column) {
		
		int temp = tiles[row][column];
		tiles[row][column] = tiles[row+1][column];
		tiles[row+1][column] = temp;
	}
	
	/* Method: 		moveSouth(int,int)
	 * Arguments:   Two integers which are row and column numbers of blank square
	 * Returns:		-
	 * */
	public void moveSouth(int row,int column) {
		
		int temp = tiles[row][column];
		tiles[row][column] = tiles[row-1][column];
		tiles[row-1][column] = temp;
	}
	
	/* Method: 		moveEast(int,int)
	 * Arguments:   Two integers which are row and column numbers of blank square
	 * Returns:		-
	 * */
	public void moveEast(int row,int column) {
	
		int temp = tiles[row][column];
		tiles[row][column] = tiles[row][column-1];
		tiles[row][column-1] = temp;
	}
	
	/* Method: 		moveWest(int,int)
	 * Arguments:   Two integers which are row and column numbers of blank square
	 * Returns:		-
	 * */
	public void moveWest(int row,int column) {
		
		int temp = tiles[row][column];
		tiles[row][column] = tiles[row][column+1];
		tiles[row][column+1] = temp;		
	}
		
	/* Method: 		generateSuccessorStates()
	 * Arguments:   none
	 * Returns:		A List of state objects
	 * */
	public List<State> generateSuccessorStates() {
		
		PositionOfZero mZeroPosition = new PositionOfZero();
		mZeroPosition.ZeroPosition();
		int xPosition = mZeroPosition.getXPosition();
		int yPosition = mZeroPosition.getYPosition();
		List<State> successorStates = new ArrayList<State>();
		int[][] copyOftiles = new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				copyOftiles[i][j] = tiles[i][j];
			}
		}
		
		if(xPosition == 0 && yPosition == 0) {
	
			int temp = tiles[0][0];
			tiles[0][0] = tiles[0][1];
			tiles[0][1] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[0][0];
			tiles2[0][0] = tiles2[1][0];
			tiles2[1][0] = temp;
			successorStates.add(new State(tiles2));
			return successorStates;
		}
		if(xPosition == 0 && yPosition == 2) {
			
			int temp = tiles[0][2];
			tiles[0][2] = tiles[0][1];
			tiles[0][1] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[0][2];
			tiles2[0][2] = tiles2[1][2];
			tiles2[1][2] = temp;
			successorStates.add(new State(tiles2));
			return successorStates;
		}
		if(xPosition == 2 && yPosition == 0) {
			
			int temp = tiles[2][0];
			tiles[2][0] = tiles[1][0];
			tiles[1][0] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[2][0];
			tiles2[0][2] = tiles2[2][1];
			tiles2[2][1] = temp;
			successorStates.add(new State(tiles2));
			return successorStates;
		}
		if(xPosition == 2 && yPosition == 2) {
			
			int temp = tiles[2][2];
			tiles[2][2] = tiles[1][2];
			tiles[1][2] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[2][2];
			tiles2[2][2] = tiles2[2][1];
			tiles2[2][1] = temp;
			successorStates.add(new State(tiles2));
			return successorStates;
		}
		if(xPosition == 1 && yPosition == 0) {
			
			int temp = tiles[1][0];
			tiles[1][0] = tiles[2][0];
			tiles[2][0] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[1][0];
			tiles2[1][0] = tiles2[1][1];
			tiles2[1][1] = temp;
			successorStates.add(new State(tiles2));
			int tiles3[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles3[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles3[1][0];
			tiles3[1][0] = tiles3[0][0];
			tiles3[0][0] = temp;
			successorStates.add(new State(tiles3));
			return successorStates;
		}
		if(xPosition == 0 && yPosition == 1) {
			
			int temp = tiles[0][1];
			tiles[0][1] = tiles[0][0];
			tiles[0][0] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[0][1];
			tiles2[0][1] = tiles2[0][2];
			tiles2[0][2] = temp;
			successorStates.add(new State(tiles2));
			int tiles3[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles3[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles3[0][1];
			tiles3[0][1] = tiles3[1][1];
			tiles3[1][1] = temp;
			successorStates.add(new State(tiles3));
			return successorStates;
		}
		if(xPosition == 2 && yPosition == 1) {
					
			int temp = tiles[2][1];
			tiles[2][1] = tiles[2][0];
			tiles[2][0] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[2][1];
			tiles2[2][1] = tiles2[2][2];
			tiles2[2][2] = temp;
			successorStates.add(new State(tiles2));
			int tiles3[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles3[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles3[2][1];
			tiles3[2][1] = tiles3[1][1];
			tiles3[1][1] = temp;
			successorStates.add(new State(tiles3));
			return successorStates;
		}
		if(xPosition == 1 && yPosition == 2) {
		
			int temp = tiles[1][2];
			tiles[1][2] = tiles[2][2];
			tiles[2][2] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}		
			temp = tiles2[1][2];
			tiles2[1][2] = tiles2[0][2];
			tiles2[0][2] = temp;
			successorStates.add(new State(tiles2));
			int tiles3[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles3[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles3[1][2];
			tiles3[1][2] = tiles3[1][1];
			tiles3[1][1] = temp;
			successorStates.add(new State(tiles3));
			return successorStates;
		}
		if(xPosition == 1 && yPosition == 1) {
		
			int temp = tiles[1][1];
			tiles[1][1] = tiles[1][0];
			tiles[1][0] = temp;
			successorStates.add(new State(tiles));
			int tiles2[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles2[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles2[1][1];
			tiles2[1][1] = tiles2[0][1];
			tiles2[0][1] = temp;
			successorStates.add(new State(tiles2));
			int tiles3[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles3[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles3[1][1];
			tiles3[1][1] = tiles3[1][2];
			tiles3[1][2] = temp;
			successorStates.add(new State(tiles3));
			int tiles4[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					tiles4[i][j] = copyOftiles[i][j];
				}
			}
			temp = tiles4[1][1];
			tiles4[1][1] = tiles4[2][1];
			tiles4[2][1] = temp;
			successorStates.add(new State(tiles4));
			return successorStates;
		}
		return successorStates;	
	}
	
	/* A helper class which calculates and returns the position of the blank square*/
		
	class PositionOfZero {
		
		int xPosition;		//row position of zero(blank square)
		int yPosition;		//column position of zero(blank square)
		
		/* This method calculates the position of zero(blank square) in the state.
		 * Method:		ZeroPosition()
		 * Arguments:	none
		 * Returns:		-
		 * */
		private void ZeroPosition() {
			boolean positionfound = false;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(tiles[i][j] == 0) {
						positionfound = true;
						xPosition = i;
						yPosition = j;
						break;
					}					
				}
				if(positionfound) {
					break;
				}
			}
		}		
		private int getXPosition() { return xPosition; }	//returns row number of blank square	
		private int getYPosition() { return yPosition; }	//returns column number of blank square			
	}
}
