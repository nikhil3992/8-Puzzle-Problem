package com.nikhil.eightPuzzle;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/* The Astar class implements the A* search algorithm.
 * It contains the Start method which takes the initial State as argument and
 * implements the A* algorithm. If the goal state is found it returns the goal state.
 * Else, it returns null.
 * */

public class Astar {
	
	HashSet<State> open = new HashSet<State>();		 	// A HashSet to contain all unique and unexplored nodes
	HashMap<Integer,Integer> xHashMap,yHashMap;		 	// Stores the position of each block in goal state
	static int nodesGenerated = 0,nodesExpanded = 0; 	// Keeps a count of nodes generated and expanded
	PriorityQueue<State> queue = new PriorityQueue<State>(1000,new Comparator<State>() {
		
		@Override
		public int compare(State a,State b) {
			return (a.f_value != b.f_value) ? (a.f_value - b.f_value) : (a.h_value - b.h_value);
		}		
	});
		
	/*Start method which implements A* search Algorithm*/
	    
	public State Start(State currentState) {
		
		List<State> successorStatesList = new ArrayList<State>();		//Initializing a list which stores successor states
		xHashMap = new HashMap<Integer,Integer>();						//Stores row positions of blocks 0-8 in goal state 
		yHashMap = new HashMap<Integer,Integer>();						//Stores column positions of blocks 0-8 in goal state 
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				xHashMap.put(Main.goalState[i][j], i);
				yHashMap.put(Main.goalState[i][j], j);
			}
		}
		currentState.g_value = 0;												//g-cost of initial state is zero.
		currentState.h_value = getHeuristicValue(currentState.tiles);			//calculates h-cost
		currentState.f_value = currentState.g_value + currentState.h_value;		//f-cost = g-cost + h-cost
		currentState.previousState = null;										//initial state does not have a previous state
		if(!open.contains(currentState)) {
			open.add(currentState);
			queue.add(currentState);
		}
		
		while(true) {		//loop starts
			/* No node is added to queue. Reports Failure and returns null */
			if(queue.isEmpty()) {
				System.out.println("Failure");
				return null;
			}
			/*Initial state is itself the goal state. Returns the initial state.*/
			if(Arrays.deepEquals(currentState.tiles, Main.goalState)) {
				System.out.println("Goal has been reached");
				return currentState;
			}
			/*Initial state is not the goal state.Expands nodes*/
			else {				
				successorStatesList = currentState.generateSuccessorStates();		//Successor states have been generated
				nodesExpanded++;
				if(nodesExpanded % 10000 == 0) {
					System.out.println("nodes expanded is "+nodesExpanded);
				}
				for(State successor: successorStatesList) {
					/*Calculates g-cost, heuristic cost and f-cost for each node and 
					 * inserts into queue based on f-cost*/
					if(!open.contains(successor)) {
						successor.h_value = getHeuristicValue(successor.tiles);
						successor.g_value = currentState.g_value + 1;
						successor.f_value = successor.g_value + successor.h_value;
						successor.previousState = currentState;
						queue.add(successor);
						open.add(successor);
						nodesGenerated++;	//Increments nodes generated
					}
				}
				/*Prints nodesGenerated for every 20000 nodes */ 
				if(nodesGenerated % 20000 == 0) {
					System.out.println("nodes generated is "+nodesGenerated+" ...");
				}
				//Polls the node at the top of the queue.
				currentState = queue.poll();
				//Checks if it is the goal node
				if(Arrays.deepEquals(currentState.tiles,Main.goalState)) {
					System.out.println("Goal has been found");
					State temporarySolution = currentState;
					//Explores other nodes in queue to find a better solution
					while(!queue.isEmpty()) {
						State tempState = queue.poll();
						if(Arrays.deepEquals(tempState.tiles,Main.goalState)) {
							if(tempState.f_value < temporarySolution.f_value) {
								currentState = tempState;	//better solution has been found
							}
						}
					} 
					return currentState;		//optimal goal state has been found.
				}
				else {
					//Goal State has not been found.Repeat the loop.
					successorStatesList.clear();
					continue;
				}
			}
		}		//loop ends
	}
	
	/* Method: 	    getHeuristicValue(int[][])
	 * Arguments: 	2-D integer array
	 * Returns: 	An integer which is heuristic value of the state
	 * */
	
	public int getHeuristicValue(int[][] tiles) {
		
		int heuristicValue = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
			heuristicValue = heuristicValue + calculateManhattanDistance(tiles[i][j],i,j);		
			}
		}
		return heuristicValue;
	}
	
	/* Method:		calculateManhattanDistance(int,int,int)
	 * Arguments:	block value,row position,column position
	 * Returns:     An integer which is the Manhattan Distance of a particular block
	 * */
	
	public int calculateManhattanDistance(int value,int xCoordinate,int yCoordinate) {
		
		int x = (int) Math.abs(xHashMap.get(value) - xCoordinate);
		int y = (int) Math.abs(yHashMap.get(value) - yCoordinate);
		return x + y;
	}
	
}
