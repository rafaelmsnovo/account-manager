package br.com.accountmanager.controller;

import br.com.accountmanager.entity.AccountEntity;
import br.com.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        Set<AccountEntity> companies = accountService.getAll();
        return new ResponseEntity(companies, HttpStatus.OK);
    }
}