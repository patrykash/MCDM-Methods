package data;

import methods.DecisionProblem;

public interface DecisionProblemSource {

    DecisionProblem loadDecisionProblem(String filePath);

}
