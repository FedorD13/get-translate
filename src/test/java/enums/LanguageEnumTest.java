package enums;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LanguageEnumTest {

    String en = LanguageEnum.Constants.getEN();
    String ru = LanguageEnum.Constants.getRU();
    List<String> invalidLanguages = List.of(
            "jp",
            "vn",
            "ab",
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(2),
            RandomStringUtils.randomAlphanumeric(5),
            RandomStringUtils.randomAlphanumeric(5)
    );
    List<String> exceptionsLanguages = List.of(
            " "
    );


    @DisplayName("Are valid languages are okay")
    @Test
    void givenIsValidLanguage_whenUseValidLanguages_thenCorrect() {
        assertTrue(LanguageEnum.isValidLanguage(en));
        assertTrue(LanguageEnum.isValidLanguage(ru));
    }

    @DisplayName("Are invalid languages are okay")
    @Test
    void givenIsValidLanguageMethod_whenUseInvalidLanguages_thenFalse() {
        invalidLanguages.stream().forEach(language -> assertFalse(LanguageEnum.isValidLanguage(language)));
    }

    @DisplayName("Exception Testing")
    @Test
    void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
        {
            LanguageEnum.isValidLanguage(exceptionsLanguages.get(0));
        });
    }

}