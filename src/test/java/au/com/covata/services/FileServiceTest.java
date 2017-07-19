package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * Test - RoverService - getNextCoordinate
 */
@RunWith(Parameterized.class)
public class FileServiceTest {
    @Parameterized.Parameter
    public String filePath;
    @Parameterized.Parameter(1)
    public String[] expectedResult;
    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection paramCollection() {
        String[] lines = new String[5];
        lines[0] = "5 5";
        lines[1] = "1 2 N";
        lines[2] = "LMLMLMLMM";
        lines[3] = "3 3 E";
        lines[4] = "MMRMMRMRRM";
        return Arrays.asList(new Object[][]{
                {"instruction1.txt", null, CovataServicesException.class},
                {"instruction.txt", lines, null}
        });
    }

    private static FileService fileService;

    @BeforeClass
    public static void init() {
        fileService = new FileService();
    }

    @Test
    public void testGetLines() throws CovataServicesException {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        List<String> lines = fileService.getLines(filePath);
        Assert.assertEquals(expectedResult.length, lines.size());
    }
}