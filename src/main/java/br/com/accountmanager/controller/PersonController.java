package br.com.accountmanager.controller;

import br.com.accountmanager.entity.PersonEntity;
import br.com.accountmanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<PersonEntity> products = personService.getAll();
        return new ResponseEntity(products, HttpStatus.OK);
    }
}