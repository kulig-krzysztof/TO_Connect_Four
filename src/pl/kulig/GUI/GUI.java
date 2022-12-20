package pl.kulig.GUI;

import pl.kulig.Board.Board;
import pl.kulig.Game.Game;
import pl.kulig.Game.IGame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GUI extends JFrame {
    private final Container container;
    private final IGame game;
    private final String title = "Connect Four Game - ";

    Board board = Board.getInstance();
    int rows = board.getRows();
    int columns = board.getColumns();
    int width = 750;
    int height = 650;
    private ImageIcon empty = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/white.png")));
    private ImageIcon red = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/red.png")));
    private ImageIcon yellow = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/yellow.png")));

    private void insert(JButton button) {
        int position = Integer.parseInt(button.getName());
        int column = position % 10;

        boolean firstPlayerTurn = game.isFirstPlayerTurn();
        if(firstPlayerTurn) {
            setTitle(title + "Tura gracza żółtego");
        }
        else {
            setTitle(title + "Tura gracza czerwonego");
        }

        int success = game.startGameGui(column);

        if(success != -1) {
            String color;

            JButton buttonToChange = (JButton) (container.getComponent(columns * success + column));

            if (!game.isFirstPlayerTurn()) {
                buttonToChange.setIcon(red);
                color = "Czerwony";

            } else {
                buttonToChange.setIcon(yellow);
                color = "Żółty";
            }
            if (game.checkForWinnerInGui(column)) {
                JOptionPane.showMessageDialog(null, "Gracz " + color + " wygrał!");
                int reply = JOptionPane.showConfirmDialog(null, "Czy chcesz zagrać jeszcze raz?", null, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    System.out.println("Wznawiam grę...");
                    game.reset();
                    resetBoard();
                } else {
                    System.exit(0);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Wybierz poprawną pozycję");
        }
    }

    public GUI() {
        game = new Game();
        container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                JButton button = new JButton();
                button.setIcon(empty);
                button.setPreferredSize(new Dimension(100,100));
                button.setName(Integer.toString(row * 10 + column));
                button.addActionListener(actionEvent -> insert(((JButton) (actionEvent.getSource()))));
                container.add(button);
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title + "Tura gracza czerwonego");
        setLocationRelativeTo(null);
        setSize(width,height);
        setVisible(true);

    }


    public void resetBoard() {
        for(int row = 0; row < rows; row++)
            for(int column = 0; column < columns; column++) {
                ((JButton)(container.getComponent(columns * row + column))).setIcon(empty);
                setTitle(title + "Tura gracza czerwonego");
            }
    }

}
