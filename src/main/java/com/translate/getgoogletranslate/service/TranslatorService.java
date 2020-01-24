package com.translate.getgoogletranslate.service;

import com.google.api.gax.rpc.InvalidArgumentException;
import com.translate.getgoogletranslate.domain.TranslationSet;
import com.translate.getgoogletranslate.helper.CsvHelper;
import enums.LanguageEnum;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Fedor on 2020-01-24
 */
@Service
public class TranslatorService {

    public TranslationSet getTranslationSet(String fileName, MultipartFile file) throws IOException {
        String sourceLanguage;
        String targetLanguage;
        List<String> wordsList;

        var csvParser = CsvHelper.getCsvParser(fileName, file);

        sourceLanguage = CsvHelper.getSourceLanguage(csvParser);

        if (LanguageEnum.isValidLanguage(sourceLanguage)) {
            targetLanguage = getTargetLanguage(sourceLanguage);
        } else {
            throw new IllegalArgumentException("Source language is invalid.");
        }

        wordsList = CsvHelper.getWords(csvParser, sourceLanguage);

        return TranslationSet.builder()
                .sourceLanguage(sourceLanguage)
                .targetLanguage(targetLanguage)
                .wordsList(wordsList)
                .build();
    }

    private String getTargetLanguage(String sourceLanguage) {
        return sourceLanguage.equals(LanguageEnum.EN.getLanguage()) ? LanguageEnum.RU.getLanguage() : LanguageEnum.EN.getLanguage();
    }
}
