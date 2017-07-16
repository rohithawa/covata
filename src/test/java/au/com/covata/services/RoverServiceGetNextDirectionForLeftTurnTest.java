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
 * Test - RoverService - getNextCoordinate
 */
@RunWith(Parameterized.class)
public class RoverServiceGetNextDirectionForLeftTurnTest {
    @Parameterized.Parameter
    public Direction currentDirection;
    @Parameterized.Parameter(1)
    public Direction expectedResult;

    @Parameterized.Parameters
    public static Collection paramCollection() {
        return Arrays.asList(new Object[][]{
                {Direction.N, Direction.W},
                {Direction.W, Direction.S},
                {Direction.S, Direction.E},
                {Direction.E, Direction.N}
        });
    }

    private static RoverService roverService;

    @BeforeClass
    public static void init() {
        roverService = new RoverService(5, 5);
    }

    @Test
    public void testGetNextDirectionForLeftTurn() {
        Assert.assertEquals(expectedResult, roverService.getNextDirectionForLeftTurn(currentDirection));
    }
}