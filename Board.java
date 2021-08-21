package SudokuSolver;

/*
* This class is responsible for creating a 2d, 9x9 integer array to
* represent the sudoku board!
*/
public class Board {

    private Integer[][] _board = new Integer[9][9];
    
    //class constructor
    public Board() {
    }

    //class copy constructor
    public Board(Board copyMe) {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                _board[j][i] = copyMe.getBoard(j,i);
            }
        }
    }

    //basic accessor (getter) method to get the board instance variable
    public Integer getBoard(int j, int i) {
        return _board[j][i];
    }

    //basic mutator (setter) method to set an element of the board
    public void setBoard(Integer A, int j, int i) {
        _board[j][i] = A;
    }
}
