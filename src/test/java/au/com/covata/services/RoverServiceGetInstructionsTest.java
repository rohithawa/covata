package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;
import au.com.covata.util.Instruction;
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
 * Test - RoverService - getInstructions
 */
@RunWith(Parameterized.class)
public class RoverServiceGetInstructionsTest {
    @Parameterized.Parameter
    public Object line;
    @Parameterized.Parameter(1)
    public Instruction[] expectedResult;
    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection paramCollection() {
        return Arrays.asList(new Object[][]{
                {"LMLMLMLMM", new Instruction[]{Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.M}, null},
                {"MMRMMRMRRM", new Instruction[]{Instruction.M, Instruction.M, Instruction.R, Instruction.M, Instruction.M, Instruction.R, Instruction.M, Instruction.R, Instruction.R, Instruction.M}, null},
                {"MMRMMRMRRP", null, CovataServicesException.class}
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
        Instruction[] instructions = roverService.getInstructions(line);
        Assert.assertEquals(expectedResult.length, instructions.length);
    }
}