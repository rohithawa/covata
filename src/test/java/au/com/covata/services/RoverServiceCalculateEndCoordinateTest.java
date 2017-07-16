package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;
import au.com.covata.model.Coordinate;
import au.com.covata.util.Direction;
import au.com.covata.util.Instruction;
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
public class RoverServiceCalculateEndCoordinateTest {
    @Parameterized.Parameter
    public Coordinate startPoint;
    @Parameterized.Parameter(1)
    public Instruction[] instructions;
    @Parameterized.Parameter(2)
    public Coordinate expectedResult;

    @Parameterized.Parameters
    public static Collection paramCollection() {
        return Arrays.asList(new Object[][]{
                {new Coordinate(1, 2, Direction.N), new Instruction[]{Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.M}, new Coordinate(1, 3, Direction.N)},
                {new Coordinate(3, 3, Direction.E), new Instruction[]{Instruction.M, Instruction.M, Instruction.R, Instruction.M, Instruction.M, Instruction.R, Instruction.M, Instruction.R, Instruction.R, Instruction.M}, new Coordinate(5, 1, Direction.E)}
        });
    }

    private static RoverService roverService;

    @BeforeClass
    public static void init() {
        roverService = new RoverService(5, 5);
    }

    @Test
    public void testCalculateEndCoordinate() throws CovataServicesException {
        Coordinate coordinate = roverService.calculateEndCoordinate(startPoint, instructions);
        Assert.assertEquals(expectedResult.getY(), coordinate.getY());
        Assert.assertEquals(expectedResult.getX(), coordinate.getX());
        Assert.assertEquals(expectedResult.getDirection(), coordinate.getDirection());
    }
}