package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * FileService service implementation for file reader
 */
public class FileService {
    /**
     * get lines by reading file.
     *
     * @return String[]
     */
    public Object[] getLines(final String fileName) throws CovataServicesException {
        try {
            final Stream<String> stream = Files.lines(Paths.get(fileName));
            return stream.toArray();
        } catch (IOException e) {
            throw new CovataServicesException("Invalid file path");
        }
    }
}
