package TicTacToe.MinMax;

import TicTacToe.Board;

public class AI {

    private static double maxPly;

    private AI(){}

    public static void makeMove(char currentPlayer, Board board, double maxPly) {
        if(maxPly < 1) {
            throw new IllegalArgumentException("Maximum Depth must be greater than 0.");
        }

        AI.maxPly = maxPly;
        miniMax(currentPlayer, board, 0);
    }

    private static int miniMax(char currentPlayer, Board board, int currentPly) {
        if(currentPly++ == maxPly || board.gameIsOver()) {
            return score(currentPlayer, board);
        }

        if(board.getCurrentPlayer() == currentPlayer) {
            return getMax(currentPlayer, board, currentPly);
        } else{
            return getMin(currentPlayer, board, currentPly);
        }

    }

    private static int getMin(char currentPlayer, Board board, int currentPly) {
        double bestScore = Double.POSITIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);

            int score = miniMax(currentPlayer, modifiedBoard, currentPly);

            if (score <= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }

        }

        board.move(indexOfBestMove);
        return (int)bestScore;
    }

    private static int getMax(char currentPlayer, Board board, int currentPly) {
        double bestScore = Double.NEGATIVE_INFINITY;
        int indexOfBestMove = -1;

        for(Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);

            int score = miniMax(currentPlayer, modifiedBoard, currentPly);

            if(score >= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }

        }
        board.move(indexOfBestMove);
        return (int)bestScore;
    }

    private static int score(char currentPlayer, Board board) {
        if(currentPlayer == ' ') {
            throw new IllegalArgumentException("Player must be X or O");
        }

        char opponent = (currentPlayer == 'X') ? 'O' : 'X';

        if(board.gameIsOver() && board.getWinner() == currentPlayer) {
            return 10;
        } else if (board.gameIsOver() && board.getWinner() == opponent){
            return -10;
        } else {
            return 0;
        }
    }
}
