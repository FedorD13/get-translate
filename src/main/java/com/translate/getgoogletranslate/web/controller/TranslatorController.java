package com.translate.getgoogletranslate.web.controller;

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
        Word wordOutput = new Word(word);
        return new ResponseEntity<Word>(wordOutput, HttpStatus.OK);
    }

}
