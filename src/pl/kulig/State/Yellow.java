package pl.kulig.State;


import java.awt.*;

public class Yellow implements IState {
    private final Color  color = Color.yellow;
    private final String name = "Player Yellow";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
