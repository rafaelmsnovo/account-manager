package br.com.accountmanager.controller;

import br.com.accountmanager.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        logger.info(String.format("list all persons"));
        return new ResponseEntity(personService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/remove", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity remove(@RequestParam(value = "person", required = true) Long personId) {
        try {
            logger.info(String.format("removing person id='%s'", personId));
            personService.remove(personId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error removing person id='%s'", personId), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}