package br.com.accountmanager.controller;

import br.com.accountmanager.model.AccountRequest;
import br.com.accountmanager.service.AccountService;
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
@RequestMapping(path = "/account")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        logger.info(String.format("list all accounts."));
        return new ResponseEntity(accountService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/get", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity get(@RequestBody AccountRequest request) {
        logger.info(String.format("get account id='%s'", request.getAccount().getId()));
        return new ResponseEntity(accountService.get(request.getAccount().getId()), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/remove", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity remove(@RequestBody AccountRequest request) {
        try {
            logger.info(String.format("removing account id='%s'", request.getAccount().getId()));
            accountService.remove(request.getAccount().getId());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error removing account id='%s'", request.getAccount().getId()), e);
            return new ResponseEntity(String.format("Erro ao remover a conta %s", request.getAccount()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity add(@RequestBody AccountRequest request) {
        try {
            logger.info(String.format("saving account %s", request.getAccount()));
            accountService.save(request.getAccount());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error saving account %s", request.getAccount()), e);
            return new ResponseEntity(String.format("Erro ao criar a conta %s", request.getAccount()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/edit", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity edit(@RequestBody AccountRequest request) {
        try {
            logger.info(String.format("editing account %s", request.getAccount()));
            accountService.update(request.getAccount());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("error editing account %s", request.getAccount()), e);
            return new ResponseEntity(String.format("Erro ao editar a conta %s", request.getAccount()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}