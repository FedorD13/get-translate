package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FedorD on 2020-01-24
 */
@AllArgsConstructor
public enum LanguageEnum {
    EN(Constants.EN),
    RU(Constants.RU);

    @Getter
    private final String language;

    public static class Constants {
        @Getter private static final String EN = "en";
        @Getter private static final String RU = "ru";
    }

    private static List<String> getLanguagesList(){
        List<String> languagesList= new ArrayList<String>();
        languagesList.add(Constants.EN);
        languagesList.add(Constants.RU);
        return languagesList;
    }

    public static boolean isValidLanguage(String language) {
        if(StringUtils.isNotBlank(language)){
            return getLanguagesList().stream().anyMatch(language::equals);
        } else {
            throw new IllegalArgumentException("Language can't be blank. Valida languages are " + getLanguagesList());
        }
    }

}
