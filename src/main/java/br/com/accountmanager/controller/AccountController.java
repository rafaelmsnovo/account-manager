package br.com.accountmanager.controller;

import br.com.accountmanager.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        logger.info(String.format("list all persons"));
        return new ResponseEntity(accountService.getAll(), HttpStatus.OK);
    }
}