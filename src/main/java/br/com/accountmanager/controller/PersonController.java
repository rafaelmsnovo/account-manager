package br.com.accountmanager.controller;

import br.com.accountmanager.model.PersonRequest;
import br.com.accountmanager.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(path = "/get", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity get(@RequestBody PersonRequest request) {
        logger.info(String.format("get person id='%s'", request.getPerson().getId()));
        return new ResponseEntity(personService.get(request.getPerson().getId()), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/remove", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity remove(@RequestBody PersonRequest request) {
        try {
            logger.info(String.format("removing person id='%s'", request.getPerson().getId()));
            personService.remove(request.getPerson().getId());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error removing person id='%s'", request.getPerson().getId()), e);
            return new ResponseEntity(String.format("Erro ao remover a pessoa %s", request.getPerson()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity add(@RequestBody PersonRequest request) {
        try {
            logger.info(String.format("saving person %s", request.getPerson()));
            personService.save(request.getPerson());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error saving person %s", request.getPerson()), e);
            return new ResponseEntity(String.format("Erro ao criar a pessoa %s", request.getPerson()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/edit", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity edit(@RequestBody PersonRequest request) {
        try {
            logger.info(String.format("editing person %s", request.getPerson()));
            personService.save(request.getPerson());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error editing person %s", request.getPerson()), e);
            return new ResponseEntity(String.format("Erro ao editar a pessoa %s", request.getPerson()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}