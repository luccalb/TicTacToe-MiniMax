package TicTacToe;

import java.util.HashSet;

public class Board {
    private static final int BOARDSIZE = 3;

    private char player;
    private boolean gameOver;
    private char winner = ' ';
    private HashSet<Integer> movesAvailable;
    private int moveCount = 0;
    private char[][] textFields;

    public Board(){
        this.player = 'X';

        movesAvailable = new HashSet<>();
        for(int i = 0; i< BOARDSIZE*BOARDSIZE; i++){
            movesAvailable.add(i);
        }

        textFields = new char[BOARDSIZE][BOARDSIZE];
    }

    public char getField(int x, int y) {
        return textFields[x][y];
    }

    public int getBoardsize(){
        return BOARDSIZE;
    }

    public void changePlayer(){
        if(this.player == 'X') this.player = 'O';
        else this.player = 'X';
    }

    public char getCurrentPlayer(){
        return this.player;
    }

    public boolean gameIsOver() {
        return gameOver;
    }

    public char getWinner() {
        return winner;
    }

    public void setCurrentPlayer(char currentPlayer) {
        this.player = currentPlayer;
    }

    public HashSet<Integer> getAvailableMoves(){
        return movesAvailable;
    }

    public Board getDeepCopy() {
        Board board = new Board();

        for (int i = 0; i < board.textFields.length; i++) {
            board.textFields[i] = this.textFields[i].clone();
        }

        board.setCurrentPlayer(getCurrentPlayer());
        board.winner = this.winner;
        board.movesAvailable    = new HashSet<>();
        board.moveCount = this.moveCount;
        board.movesAvailable.addAll(this.movesAvailable);
        board.gameOver = this.gameOver;
        return board;
    }

    public int[] move(int id) {
        int[] coords = getCoordsByID(id, BOARDSIZE);

        textFields[coords[0]][coords[1]] = this.player;

        this.moveCount++;
        this.movesAvailable.remove(id);

        if(moveCount == BOARDSIZE * BOARDSIZE) {
            this.winner = ' ';
            this.gameOver = true;
        }

        checkWinner(coords);
        this.changePlayer();

        return coords;

    }

    private void checkWinner(int[] coords) {
        int y = coords[0];
        int x = coords[1];
        checkRow(y);
        checkColumn(x);
        checkDiagonalFromTopLeft(x, y);
        checkDiagonalFromTopRight(x, y);
    }

    private void checkDiagonalFromTopRight(int x, int y) {
        if (BOARDSIZE -1-x == y) {
            for (int i = 1; i < BOARDSIZE; i++) {
                if (textFields[BOARDSIZE -1-i][i] != textFields[BOARDSIZE -i][i-1]) {
                    break;
                }
                if (i == BOARDSIZE -1) {
                    winner = player;
                    gameOver = true;
                }
            }
        }
    }

    private void checkDiagonalFromTopLeft(int x, int y) {
        if (x == y) {
            for (int i = 1; i < BOARDSIZE; i++) {
                if (textFields[i][i] != textFields[i-1][i-1]) {
                    break;
                }
                if (i == BOARDSIZE -1) {
                    winner = player;
                    gameOver = true;
                }
            }
        }
    }

    private void checkColumn(int column) {
        for (int i = 1; i < BOARDSIZE; i++) {
            if (textFields[i][column] != textFields[i-1][column]) {
                break;
            }
            if (i == BOARDSIZE -1) {
                winner = player;
                gameOver = true;
            }
        }
    }

    private void checkRow(int row) {
        for (int i = 1; i < BOARDSIZE; i++) {
            if (textFields[row][i] != textFields[row][i-1]) {
                break;
            }
            if (i == BOARDSIZE -1) {
                winner = player;
                gameOver = true;
            }
        }
    }

    public int[] getCoordsByID(int id, int boardsize) {
        int[] coords = new int[2];

        int counter = 0;
        for(int x = 0; x < boardsize; x++){
            for( int y = 0; y < boardsize; y++){
                if(counter == id) {
                    coords[0] = x;
                    coords[1] = y;
                }
                counter++;
            }
        }


        return coords;
    }
}
