package au.com.covata.services;

import au.com.covata.exceptions.CovataServicesException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
    public List<String> getLines(final String fileName) throws CovataServicesException {
        try {
            final Stream<String> stream = Files.lines(Paths.get(fileName));
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new CovataServicesException("Invalid file path");
        }
    }
}
