package com.translate.getgoogletranslate.web.controller;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.translate.getgoogletranslate.domain.TranslationSet;
import com.translate.getgoogletranslate.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fedor on 2020-01-24
 */
@Controller
public class TranslatorControllerV2 {

    @Autowired
    TranslatorService translatorService;

    @RequestMapping(value = "/uploadV2", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> getTranslation(@RequestParam("fileName") String fileName,
                                       @RequestParam("file") MultipartFile file) {
        List<String> translationsList = new ArrayList<>();

        if (!file.isEmpty()) {
            try {
                TranslationSet translationSet = translatorService.getTranslationSet(fileName, file);

                Translate translate = TranslateOptions.getDefaultInstance().getService();
                Translate.TranslateOption translateOption = Translate.TranslateOption.sourceLanguage(translationSet.getSourceLanguage());

                translationsList = translationSet.getWordsList().stream()
                        .map(word -> translate.translate(word, Translate.TranslateOption.sourceLanguage(translationSet.getSourceLanguage()),
                                Translate.TranslateOption.targetLanguage(translationSet.getTargetLanguage())).getTranslatedText())
                        .collect(Collectors.toList());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<List<String>>(translationsList, HttpStatus.OK);
    }
}