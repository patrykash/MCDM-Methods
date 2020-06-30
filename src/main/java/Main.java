import data.*;
import methods.*;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ValidationException {
        String methodType = args[0];
        DecisionProblemSource decisionProblemSource = new JsonFileSource();
        DecisionProblem decisionProblem = decisionProblemSource.loadDecisionProblem("src\\main\\resources\\dataTes.json");
        McdmMethod mcdmMethod = null;
        switch (methodType) {
            case "1":
                mcdmMethod = TopsisMethod.createWithMaxMinNormalization(decisionProblem);
                break;
            case "2":
                mcdmMethod = TopsisMethod.createWithVetorNormalization(decisionProblem);
                break;
            case "3":
                mcdmMethod = SawMethod.createWithMaxMinNormalization(decisionProblem);
                break;
            case "4":
                mcdmMethod = SawMethod.createWithVectorNormalization(decisionProblem);
                break;
             default:
                 System.out.println("Invalid method number");
                 System.exit(0);
        }

        List<ResultRank> resultRanks = mcdmMethod.calculateResult();
        resultRanks.forEach((x) -> System.out.println(x.getVariantName() + " :" + x.getRating()));
    }
}
