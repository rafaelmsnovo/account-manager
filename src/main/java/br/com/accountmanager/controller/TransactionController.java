package br.com.accountmanager.controller;

import br.com.accountmanager.model.TransactionRequest;
import br.com.accountmanager.service.TransactionService;
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

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        logger.info(String.format("list all transactions."));
        return new ResponseEntity(transactionService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/get", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity get(@RequestBody TransactionRequest request) {
        logger.info(String.format("get transaction id='%s'", request.getTransaction().getId()));
        return new ResponseEntity(transactionService.get(request.getTransaction().getId()), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/reverse", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity reverse(@RequestBody TransactionRequest request) {
        try {
            logger.info(String.format("extorning transaction %s", request.getTransaction()));
            transactionService.reverse(request.getTransaction());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error extorning transaction %s", request.getTransaction()), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity register(@RequestBody TransactionRequest request) {
        try {
            logger.info(String.format("register transaction %s", request.getTransaction()));
            transactionService.register(request.getTransaction());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error register transaction %s", request.getTransaction()), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}