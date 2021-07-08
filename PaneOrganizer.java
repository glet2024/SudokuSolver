package SudokuSolver;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PaneOrganizer {

    private GridPane _root;
    private TextField[][] _textFields;

    public PaneOrganizer() {
        _textFields = new TextField[9][9];
        _root = new GridPane();
        _root.setAlignment(Pos.CENTER);
        _root.setVgap(20);
        _root.setHgap(10);
        _root.setPadding(new Insets(25, 25, 25, 25));

        this.setupBoard();
        this.setupClearButton();
        this.setupSolveButton();
    }


    public void setupBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField tf = new TextField();
                tf.setAlignment(Pos.CENTER);
                _textFields[j][i] = tf;
                _root.add(tf, j, i);
            }
        }
    }

    public void setupClearButton() {
        Button clear = new Button("Clear Board");
        HBox buttonPane = new HBox(10);
        buttonPane.getChildren().add(clear);
        buttonPane.setAlignment(Pos.BOTTOM_LEFT);
        _root.add(buttonPane, 0, 9, 3, 1);

        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        _textFields[j][i].setText("");
                        _textFields[j][i].setStyle("-fx-background-color:rgb(255,255,255)");
                    }
                }
            }
        } );
    }

    public void setupSolveButton() {
        Button solve = new Button("Solve");
        HBox buttonPane = new HBox(10);
        buttonPane.getChildren().add(solve);
        buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
        _root.add(buttonPane, 6, 9, 3, 1);

        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Solver solver = new Solver();
                Board board = new Board();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        try {
                            Integer integer = Integer.valueOf(_textFields[j][i].getText().trim());
                            board.setBoard(integer, j, i);
                        } catch(NumberFormatException e) {
                        }
                    }
                }

                Board solution = solver.solve(board);


                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (_textFields[j][i].getText().trim().equals("")) {
                            if (solution == null) {
                                _textFields[j][i].setStyle("-fx-background-color:rgb(255,205,205)");
                            }
                            else {
                                _textFields[j][i].setStyle("-fx-background-color:rgb(205,255,205)");
                                _textFields[j][i].setText("" + solution.getBoard(j, i));
                            }
                        }
                        else {
                            _textFields[j][i].setStyle("-fx-background-color:rgb(255,255,255)");
                        }
                    }
                }
            }
        } );
    }


    public Pane getRoot() {
        return _root;
    }
}
