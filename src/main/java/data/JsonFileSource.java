package data;

import com.google.gson.Gson;
import methods.DecisionProblem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileSource implements DecisionProblemSource {

    @Override
    public DecisionProblem loadDecisionProblem(String filePath) {
        String text = null;
        try {
            text = new  String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {

            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(text, DecisionProblem.class);
    }
}
