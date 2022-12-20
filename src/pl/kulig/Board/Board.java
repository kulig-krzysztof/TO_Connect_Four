package pl.kulig.Board;

import pl.kulig.Coin.Coin;
import pl.kulig.State.Empty;
import pl.kulig.State.IState;
import pl.kulig.State.Red;
import pl.kulig.State.Yellow;

import java.awt.*;

public class Board {
    private final static int rows = 6;
    private final static int columns = 7;
    private final Coin[][] board;
    private static Board instance;

    private Board() {
        board = new Coin[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = new Coin(new Empty(), row, col);
            }
        }
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public Coin[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void clearBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Coin(new Empty(), i, j);
            }
        }
    }

    public int addCoin(int column, Color color) {
        if (column >= 0 && column < columns) {
            if (board[0][column].getColor() == Color.white) {
                int addedToRow = -1;
                for (int row = rows - 1; row >= 0; row--) {
                    if (board[row][column].getColor() == Color.white) {
                        if (color == Color.red) {
                            //board[row][column] = new Coin(new Red(), row, column);
                            board[row][column].setState(new Red());
                        } else {
                            //board[row][column] = new Coin(new Yellow(), row, column);
                            board[row][column].setState(new Yellow());
                        }
                        addedToRow = row;
                        break;
                    }
                }
                return addedToRow;
            } else {
                System.out.println("Ta kolumna jest pelna, wybierz inna!");
                return -1;
            }
        } else {
            System.out.println("Poza granicami!");
            return -1;
        }
    }

    public void printBoard() {
        for (int row = 0; row < rows; row++) {
            System.out.print("|");
            for (int col = 0; col < columns; col++) {
                if (board[row][col].getColor() == Color.white) {
                    System.out.print("_");
                } else if (board[row][col].getColor() == Color.red) {
                    System.out.print("R");
                } else {
                    System.out.print("Y");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }
}


