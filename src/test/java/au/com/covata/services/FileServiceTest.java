package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;
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
 * Test - RoverService - getNextCoordinate
 */
@RunWith(Parameterized.class)
public class FileServiceTest {
    @Parameterized.Parameter
    public String filePath;
    @Parameterized.Parameter(1)
    public List<String> expectedResult;
    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection paramCollection() {
        List<String> lines = new ArrayList<>();
        lines.add("5 5");
        lines.add("1 2 N");
        lines.add("LMLMLMLMM");
        lines.add("3 3 E");
        lines.add("MMRMMRMRRM");
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
        Assert.assertEquals(expectedResult.size(), lines.size());
    }
}