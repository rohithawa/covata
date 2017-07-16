package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;
import au.com.covata.model.Coordinate;
import au.com.covata.util.Direction;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * Test - RoverService - getNextCoordinate
 */
@RunWith(Parameterized.class)
public class RoverServiceGetNextCoordinateTest {
    @Parameterized.Parameter
    public int x;
    @Parameterized.Parameter(1)
    public int y;
    @Parameterized.Parameter(2)
    public Direction direction;

    @Parameterized.Parameter(3)
    public Coordinate expectedResult;

    @Parameterized.Parameter(4)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection paramCollection() {
        return Arrays.asList(new Object[][]{
                {1, 2, Direction.N, new Coordinate(1, 3, Direction.N), null},
                {1, 2, Direction.W, new Coordinate(0, 2, Direction.W), null},
                {1, 2, Direction.E, new Coordinate(2, 2, Direction.E), null},
                {1, 2, Direction.S, new Coordinate(1, 1, Direction.S), null},
                {0, 5, Direction.N, null, CovataServicesException.class}
        });
    }

    private static RoverService roverService;

    @BeforeClass
    public static void init() {
        roverService = new RoverService(5, 5);
    }

    @Test
    public void testGetNextCoordinate() throws CovataServicesException {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        Coordinate coordinate = roverService.getNextCoordinate(x, y, direction);
        Assert.assertEquals(expectedResult.getDirection(), coordinate.getDirection());
        Assert.assertEquals(expectedResult.getX(), coordinate.getX());
        Assert.assertEquals(expectedResult.getY(), coordinate.getY());
    }
}