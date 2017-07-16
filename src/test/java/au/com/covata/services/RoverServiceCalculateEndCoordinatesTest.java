package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;
import au.com.covata.model.Coordinate;
import au.com.covata.util.Direction;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * Test - RoverService - getNextCoordinate
 */
@RunWith(Parameterized.class)
public class RoverServiceCalculateEndCoordinatesTest {
    @Parameterized.Parameter
    public Object[] lines;
    @Parameterized.Parameter(1)
    public List<Coordinate> expectedResult;

    @Parameterized.Parameters
    public static Collection paramCollection() {
        Object[] lines = new Object[5];
        lines[0] = "5 5";
        lines[1] = "1 2 N";
        lines[2] = "LMLMLMLMM";
        lines[3] = "3 3 E";
        lines[4] = "MMRMMRMRRM";
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(1, 3, Direction.N));
        coordinates.add(new Coordinate(5, 1, Direction.E));
        return Arrays.asList(new Object[][]{
                {lines, coordinates}
        });
    }

    private static RoverService roverService;

    @BeforeClass
    public static void init() {
        roverService = new RoverService(5, 5);
    }

    @Test
    public void testCalculateEndCoordinates() throws CovataServicesException {
        List<Coordinate> coordinates = roverService.calculateEndCoordinates(lines);
        Assert.assertEquals(expectedResult.size(), coordinates.size());
        Assert.assertEquals(expectedResult.get(0).getY(), coordinates.get(0).getY());
        Assert.assertEquals(expectedResult.get(0).getX(), coordinates.get(0).getX());
        Assert.assertEquals(expectedResult.get(0).getDirection(), coordinates.get(0).getDirection());
    }
}