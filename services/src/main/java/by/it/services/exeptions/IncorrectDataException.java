package by.it.services.exeptions;


public class IncorrectDataException  extends ServiceException{
    public IncorrectDataException(Exception exception) {
        super(exception);
    }
}
