package Models;

import java.io.Serializable;

public enum TurnResult implements Serializable {
    MISSED,
    WOUNDED,
    KILLED,
    KILLED_AND_WON,
}
