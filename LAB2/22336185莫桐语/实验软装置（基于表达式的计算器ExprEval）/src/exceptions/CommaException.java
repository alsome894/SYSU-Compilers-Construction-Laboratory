package exceptions;

public class CommaException extends ExpressionException {
    public CommaException() {
        this("Bad comma in expression");
    }

    public CommaException(String message) {
        super(message);
    }
}
