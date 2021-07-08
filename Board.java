package SudokuSolver;

public class Board {

    private Integer[][] _board = new Integer[9][9];

    public Board() {
    }

    public Board(Board copyMe) {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                _board[j][i] = copyMe.getBoard(j,i);
            }
        }
    }

    public Integer getBoard(int j, int i) {
        return _board[j][i];
    }

    public void setBoard(Integer A, int j, int i) {
        _board[j][i] = A;
    }
}
