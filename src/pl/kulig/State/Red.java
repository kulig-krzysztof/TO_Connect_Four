package pl.kulig.State;


import java.awt.*;

public class Red implements IState {
    private final Color  color = Color.red;
    private final String name = "Player Red";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

}
