package pl.kulig.State;


import java.awt.*;

public class Empty implements IState {
    private final Color  color = Color.white;
    private final String name = "Empty place";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

}
