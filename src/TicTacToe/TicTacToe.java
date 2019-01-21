package TicTacToe;

import TicTacToe.MinMax.AI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {

    Window view;
    Board board;

    public TicTacToe(Board board, Window view) {
        this.view = view;
        this.board = board;

        addListeners();
    }

    private void addListeners() {
        int boardsize = board.getBoardsize();

        for(int i = 0; i < boardsize; i++) {
            for(int j = 0; j < boardsize; j++) {
                view.addFieldListener(new FieldListener(), i, j);
            }
        }
    }

    public void start() {
        boolean AIShouldStart = view.askBeginner();
        if(AIShouldStart) {
            AI.makeMove(board.getCurrentPlayer(), board, Double.POSITIVE_INFINITY);
            view.redraw();
        }
    }

    class FieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = 0;
            if (e.getSource() instanceof JButton) {
                id = Integer.parseInt(((JButton) e.getSource()).getName());
            }
            board.move(id);
            view.redraw();
            AI.makeMove(board.getCurrentPlayer(), board, Double.POSITIVE_INFINITY);
            view.redraw();
        }
    }
}
