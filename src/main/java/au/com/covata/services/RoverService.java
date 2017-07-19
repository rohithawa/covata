package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;
import au.com.covata.model.Coordinate;
import au.com.covata.util.Direction;
import au.com.covata.util.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * RoverService service implementation of Rover
 */
public class RoverService {
    private int edgeX;
    private int edgeY;

    public RoverService(int edgeX, int edgeY) {
        this.edgeX = edgeX;
        this.edgeY = edgeY;
    }

    /**
     * calculate end points by given list of start points and instructions.
     *
     * @param lines - lines
     * @return List<Coordinate>
     */
    public List<Coordinate> calculateEndCoordinates(final List<String> lines) throws CovataServicesException {
        List<String> direction = lines.stream().filter(line -> line.contains(" ")).collect(Collectors.toList());
        List<String> instruction = lines.stream().filter(line -> !line.contains(" ")).collect(Collectors.toList());
        final List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < direction.size(); i++) {
            final Coordinate coordinate = getCoordinate(direction.get(i));
            final List<Instruction> instructions = getInstructions(instruction.get(i));
            coordinates.add(calculateEndCoordinate(coordinate, instructions));
        }
        return coordinates;
    }

    /**
     * get Instructions by line.
     *
     * @param line   - instruction string
     * @return Instruction[]
     */
    protected List<Instruction> getInstructions(String line) throws CovataServicesException {
        char[] chars = line.toCharArray();
        final List<Instruction> instructions = new ArrayList<>();
        try {
            for (char aChar : chars) {
                instructions.add(Instruction.valueOf(String.valueOf(aChar)));
            }
        } catch (IllegalArgumentException e) {
            throw new CovataServicesException("Invalid instruction provided");
        }
        return instructions;
    }

    /**
     * get Coordinate by line.
     *
     * @param line   - instruction string
     * @return Coordinate
     */
    protected Coordinate getCoordinate(String line) throws CovataServicesException {
        try {
            final String[] coordinateStrings = line.split(" ");
            return new Coordinate(Integer.parseInt(coordinateStrings[0]),
                    Integer.parseInt(coordinateStrings[1]), Direction.valueOf(coordinateStrings[2]));
        } catch (IllegalArgumentException e) {
            throw new CovataServicesException("Invalid coordinate provided");
        }
    }

    /**
     * calculate end point by given start point and instructions.
     *
     * @param startPoint   - startPoint
     * @param instructions - instructions
     * @return Coordinate
     */
    protected Coordinate calculateEndCoordinate(final Coordinate startPoint, final List<Instruction> instructions) throws CovataServicesException {
        int x = startPoint.getX();
        int y = startPoint.getY();
        Direction direction = startPoint.getDirection();
        Coordinate endPoint = startPoint;

        for (Instruction instruction : instructions) {
            switch (instruction) {
                case L:
                    direction = getNextDirectionForLeftTurn(direction);
                    endPoint = new Coordinate(x, y, direction);
                    break;
                case R:
                    direction = getNextDirectionForRightTurn(direction);
                    endPoint = new Coordinate(x, y, direction);
                    break;
                default:
                    endPoint = getNextCoordinate(x, y, direction);
                    x = endPoint.getX();
                    y = endPoint.getY();
                    break;
            }
        }
        return endPoint;
    }

    /**
     * calculate next direction for left turn.
     *
     * @param currentDirection - direction
     * @return Coordinate
     */
    protected Direction getNextDirectionForLeftTurn(final Direction currentDirection) {
        final Direction nextDirection;
        switch (currentDirection) {
            case N:
                nextDirection = Direction.W;
                break;
            case E:
                nextDirection = Direction.N;
                break;
            case S:
                nextDirection = Direction.E;
                break;
            default:
                nextDirection = Direction.S;
                break;
        }
        return nextDirection;
    }

    /**
     * calculate next direction for right turn.
     *
     * @param currentDirection - direction
     * @return Coordinate
     */
    protected Direction getNextDirectionForRightTurn(final Direction currentDirection) {
        final Direction nextDirection;
        switch (currentDirection) {
            case N:
                nextDirection = Direction.E;
                break;
            case E:
                nextDirection = Direction.S;
                break;
            case S:
                nextDirection = Direction.W;
                break;
            default:
                nextDirection = Direction.N;
                break;
        }
        return nextDirection;
    }

    /**
     * calculate next coordinate.
     *
     * @param x         - x coordination
     * @param y         - y coordination
     * @param direction - direction
     * @return Coordinate
     */
    protected Coordinate getNextCoordinate(int x, int y, final Direction direction) throws CovataServicesException {
        if (!validateEdgePoint(x, y, direction)) {
            throw new CovataServicesException("Outside plateau area");
        }
        switch (direction) {
            case N:
                y++;
                break;
            case S:
                y--;
                break;
            case E:
                x++;
                break;
            default:
                x--;
                break;
        }
        return new Coordinate(x, y, direction);
    }

    /**
     * Validate edge point.
     *
     * @param x         - x coordination
     * @param y         - y coordination
     * @param direction - direction
     * @return boolean
     */
    protected boolean validateEdgePoint(final int x, final int y, final Direction direction) {
        if (direction.equals(Direction.N) && y >= edgeY) {
            return false;
        } else if (direction.equals(Direction.S) && y <= 0) {
            return false;
        } else if (direction.equals(Direction.E) && x >= edgeX) {
            return false;
        } else if (direction.equals(Direction.W) && x <= 0) {
            return false;
        }
        return true;
    }
}
