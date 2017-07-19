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
 * Test - RoverService - getCoordinate
 */
@RunWith(Parameterized.class)
public class RoverServiceGetCoordinateTest {
    @Parameterized.Parameter
    public String line;
    @Parameterized.Parameter(1)
    public Coordinate expectedResult;
    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection paramCollection() {
        return Arrays.asList(new Object[][]{
                {"1 2 N", new Coordinate(1, 2, Direction.N), null},
                {"3 3 E", new Coordinate(3, 3, Direction.E), null},
                {"3 3 P", null, CovataServicesException.class}
        });
    }

    private static RoverService roverService;

    @BeforeClass
    public static void init() {
        roverService = new RoverService(5, 5);
    }

    @Test
    public void testCalculateEndCoordinates() throws CovataServicesException {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        Coordinate coordinate = roverService.getCoordinate(line);
        Assert.assertEquals(expectedResult.getX(), coordinate.getX());
        Assert.assertEquals(expectedResult.getY(), coordinate.getY());
        Assert.assertEquals(expectedResult.getDirection(), coordinate.getDirection());
    }
}