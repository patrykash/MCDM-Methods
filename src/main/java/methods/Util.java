package methods;


import java.util.ArrayList;
import java.util.List;

class Util {


    static List<ResultRank> assignRatingsToVariants(double[] rating, String[] variants) {
        List<ResultRank> resultRanks = new ArrayList<>();
        for (int i = 0; i < rating.length; i++) {
            ResultRank resultRank = new ResultRank(variants[i], rating[i]);
            resultRanks.add(resultRank);
        }
        return resultRanks;
    }


}
