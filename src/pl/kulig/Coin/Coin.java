package pl.kulig.Coin;

import pl.kulig.State.IState;

import java.awt.*;

public class Coin implements ICoin {
    private IState state;
    private final int row;
    private final int column;

    public Coin(IState state, int row, int column) {
        this.state = state;
        this.row = row;
        this.column = column;
    }

    @Override
    public Color getColor() {
        return state.getColor();
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public IState getState() {
        return state;
    }

    @Override
    public void setState(IState state) {
        this.state = state;
    }
}
