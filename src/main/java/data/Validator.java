package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class Validator {
    private static final Logger logger = LoggerFactory.getLogger(Validator.class);

    private List<String> errorMessages = new ArrayList<>();

    boolean isEachVariantsCorrect(String[] variants) {
        if (isNull(variants) || (isEmpty(variants))){
            return false;
        } else
        if (isThereNull(variants) || isThereEmpty(variants)) {
            return false;
        }
        return true;
    }

    private boolean isNull(String[] variants) {
        if(variants==null){
            logger.error("Variants array is null");
            errorMessages.add("Variants array can't be null");
            return true;
        }
        return false;
    }

    private boolean isEmpty(String[] variants) {
        if(variants.length<1){
            logger.error("Variants array is empty");
            errorMessages.add("Variants array can't be empty");
            return true;
        }
        return false;
    }

    private boolean isThereNull(String[] variants) {
        for (String variant : variants) {
            if (variant == null){
                logger.error("Variant is null");
                errorMessages.add("Variant can't be null");
                return true;
            }
        }
        return false;
    }

    private boolean isThereEmpty(String[] variants) {
        for (String variant : variants) {
            if (variant.length() < 1){
                logger.error("Variant is empty");
                errorMessages.add("Variant can't be empty");
                return true;
            }
        }
        return false;
    }


    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
