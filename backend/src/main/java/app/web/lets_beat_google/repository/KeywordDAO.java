package app.web.lets_beat_google.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

import app.web.lets_beat_google.model.Keyword;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Repository
public class KeywordDAO {

  @Getter
  ArrayList<Keyword> keywordDB;

  public KeywordDAO() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      File keywordFile = new File("backend/src/main/resources/public/keywords.json");
      this.keywordDB = objectMapper.readValue(keywordFile, new TypeReference<ArrayList<Keyword>>() {
      });
    } catch (IOException e) {
      System.out.println("KeywordDAO error at: " + e.getMessage());
    }
  }
}
