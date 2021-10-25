package Models;

import java.io.Serializable;

public class Turn implements Serializable {
    public int x;
    public int y;

    public Turn(int x_, int y_) {
        x = x_;
        y = y_;
    }
}
