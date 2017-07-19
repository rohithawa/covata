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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * Test - RoverService - getNextCoordinate
 */
@RunWith(Parameterized.class)
public class RoverServiceCalculateEndCoordinateTest {
    @Parameterized.Parameter
    public Coordinate startPoint;
    @Parameterized.Parameter(1)
    public List<Instruction> instructions;
    @Parameterized.Parameter(2)
    public Coordinate expectedResult;

    @Parameterized.Parameters
    public static Collection paramCollection() {
        List<Instruction> instructions1 = new ArrayList<>();
        instructions1.add(Instruction.L);
        instructions1.add(Instruction.M);
        instructions1.add(Instruction.L);
        instructions1.add(Instruction.M);
        instructions1.add(Instruction.L);
        instructions1.add(Instruction.M);
        instructions1.add(Instruction.L);
        instructions1.add(Instruction.M);
        instructions1.add(Instruction.M);
        List<Instruction> instructions2 = new ArrayList<>();
        instructions2.add(Instruction.M);
        instructions2.add(Instruction.M);
        instructions2.add(Instruction.R);
        instructions2.add(Instruction.M);
        instructions2.add(Instruction.M);
        instructions2.add(Instruction.R);
        instructions2.add(Instruction.M);
        instructions2.add(Instruction.R);
        instructions2.add(Instruction.R);
        instructions2.add(Instruction.M);
        return Arrays.asList(new Object[][]{
                {new Coordinate(1, 2, Direction.N), instructions1, new Coordinate(1, 3, Direction.N)},
                {new Coordinate(3, 3, Direction.E), instructions2, new Coordinate(5, 1, Direction.E)}
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