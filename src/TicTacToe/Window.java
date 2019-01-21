package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Window extends JFrame{
    public Board board;
    private JPanel container;
    private JButton[][] fields;


    public Window(Board board){
        this.board = board;

        container = new JPanel();
        container.setLayout(new GridLayout(3,3));

        int boardsize = board.getBoardsize();

        fields = new JButton[boardsize][boardsize];

        int id = 0;
        for(int i = 0; i < boardsize; i++) {
            for(int j = 0; j < boardsize; j++) {
                fields[i][j] = new JButton();
                fields[i][j].setName(String.valueOf(id));
                fields[i][j].setFont(new Font("Arial", Font.BOLD, 70));
                container.add(fields[i][j]);
                id++;
            }
        }

        this.setContentPane(container);
        this.pack();

        this.setTitle("Intelligent TicTacToe");
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    void addFieldListener(ActionListener l, int x, int y){
        fields[x][y].addActionListener(l);
    }

    public void redraw(){
        for(int i = 0; i< board.getBoardsize()* board.getBoardsize(); i++) {
            int[] coords = board.getCoordsByID(i, board.getBoardsize());
            char toDraw = board.getField(coords[0], coords[1]);
            String sToDraw = String.valueOf(toDraw);
            if(sToDraw.equals("X") || sToDraw.equals("O")) {
                fields[coords[0]][coords[1]].setText(sToDraw);
                fields[coords[0]][coords[1]].setEnabled(false);
            }
        }
        if(board.gameIsOver()){
            String ObjButtons[] = {"Close"};
            int PromptResult;
            if(board.getWinner() != ' ') {
                String winner = "Gewinner: " + board.getWinner();
                PromptResult = JOptionPane.showOptionDialog(null,
                        winner, "Game Over!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, null);
            } else {
                String winner = "Unentschieden." + board.getWinner();
                PromptResult = JOptionPane.showOptionDialog(null,
                        winner, "Game Over!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, null);
            }

            if(PromptResult==0)
            {
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    public boolean askBeginner() {
        String ObjButtons[] = {"Ich","AI"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Wer soll anfangen?", "Game Settings",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons,ObjButtons[1]);
        if(PromptResult==1)
        {
            return true;
        }
        else return false;
    }
}
