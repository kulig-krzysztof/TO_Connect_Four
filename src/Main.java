import pl.kulig.Board.Board;
import pl.kulig.GUI.GUI;
import pl.kulig.Game.Game;
import pl.kulig.Game.IGame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
