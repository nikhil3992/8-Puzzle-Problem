package com.nikhil.eightPuzzle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * The Main class of the program where the execution starts.
 * It reads the initial and goal states from the user and validates them.
 * The number of nodes expanded,generated,number of moves to solve the puzzle
 * and time taken(in milli seconds) to solve the puzzle is printed as output.
 * */


public class Main {

	public static int[][] initialState;	//Stores initial State of the puzzle
	public static int[][] goalState; 	//Stores goal State of the puzzle
	
	public static void main(String[] args) {
		
		System.out.println("Enter the start state of the puzzle");
		Scanner input = new Scanner(System.in);	//Scanner object to read input from user 
		initialState = new int[3][3]; 			//initializing initial state
		goalState = new int[3][3];  			// initializing goal state
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				try {
					System.out.print("Enter element at row "+i+" column "+j+" ");
					initialState[i][j] = input.nextInt();  // read input from user
				} catch(Exception e) {
					System.out.print("This is not an integer\n"); 	//catch exception in input					
				}	
			}
		}
		System.out.println("Enter the goal state");
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				try {
					System.out.print("Enter element at row "+i+" column "+j+" ");
					goalState[i][j] = input.nextInt();	//read goal state from user
				} catch(Exception e) {
					System.out.print("This is not an integer\n");	//catch exception in input 
					}	
			}
		}
		/* Print the initial state */
		System.out.println("The initial state is "); 
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(initialState[i][j]+"\t");
			}
			System.out.println();
		}
		/* Print the goal state */
		System.out.println("\nThe goal state is ");
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(goalState[i][j]+"\t");
			}
			System.out.println();
		}
		
		State mState = new State(initialState);		//Create an instance of State class
		Astar mAstar = new Astar();					//Create an instance of Astar class
		
		/* Starts Astar algorithm and returns goal state if it is found else returns null*/
		long timeElapsed = System.currentTimeMillis();
		State returnedState = mAstar.Start(mState);
		if(returnedState == null) {
			System.out.print("Failed to find goal node");
		}
		else {		//Prints the states from goal state to initial state
				System.out.println("The solution path is ");
				int numberOfMoves = 0;
				List<State> path = new ArrayList<State>(); 
				while(returnedState!=null) {
					path.add(returnedState);
					returnedState = returnedState.previousState;
				}
				Collections.reverse(path);
				for(int i=0;i<path.size();i++) {
					State output = path.get(i);
					numberOfMoves++;
					for(int j=0;j<3;j++) {
						for(int k=0;k<3;k++) {
							System.out.print(output.tiles[j][k]+"\t");
						}
						System.out.println();
					}
					System.out.println();
				}
				/* Prints number of nodes generated, expanded,number of moves 
				 * and time taken to solve the problem */
				System.out.println("Nodes generated is "+Astar.nodesGenerated);
				System.out.println("Nodes expanded is "+Astar.nodesExpanded);
				System.out.println("Puzzle solved in "+(numberOfMoves-1)+" moves");
				System.out.println("Time elapsed is "+(System.currentTimeMillis()-timeElapsed)+" milli seconds");
		}
	}
}
