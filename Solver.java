package SudokuSolver;

import java.util.HashSet;
import java.util.Set;

public class Solver {

    private Set<Integer> _checker;

    public Solver() {
        _checker = new HashSet<Integer>();
        _checker.add(1);
        _checker.add(2);
        _checker.add(3);
        _checker.add(4);
        _checker.add(5);
        _checker.add(6);
        _checker.add(7);
        _checker.add(8);
        _checker.add(9);
    }

    public boolean checkRow(Board board, int row) {
        Set<Integer> hset = new HashSet<Integer>(_checker);
        for (int col = 0; col < 9; col++) {
            Integer i = board.getBoard(col, row);
            if (i != null) {
                if (!hset.contains(i)) {
                    return false;
                }
                else {
                    hset.remove(i);
                }
            }
        }
        return true;
    }

    public boolean checkColumn(Board board, int col) {
        Set<Integer> hset = new HashSet<Integer>(_checker);
        for (int row = 0; row < 9; row++) {
            Integer i = board.getBoard(col, row);
            if (i != null) {
                if (!hset.contains(i)) {
                    return false;
                }
                else {
                    hset.remove(i);
                }
            }
        }
        return true;
    }

    public boolean checkSquare(Board board, int anchorCol, int anchorRow) {
        Set<Integer> hset = new HashSet<Integer>(_checker);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Integer i = board.getBoard(anchorCol + col, anchorRow + row);
                if (i != null) {
                    if (!hset.contains(i)) {
                        return false;
                    } else {
                        hset.remove(i);
                    }
                }
            }
        }
        return true;
    }

    public boolean checkAllRows(Board board) {
        for (int row = 0; row < 9; row++) {
            if (!checkRow(board, row)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAllColumns(Board board) {
        for (int col = 0; col < 9; col++) {
            if (!checkColumn(board, col)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAllSquares(Board board) {
        for (int row = 0; row < 9; row+=3) {
            for (int col = 0; col < 9; col+=3) {
                if (!checkSquare(board, col, row)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(Board board) {
        return checkAllRows(board) && checkAllColumns(board) && checkAllSquares(board);
    }

    public boolean isComplete(Board board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getBoard(col, row) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCompleteAndValid(Board board) {
        return isValid(board) && isComplete(board);
    }

    public Board solve(Board board) {
        if (!isValid(board)) {
            return null;
        }

        if (isCompleteAndValid(board)) {
            return board;
        }

        Board partial = new Board(board);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Integer i = partial.getBoard(col, row);
                if (i == null) {
                    Set<Integer> tryUs = new HashSet<Integer>(_checker);
                    for (Integer number:tryUs) {
                        partial.setBoard(number, col, row);
                        Board solution = solve(partial);
                        if (solution != null) {
                            return solution;
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

}
