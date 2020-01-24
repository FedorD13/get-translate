package com.translate.getgoogletranslate.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by Fedor on 2020-01-24
 */
@Builder
@Data
public class TranslationSet {
    private String sourceLanguage;
    private String targetLanguage;
    private List<String> wordsList;
}
