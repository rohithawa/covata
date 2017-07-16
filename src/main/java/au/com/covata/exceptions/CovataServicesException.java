package au.com.covata.exceptions;

/**
 * Created by Rohitha Wanni Achchige on 13/7/17.
 * Exception handling
 */
public class CovataServicesException extends Exception {
    private final String errorMessage;

    /**
     * CovataServicesException constructor.
     *
     * @param errorMessage errorMessage
     */
    public CovataServicesException(final String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
