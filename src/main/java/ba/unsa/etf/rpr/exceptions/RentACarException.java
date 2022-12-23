package ba.unsa.etf.rpr.exceptions;

public class RentACarException extends Exception{
    public RentACarException(String message) {
        super(message);
    }

    public RentACarException(String message, Exception cause) {
        super(message, cause);
    }
}
