package au.com.covata;

import au.com.covata.exceptions.CovataServicesException;
import au.com.covata.model.Coordinate;
import au.com.covata.services.FileService;
import au.com.covata.services.RoverService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Rohitha Wanni Achchige on 14/7/17.
 * MainApp
 */
public class MainApp {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
    public static void main(String[] args) {
        try {
            if (args == null || args.length == 0){
                throw new CovataServicesException("Please provide the file path");
            }
            final FileService fileService = new FileService();
            final List<String> lines = fileService.getLines(args[0]);
            if (lines == null || lines.size() < 3 || lines.size() % 2 == 0){
                throw new CovataServicesException("Invalid file format");
            }

            final String[] edgeCoordinates = lines.get(0).split(" ");
            if (edgeCoordinates.length != 2){
                throw new CovataServicesException("Invalid edge coordinates");
            }
            final RoverService roverService = new RoverService(Integer.parseInt(edgeCoordinates[0]),
                    Integer.parseInt(edgeCoordinates[1]));
            lines.remove(0);
            List<Coordinate> endCoordinates = roverService.calculateEndCoordinates(lines);
            endCoordinates.forEach(endCoordinate -> {
                logger.info("Output:------------------");
                logger.info(endCoordinate.getX() + " " + endCoordinate.getY() + " " + endCoordinate.getDirection().toString());
                logger.info("-------------------------");
            });

        } catch (NumberFormatException e) {
            logger.error("Invalid edge coordinates");
        } catch (CovataServicesException e) {
            logger.error(e.getErrorMessage());
        }
    }
}
