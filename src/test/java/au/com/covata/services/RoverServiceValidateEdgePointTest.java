package au.com.covata.services;

import au.com.covata.util.Direction;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * Test - RoverService - validateEdgePoint
 */
@RunWith(Parameterized.class)
public class RoverServiceValidateEdgePointTest {
    @Parameterized.Parameter
    public int x;
    @Parameterized.Parameter(1)
    public int y;
    @Parameterized.Parameter(2)
    public Direction direction;

    @Parameterized.Parameter(3)
    public boolean expectedResult;

    @Parameterized.Parameters
    public static Collection paramCollection() {
        return Arrays.asList(new Object[][]{
                {0, 5, Direction.N, false},
                {0, 5, Direction.W, false},
                {5, 5, Direction.N, false},
                {5, 5, Direction.E, false},
                {0, 0, Direction.S, false},
                {0, 0, Direction.W, false},
                {5, 0, Direction.S, false},
                {5, 0, Direction.E, false},
                {0, 5, Direction.S, true},
                {0, 5, Direction.E, true},
                {5, 5, Direction.S, true},
                {5, 5, Direction.W, true},
                {0, 0, Direction.N, true},
                {0, 0, Direction.E, true},
                {5, 0, Direction.N, true},
                {5, 0, Direction.W, true}
        });
    }

    private static RoverService roverService;

    @BeforeClass
    public static void init() {
        roverService = new RoverService(5, 5);
    }

    @Test
    public void testValidateEdgePoint() {
        Assert.assertEquals(expectedResult, roverService.validateEdgePoint(x, y, direction));
    }
}