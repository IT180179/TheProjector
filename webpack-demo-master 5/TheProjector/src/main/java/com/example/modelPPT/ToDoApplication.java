package com.example.modelPPT;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

public class ToDoApplication {

    String inputJson = "http://localhost:8080/projekte/all";
    ObjectMapper objectMapper = new ObjectMapper();
    Project project;


    public Project get() {
        {
            try {
                URL inputJsonURL = new URL(inputJson);
                Project project = objectMapper.readValue(inputJsonURL, Project.class);// convert to object of class OutputPOJO
            } catch (Exception e) {
                System.out.println(String.format("Could not convert String %s to class %s due to exception %s", inputJson, project, e));
            }
        }

        return project;

    }

}
