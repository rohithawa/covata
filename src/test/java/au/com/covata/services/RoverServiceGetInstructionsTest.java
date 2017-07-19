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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * Test - RoverService - getInstructions
 */
@RunWith(Parameterized.class)
public class RoverServiceGetInstructionsTest {
    @Parameterized.Parameter
    public String line;
    @Parameterized.Parameter(1)
    public List<Instruction> expectedResult;
    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
                {"LMLMLMLMM", instructions1, null},
                {"MMRMMRMRRM", instructions2, null},
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
        List<Instruction> instructions = roverService.getInstructions(line);
        Assert.assertEquals(expectedResult.size(), instructions.size());
    }
}