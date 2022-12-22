package com.example.demo.model;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Website {

  private final String URL;

  private int score;
  private String content, title;

  @Builder.Default
  private ArrayList<Website> subpages = new ArrayList<>();

  public void addSubpage(Website website) {
    subpages.add(website);
  }

}
