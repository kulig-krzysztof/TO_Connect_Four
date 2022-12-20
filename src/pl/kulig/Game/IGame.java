package pl.kulig.Game;

import java.awt.*;

public interface IGame {
    void startGame();

    boolean isFirstPlayerTurn();

    boolean checkForWinner(int column);

    boolean checkForWinnerInGui(int column);

    boolean checkForWin(int column, Color winnersColor);

    void reset();

    int startGameGui(int col);
}
