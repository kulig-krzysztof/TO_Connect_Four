package pl.kulig.Coin;

import pl.kulig.State.IState;

import java.awt.*;

public interface ICoin {
    Color getColor();

    int getRow();

    int getColumn();

    IState getState();

    void setState(IState state);

}
