# BlackJack Game

class TicTacToe
{  
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        Game game = new Game('o');
	
	Computer com = new Computer('x', false);
        
        System.out.println("Tic-Tac-Toe!");
        do
        {
            System.out.println("Current board layout:");
            System.out.println(game);
            int row, col;
            do
            {
		
                System.out.print("Player " + game.getCurrentPlayer() + ", enter an empty row to place your mark! or 9 to quit ");
                row = scan.nextInt()-1;
		if(row==8) { System.exit(1); }
		System.out.print("Player " + game.getCurrentPlayer() + ", enter an empty col to place your mark! ");
                col = scan.nextInt()-1;
		if(row>2 || row < 0 || col>2 || col<0) {
			throw new IllegalArgumentException("Wrong input, program terminated!");
		}
		
            }
            while (!game.placeMark(row, col));
	    com.myBoard(game.getBoard());
	    System.out.println(com);
            game.changePlayer();
		game.placeMarkByComputer();
		game.changePlayer();
        }
        while(!game.isWinner() && !game.isBoardFull());
        if (game.isBoardFull() && !game.isWinner())
        {
            System.out.println("The game was a tie!");
        }
        else
        {
            System.out.println("Current board layout:");
            System.out.println(game);
            game.changePlayer();
            System.out.println(Character.toUpperCase(game.getCurrentPlayer()) + " Wins!");
}
	
}
}


class Game {
    private char[][] board;
    private char currentPlayer;
	private boolean isComputerTurn;

    public Game(char player) {
        board = new char[3][3];
        currentPlayer = player;
        initializeBoard();
	isComputerTurn = false;
    }

    //Gives us access to currentPlayerMark
    public char getCurrentPlayer()
    {
        return currentPlayer;
    }


    // Set/Reset the board back to all empty values.
    public void initializeBoard() {

        // Loop through rows
        for (int i = 0; i < 3; i++) {

            // Loop through columns
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }



    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }


    // Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
		System.out.print(board[i][j]);
                if (board[i][j] == '-') {
                    return false;
                }
            }
		System.out.println();
        }

        return true;
    }


    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    public boolean isWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }


    // Loop through rows and see if any are winners.
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }


    // Loop through columns and see if any are winners.
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }


    // Check the two diagonals to see if either is a win. Return true if either wins.
    private boolean checkDiagonals() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }


    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != '-') && (c1 == c2) && (c2 == c3);
    }


    // Change player marks back and forth.
    public void changePlayer() {
	
		if (currentPlayer == 'x') {
            currentPlayer = 'o';
        }
        else {
            currentPlayer = 'x';
        }
	
        
    }

    // Places a mark at the cell specified by row and col with the mark of the current player.
    public boolean placeMark(int row, int col) {

        // Make sure that row and column are in bounds of the board.
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayer;
                    return true;
                }
            
        	}

        	return false;
	}

	
	// AI computer playing
	public void placeMarkByComputer() {
		boolean[][] availableSpot = findAvailableSpots();
		if(availableSpot!=null) {
			Random r = new Random();
			int row, col;
			do {
				row = r.nextInt(3);
				col = r.nextInt(3);
				if(availableSpot[row][col]) {
					board[row][col] = this.currentPlayer;
				}
			} while(!availableSpot[row][col]);
		}
	}

	private boolean[][] findAvailableSpots() {
		if(!isBoardFull()) {
		boolean[][] spot = new boolean[3][3];

		for(int row=0; row<3; row++) {
			for(int col=0; col<3; col++) {
				if(board[row][col]=='-') {
					spot[row][col] = true;
				}
			}
		}
		return spot;
		} else {
			return null;
		}
	}

	public void setComputerTurn(boolean isComputerTurn) {
		this.isComputerTurn = isComputerTurn;
	}

	

	public String toString() {
    		String result = "\n-------------\n";
    
    		for(int row=0; row<3;row++) {
        		result += "| ";
        		for(int col=0;col<3;col++) {
            			result += board[row][col] + " | ";
        		}
        		result += "\n-------------\n";
    		}
    		return result;
	}
    
}

class Computer {

	private char name;
	boolean[][] myBoard;
	private boolean isMyTurn;

	public Computer(char name, boolean isMyTurn) {
		this.name = name;
		this.isMyTurn = isMyTurn;
		this.myBoard = new boolean[3][3];
	}

	public void myBoard(char[][] board) {
		for(int row=0;row<3;row++) {
			for(int col=0;col<3;col++) {
				
				if(board[row][col]==this.name || board[row][col]=='-') {

					myBoard[row][col]=true;
				} 
			}
		}

	
	}

	public String toString() {
		String result = "\n-------------\n";
    
    		for(int row=0; row<3;row++) {
        		result += "| ";
        		for(int col=0;col<3;col++) {
            			result += myBoard[row][col] + " | ";
        		}
        		result += "\n-------------\n";
    		}
    		return result;
	}
	
}
