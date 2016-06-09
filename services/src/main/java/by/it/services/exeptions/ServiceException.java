package by.it.services.exeptions;


public class ServiceException extends Exception {
    private Exception exception;

    public ServiceException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
