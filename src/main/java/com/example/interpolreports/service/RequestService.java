package com.example.interpolreports.service;

import com.example.interpolreports.model.Notice;
import com.example.interpolreports.model.Person;

import java.util.List;

public interface RequestService {
    List<Person> doRequest(Notice notice);
}
