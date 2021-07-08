package SudokuSolver;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class PaneOrganizer {

    private GridPane _root;
    private TextField[][] _textFields;
    private Pane _linePane;
    private StackPane _stackPane;

    public PaneOrganizer() {
        _textFields = new TextField[9][9];
        _root = new GridPane();
        _root.setAlignment(Pos.CENTER);
        _root.setVgap(20);
        _root.setHgap(10);
        _root.setPadding(new Insets(25, 25, 25, 25));

        Line line1 = new Line(140, 11, 140, 395);
        Line line2 = new Line(260, 11, 260, 395);
        Line line3 = new Line(25, 135, 375, 135);
        Line line4 = new Line(25, 270, 375, 270);
        line1.setStyle("-fx-stroke:rgb(200,200,200)");
        line2.setStyle("-fx-stroke:rgb(200,200,200)");
        line3.setStyle("-fx-stroke:rgb(200,200,200)");
        line4.setStyle("-fx-stroke:rgb(200,200,200)");
        line1.setStrokeWidth(3);
        line2.setStrokeWidth(3);
        line3.setStrokeWidth(3);
        line4.setStrokeWidth(3);
        _linePane = new Pane();
        _linePane.getChildren().addAll(line1, line2, line3, line4);


        _stackPane = new StackPane(_linePane, _root);


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
        return _stackPane;
    }
}
