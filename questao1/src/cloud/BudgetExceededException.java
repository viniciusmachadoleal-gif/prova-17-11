package cloud;

public class BudgetExceededException extends RuntimeException {
    public BudgetExceededException(String message) { super(message); }
}
