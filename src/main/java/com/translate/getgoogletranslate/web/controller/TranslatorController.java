package com.translate.getgoogletranslate.web.controller;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.translate.getgoogletranslate.domain.Word;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fedor on 2020-01-23
 */
@RestController
@RequestMapping("/api/word")
public class TranslatorController {

    @GetMapping("/{word}")
    public ResponseEntity<?> getWord(@PathVariable String word) {

        Translate translate = TranslateOptions.getDefaultInstance().getService();

        Translation translation = translate.translate(
                word,
                Translate.TranslateOption.sourceLanguage("en"),
                Translate.TranslateOption.targetLanguage("ru"));

        Word outputWord = new Word(translation.getTranslatedText());
        return new ResponseEntity<Word>(outputWord, HttpStatus.OK);
    }

}
