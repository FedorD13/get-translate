package com.translate.getgoogletranslate.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fedor on 2020-01-24
 */
public class CsvHelper {
    private final static char delimiter = ',';
    private final static CSVFormat format = CSVFormat.DEFAULT
            .withHeader()
            .withDelimiter(delimiter);

    public static CSVParser getCsvParser(String fileName, MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return new CSVParser(reader, format);
    }

    private static List<String> getHeader(CSVParser csvParser) {
        return csvParser.getHeaderNames();
    }

    public static String getSourceLanguage(CSVParser csvParser) {
        var header = getHeader(csvParser);
        return header.stream().findFirst().get().toLowerCase();
    }

    public static List<String> getWords(CSVParser csvParser, String sourceLanguage) throws IOException {
        return csvParser.getRecords().stream().map(record -> record.get(sourceLanguage))
                .collect(Collectors.toList());
    }
}
