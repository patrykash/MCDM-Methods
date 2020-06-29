package methods;

import java.util.List;

public interface McdmMethod {

    public List<ResultRank> calculateResult() throws ValidationException;
}
