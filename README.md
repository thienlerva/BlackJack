# BlackJack Game

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        int[][] maze = { { 1, 0, 0, 0, 0 }, 
                         { 1, 1, 1, 1, 0 }, 
                         { 0, 0, 1, 0, 0 }, 
                         { 1, 1, 1, 1, 0 },
                         { 1, 1, 1, 1, 1 }}; 
        
        RatMaze game = new RatMaze(maze);
        System.out.println("Initial maze: ");
        game.print(maze);
        game.solveMaze();
    }
}

class RatMaze {
 
    private int[][] maze;
    private int[][] solution;
    private final int ROW;
    private final int COL;
    
    public RatMaze(int[][] maze) {
            this.maze = maze;
            this.ROW = maze.length;
            this.COL = maze[0].length;
            initializeSolution();
    }
    
    private void initializeSolution() {
            solution = new int[ROW][COL];
        for(int i=0;i<ROW;i++) {
            for(int j=0;j<COL;j++) {
                solution[i][j] = 0;
            }
        }
    }
    
    private boolean isSafe(int x, int y) {
          return x>=0 && x<ROW & y>=0 && y<COL && maze[x][y] == 1;  
    
    }
    
    /* This function solves the Maze problem using 
       Backtracking. It mainly uses solveMazeUtil() 
       to solve the problem. It returns false if no 
       path is possible, otherwise return true and 
       prints the path in the form of 1s. Please note 
       that there may be more than one solutions, this 
       function prints one of the feasible solutions.*/
    public void solveMaze() {
        if(!solveMazeUtil(0,0)) {
            System.out.println("Solution doesn't exist!");
            return;
        }
        System.out.println("Solution is: ");
        print(solution);
    }
    
    
    /* A recursive utility function to solve Maze 
       problem */
    private boolean solveMazeUtil(int x, int y) {
        
        // if (x, y is goal) return true 
        if(x==ROW-1 && y==COL-1 && isSafe(x,y)) {
            solution[x][y]=1;
            return true;
        }
        
        // Starting point 0,0
        if(isSafe(x,y)) {
            // mark x, y as part of solution path
            solution[x][y] = 1;
            
            //Move downward in y direction
            if(solveMazeUtil(x,y+1)) {
                return true;
            }
            
            // If can't move downward, move forward in x direction
            if(solveMazeUtil(x+1,y)) {
                return true;
            }
            
            /* If none of the above movements works then 
               BACKTRACK: unmark x, y as part of solution 
               path */
            solution[x][y] = 0;
            return false;
        }
        return false;
    }
    
    public int[][] getSolution() {
        return this.solution;
    }
    
    public void print(int[][] sol) {
            String str = "\n-----------------\n";
        for(int i = 0; i<ROW; i++) {
            str += "  | ";
            for(int j=0;j<COL;j++) {
                str += sol[i][j] + " ";
            }
            str += "|\n";
        }
        str += "----------------\n";
        
        System.out.println(str);
    }
    
    @Override
    public String toString() {
        
        int row = maze.length;
        int col = maze[0].length;
        String str = "\n-----------------\n";
        for(int i = 0; i<row; i++) {
            str += "  | ";
            for(int j=0;j<col;j++) {
                str += maze[i][j] + " ";
            }
            str += "|\n";
        }
        str += "----------------\n";
        return str;
        }
}


------------------------------------------------------------------

Maze

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        Maze mazeToExplore = new Maze();
        Maze aux = mazeToExplore.clone();
	    if (aux.findPathFrom(1, 0)) {
	        System.out.println("maze solved");
			aux.print('-');
			System.out.println("original maze");
			mazeToExplore.print('-');
			
		} else {
		    System.out.println("no solution found");
		}
    }
}

class Maze implements Cloneable {
 
    private final char[] mazeSymbols = { '#', '.', '+', '*'};
    // use symbols to make reading the output easier...
	// 0 - obstacle - '#'
	// 1 - open space - '.'
	// 2 - path taken - '+'
	// 3 - goal - '*'
    private final int[][] maze;
    
    // 0 - obstacle
	// 1 - open space
	// 2 - path taken
	// 3 - goal 
	private final static int[][] DEFAULT_MAZE = 
		{{0, 0, 1, 1, 1, 1, 1, 1},
		{2, 0, 1, 0, 0, 0, 1, 1},
		{1, 0, 1, 0, 0, 0, 0, 0},
		{1, 1, 1, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 1, 3, 1, 1},
		{0, 0, 1, 0, 1, 0, 0, 1},
		{1, 0, 1, 1, 1, 0, 0, 0},
		{1, 1, 1, 0, 1, 1, 0, 0}};
    
    public Maze(int[][] maze) {
        this.maze = maze;
    }
    
    public Maze() {
        this(DEFAULT_MAZE);
    }
    
    /*Todo: change this method using only one loop 
	* and Arrays.copyOf(maze[i], maze[i].length)
	*/
	public Maze clone(){
	   int[][] clonedMaze = new int[maze.length][maze[0].length];
	   for(int i=0; i<maze.length; i++){
	       for (int j= 0; j< maze[i].length; j++){
	         clonedMaze[i][j] = maze[i][j];
	       }
	   }
	   return new Maze(clonedMaze);
	}
    
    // A position is available if: (1) it is inside the bounds of the maze 
	// (2) if it is an open space or (3) it is the goal 
    private boolean isAvailablePosition(int x, int y) {
            return x >=0 && x<maze.length && y >=0 && y < maze[0].length && (maze[x][y]==3 || maze[x][y]==1);
    }
    
    public boolean findPathFrom(int x, int y) {
          // when we reach the goal we have solved the problem  
        if(maze[x][y]==3) { return true; }
        
        // add the position to our path changing its value to '2'
        maze[x][y] = 2;
        
        //try all available neighbours 
		//West (row, col-1), east (row, col+1), South (row+1, col) and North (row-1, col)
		// if any of these return true then we have solved the problem
        if(isAvailablePosition(x-1, y) && findPathFrom(x-1, y)) {
            return true;
        }
         if(isAvailablePosition(x+1, y) && findPathFrom(x+1, y)) {
            return true;
        }
        if(isAvailablePosition(x, y+1) && findPathFrom(x, y+1)) {
            return true;
        }
        if(isAvailablePosition(x, y-1) && findPathFrom(x, y-1)) {
            return true;
        }
        
        //If none of previous positions is valid or matches the goal, it is necessary to revert the 
		//temporary state. This reversal or backtrack is what give name to the algorithm: backtracking
		maze[x][y] = 1;

		return false;
    }
    
    public void print() {
        for(int row = 0; row < maze.length; ++row) {
			for(int col = 0; col < maze[row].length; ++col) {
				System.out.print(mazeSymbols[maze[row][col]]);
			}
			System.out.println();
		}
    }
    
    public void print(char footprint){
		setFootprint(footprint);
		print();

	}
	private void setFootprint(char footprint){
		mazeSymbols[2] = footprint;
	}
    
}
