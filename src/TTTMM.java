import TicTacToe.Board;
import TicTacToe.TicTacToe;
import TicTacToe.Window;

public class TTTMM {
    public static void main(String[] args) {
        Board model = new Board();
        Window view = new Window(model);
        TicTacToe controller = new TicTacToe(model, view);

        controller.start();
    }
}
