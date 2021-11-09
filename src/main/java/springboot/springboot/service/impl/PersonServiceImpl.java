package springboot.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.springboot.bean.Person;
import springboot.springboot.service.PersonService;

public class PersonServiceImpl implements PersonService {
    @Autowired
    private Person person;

    @Override
    public Person getPersonInfo() {
        return person;
    }
}
