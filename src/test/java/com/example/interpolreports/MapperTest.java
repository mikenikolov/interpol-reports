package com.example.interpolreports;

import com.example.interpolreports.model.Person;
import com.example.interpolreports.service.component.PersonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private PersonMapper personMapper;

    @Test
    void parseJsonWithPersons_OK() {
        List<Person> people = personMapper.map("""
                {
                    "total": 7103,
                    "query": {
                        "page": 1,
                        "resultPerPage": 20
                    },
                    "_embedded": {
                        "notices": [
                        {
                            "forename": "Fore Name",
                            "date_of_birth": "1111/11/11",
                            "entity_id": "1/11",
                            "nationalities": [
                                "WW"
                            ],
                            "name": "Person",
                            "_links": {
                                "self": {
                                    "href": "link6"
                                },
                                "images": {
                                    "href": "link7"
                                },
                                "thumbnail": {
                                    "href": "link"
                                }
                            }
                        },
                        {
                            "forename": "Second Fore Name",
                            "date_of_birth": "2222/22/22",
                            "entity_id": "2/22",
                            "nationalities": [
                                "SS"
                            ],
                            "name": "Person2",
                            "_links": {
                                "self": {
                                    "href": "link23"
                                },
                                "images": {
                                    "href": "link21"
                                },
                                "thumbnail": {
                                    "href": "link2"
                                }
                            }
                        }
                    ]
                    }
                }
                """);
        Person first = new Person()
                .setFirstName("Person")
                .setSecondName("Fore Name")
                .setEntityId("1/11")
                .setBirthDate("1111/11/11")
                .setImage("link");
        Person second = new Person()
                .setFirstName("Person2")
                .setSecondName("Second Fore Name")
                .setEntityId("2/22")
                .setBirthDate("2222/22/22")
                .setImage("link2");
        Assertions.assertEquals(2, people.size());
        Assertions.assertEquals(first, people.get(0));
        Assertions.assertEquals(second, people.get(1));
    }

    @Test
    void parseJsonWithError_NotOK() {
        String json = """
                {object = someObject}
                """;
        Assertions.assertThrows(RuntimeException.class, () -> personMapper.map(json));
    }
}
