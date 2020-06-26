package methods;

import java.util.List;

public class ValidationException extends Exception {
    private List<String> errorsDescription;

    ValidationException(String errorMessage, List<String> errorsDescription) {
        super(errorMessage);
        this.errorsDescription = errorsDescription;
    }

    public List<String> getErrorsDescription() {
        return errorsDescription;
    }
}
