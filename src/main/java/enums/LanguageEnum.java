package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by FedorD on 2020-01-24
 */
@AllArgsConstructor
public enum LanguageEnum {
    EN(Constants.EN),
    RU(Constants.RU);

    @Getter
    private final String language;

    private static class Constants {
        private static final String EN = "en";
        private static final String RU = "ru";
    }
}
