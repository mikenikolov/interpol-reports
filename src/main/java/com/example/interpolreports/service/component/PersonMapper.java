package com.example.interpolreports.service.component;

import com.example.interpolreports.model.Person;

import java.util.List;

public interface PersonMapper {
    List<Person> map(String json);
}
