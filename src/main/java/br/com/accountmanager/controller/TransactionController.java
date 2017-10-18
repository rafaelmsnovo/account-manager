package br.com.accountmanager.controller;

import br.com.accountmanager.entity.TransactionEntity;
import br.com.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        Set<TransactionEntity> orders = transactionService.getAll();
        return new ResponseEntity(orders, HttpStatus.OK);
    }
}