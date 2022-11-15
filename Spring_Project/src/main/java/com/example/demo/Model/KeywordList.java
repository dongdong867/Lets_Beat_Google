package com.example.demo.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KeywordList {

    ArrayList<Keyword> keywordList;

    public ArrayList<Keyword> getKeywordList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File keywordFile = new File("Spring_Project/src/main/resources/keywords.json");
        keywordList = objectMapper.readValue(keywordFile, new TypeReference<ArrayList<Keyword>>() {
        });
        return keywordList;
    }
}
