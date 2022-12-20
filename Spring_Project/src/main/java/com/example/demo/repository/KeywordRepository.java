package com.example.demo.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.example.demo.model.Keyword;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

public class KeywordRepository {

    @Getter
    ArrayList<Keyword> keywordList;

    public KeywordRepository() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File keywordFile = new File("Spring_Project/src/main/resources/keywords.json");
            keywordList = objectMapper.readValue(keywordFile, new TypeReference<ArrayList<Keyword>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
