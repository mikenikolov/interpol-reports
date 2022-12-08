package com.example.interpolreports.service.component.impl;

import com.example.interpolreports.model.Person;
import com.example.interpolreports.service.component.PersonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonMapperImpl implements PersonMapper {
    private ObjectMapper mapper;

    @Override
    public List<Person> map(String json) {
        List<Person> list = new ArrayList<>();
        try {
            JsonNode jsonNode = mapper.readTree(json);
            JsonNode embedded = jsonNode.get("_embedded");
            JsonNode notices = embedded.get("notices");
            for (JsonNode jn : notices) {
                String img;
                JsonNode thumbnailNode = jn.get("_links").get("thumbnail");
                if (thumbnailNode == null) img = "https://upload.wikimedia.org/wikipedia/commons/e/ee/Unknown-person.gif";
                else img = thumbnailNode.get("href").asText();
                list.add(new Person()
                        .setFirstName(jn.get("name").asText())
                        .setSecondName(jn.get("forename").asText())
                        .setEntityId(jn.get("entity_id").asText())
                        .setBirthDate(jn.get("date_of_birth").asText())
                        .setImage(img));
            }
            return list;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't read json", e);
        }
    }
}
