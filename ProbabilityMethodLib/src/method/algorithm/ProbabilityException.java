package method.algorithm;

public class ProbabilityException extends Exception {
    public ProbabilityException() {
        super();
    }

    public ProbabilityException(String message) {
        super(message);
    }

    public ProbabilityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProbabilityException(Throwable cause) {
        super(cause);
    }

    protected ProbabilityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
