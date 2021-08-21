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

    //helper method for checking whether the inputted row is valid (no repeated integers in the row's cells)
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

    //helper method for checking whether the inputted column is valid (no repeated integers in the column's cells)
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

    //helper method for checking whether the 3x3 square containing the intersection of the inputted row and column
    //is valid (no repeated integers in the square's cells)
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

    //helper method to check all 9 rows of the board
    public boolean checkAllRows(Board board) {
        for (int row = 0; row < 9; row++) {
            if (!checkRow(board, row)) {
                return false;
            }
        }
        return true;
    }

    //helper method to check the validity of all 9 columns in the board
    public boolean checkAllColumns(Board board) {
        for (int col = 0; col < 9; col++) {
            if (!checkColumn(board, col)) {
                return false;
            }
        }
        return true;
    }

    //herlper method to check the validity of all 9 3x3 squares in the board
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

    //helper method to check the validity of the entire board (checks all rows, columns, and squares)
    public boolean isValid(Board board) {
        return checkAllRows(board) && checkAllColumns(board) && checkAllSquares(board);
    }

    //helper method to check whether all the cells in the board have been filled in
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

    //helper method that determines if the board is both completely full and valid
    public boolean isCompleteAndValid(Board board) {
        return isValid(board) && isComplete(board);
    }

    //main method that implements a backtracking algorithm that solves the board
    public Board solve(Board board) {
        //if inputted board is invalid, return null
        if (!isValid(board)) {
            return null;
        }

        //if the inputted board is already valid and complete, return the inputted board
        if (isCompleteAndValid(board)) {
            return board;
        }
        
        Board partial = new Board(board); //make a copy of the inputted board
        
        //this is the backtracking algorithm that actually solves the board
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Integer i = partial.getBoard(col, row);
                if (i == null) {
                    Set<Integer> tryUs = new HashSet<Integer>(_checker);
                    for (Integer number:tryUs) {
                        partial.setBoard(number, col, row);
                        Board solution = solve(partial);
                        if (solution != null) {
                            return solution; //return the fully solved, solution board!
                        }
                    }
                    return null; //return null if the board is unsolvable
                }
            }
        }
        return null; //return null if nothing is ever returned
    }
}
