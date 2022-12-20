package pl.kulig.Game;

import pl.kulig.Board.Board;
import pl.kulig.Coin.Coin;

import java.awt.*;
import java.util.Scanner;

public class Game implements IGame {

    private final Board board;

    private boolean firstPlayerTurn;

    @Override
    public boolean isFirstPlayerTurn() {
        return firstPlayerTurn;
    }


    public Game() {
        board = Board.getInstance();
        firstPlayerTurn = true;
    }

    @Override
    public void startGame() {
        boolean running = true;

        while(running) {
            board.printBoard();
            Color color;

            if(firstPlayerTurn) {
                color = Color.red;
                System.out.println("Czerwony gracz wybiera");
            }
            else {
                color = Color.yellow;
                System.out.println("Żółty gracz wybiera");
            }

            System.out.println("Wybierz kolumnę od 1 do 7: ");

            Scanner scanner = new Scanner(System.in);
            int column = scanner.nextInt() - 1;
            int addedToRow = board.addCoin(column, color);

            if(addedToRow != -1) {
                if(checkForWinner(column)) {
                    if(firstPlayerTurn) {
                        System.out.println("Czerwony gracz wygrał");
                    }
                    else {
                        System.out.println("Żółty gracz wygrał");
                    }
                    board.printBoard();
                    System.out.println("Chcesz zagrać jeszcze raz?");
                    System.out.println("T/N");
                    Scanner scanner1 = new Scanner(System.in);
                    String playAgain = scanner1.nextLine();
                    if(playAgain.equalsIgnoreCase("t")) {
                        reset();
                    }
                    else {
                        running = false;
                    }
                }
                    firstPlayerTurn = !firstPlayerTurn;
            }
        }
    }

    @Override
    public boolean checkForWinner(int column) {
        Color winnersColor = firstPlayerTurn ? Color.red : Color.yellow;
        return checkForWin(column, winnersColor);
    }

    @Override
    public boolean checkForWinnerInGui(int column) {
        Color winnersColor = !firstPlayerTurn ? Color.red : Color.yellow;
        return checkForWin(column,winnersColor);
    }

    @Override
    public boolean checkForWin(int column, Color winnersColor) {
        boolean isWinner = false;
        int rows = board.getRows();
        int columns = board.getColumns();
        Coin[][] gameBoard = board.getBoard();

        for (int row = 0; row < rows; row++) {
            if (gameBoard[row][column].getColor() != Color.white) {
                int streak = 3;
                //w dol
                for (int iRow = row + 1; iRow < rows; iRow++) {
                    if (gameBoard[iRow][column].getColor() == winnersColor) {
                        streak--;
                        if (streak == 0) isWinner = true;
                    } else {
                        streak = 3;
                    }
                }

                //poziomo
                streak = 4;
                for (int iCol = column - 3; iCol <= column + 3; iCol++) {
                    if (iCol < 0) continue;
                    if (iCol >= columns) break;
                    if (gameBoard[row][iCol].getColor() != Color.white && gameBoard[row][iCol].getColor() == winnersColor) {
                        streak--;
                        if (streak == 0) {
                            isWinner = true;
                        }
                    } else {
                        streak = 4;
                    }
                }

                //po skosie z gory do prawej
                streak = 4;
                for (int iRow = row - 3, iCol = column - 3; iRow <= row + 3 && iCol <= column + 3; iRow++, iCol++) {
                    if (iRow < 0 || iCol < 0) continue;
                    if (iRow >= rows || iCol >= columns) break;

                    if (gameBoard[iRow][iCol].getColor() != Color.white && gameBoard[iRow][iCol].getColor() == winnersColor) {
                        streak--;
                        if (streak == 0) {
                            isWinner = true;
                        }
                    } else {
                        streak = 4;
                    }
                }

                //po skosie z dolu do prawej
                for(int iRow = row - 3, iCol = column + 3; iRow <= row + 3 && iCol >= column - 3; iRow++, iCol--) {
                    if(iRow < 0 || iCol >= columns) continue;
                    if(iRow >= rows || iCol < 0) break;

                    if(gameBoard[iRow][iCol].getColor() != Color.white && gameBoard[iRow][iCol].getColor() == winnersColor) {
                        streak--;
                        if(streak == 0) {
                            isWinner = true;
                        }
                    }
                    else {
                        streak = 4;
                    }
                }

                break;
            }
        }
        return isWinner;
    }

    @Override
    public int startGameGui(int col) {
        int addedToRow;
        Color color;
        if(firstPlayerTurn) {
            color = Color.red;
        }
        else {
            color = Color.yellow;
        }
        addedToRow = board.addCoin(col, color);

        if(addedToRow != -1) {
            firstPlayerTurn = !firstPlayerTurn;
        }

        return addedToRow;
    }


    @Override
    public void reset() {
        board.clearBoard();
        firstPlayerTurn = true;
    }
}
