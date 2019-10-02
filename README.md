# BlackJack Game

class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    
    public TicTacToe(char player) {
        board = new char[3][3];
        this.currentPlayer = player;
        initializeBoard();
    }
    
    public void initializeBoard() {
     
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
        
    }
    
    public void printBoard() {
        System.out.println("-------------");
        for(int row=0; row<3;row++) {
            System.out.print("|");
            for(int col=0; col<3; col++) {
                System.out.print(" - |");
            }
            System.out.println("\n-------------");
        }
    }
    
    public boolean isBoardFull() {
        return false;
    }
    
    public boolean isWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }
    
    private boolean checkRows() {
        for(int row = 0; row < 3; row++) {
            return (checkRowCol(board[row][0], board[row][1], board[row][2]));
        }
        return false;
    }
    
    private boolean checkColumns() {
        for(int col = 0; col < 3; col++) {
            return checkRowCol(board[0][col], board[1][col], board[2][col]);
        }
        return false;
    }
    
    private boolean checkDiagonals() {
        return checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[3][0]);
    }
    
    private boolean checkRowCol(char c1, char c2, char c3) {
        return c1=='-' && c1==c2 && c2==c3;
    }
    
    public void changePlayer() {
        if(currentPlayer == 'x') {
            currentPlayer = '0';
        } else {
            currentPlayer = 'x';
        }
    }
    
    public boolean placeMark(int row, int col) {
        if(row>=0 && row<3 && col>=0 && col<3) {
            if(board[row][col]=='-') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
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
