package au.com.covata.model;

import au.com.covata.util.Direction;

/**
 * Created by Rohitha Wanni Achchige on 13/7/17.
 * Coordinate information
 */
public class Coordinate {
    private final int x;
    private final int y;
    private final Direction direction;

    public Coordinate(final int x, final int y, final Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
