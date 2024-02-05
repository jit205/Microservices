package UserService.demo.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException()
    {
        super("Redouce not found on server side");
    }
    public ResourceNotFoundException(String message)
    {
        super(message);
    }

}
