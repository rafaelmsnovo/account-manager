import React, { Component } from 'react';
import { Col, Button, Form, FormGroup, FormControl, ControlLabel } from 'react-bootstrap';
import '../styles/App.css';

export default class Transaction extends Component {

  constructor(props) {
    super(props);
    this.state = { transaction: {}, accounts: [] }

    this.listAccount = this.listAccount.bind(this);
    this.register = this.register.bind(this);
  }

  register(transaction){
    fetch('http://localhost:8080/transaction/add', {
       method: 'POST',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({transaction: transaction})
    })
    .then((response) => {
      if (!response.ok) {
        throw Error();
      }
      this.listTransaction();
    })
    .catch((error) => {
      alert('Erro ao registrar a transação '+transaction.id+', tente novamente mais tarde.');
    });
  }

  listAccount(){
    fetch('http://localhost:8080/account/list')
    .then(response => response.json())
    .then(accounts => {
      console.log(accounts);
      this.setState({ accounts: accounts });
      console.log(this.state);
    });
  }

  componentDidMount() {
    this.listAccount();
  }


  render() {
    return (
      <Col sm={12} md={12} className="Content">
        <Form horizontal>
          <FormGroup controlId="status">
            <Col componentClass={ControlLabel} sm={2}>
              STATUS:
            </Col>
            <Col sm={10}>
              <FormControl componentClass="select" onChange={this.setStatus}>
                <option value="0"></option>
                <option value="TRANSFERÊCIA">TRANSFERÊCIA</option>
                <option value="APORTE">APORTE</option>
              </FormControl>
            </Col>
          </FormGroup>
          <FormGroup controlId="parent">
            <Col componentClass={ControlLabel} sm={2}>
              CONTA FILIAL:
            </Col>
            <Col sm={10}>
              <FormControl componentClass="select" onChange={this.setParent}>
                <option></option>
                {this.state.accounts.map(function(account){
                  return (
                    <option value={account.id}>{account.accountName}</option>
                  )
                }, this)}
              </FormControl>
            </Col>
          </FormGroup>
          <FormGroup controlId="accountName">
            <Col componentClass={ControlLabel} sm={2}>
              NOME:
            </Col>
            <Col sm={10}>
              <FormControl type="text" placeholder="" onChange={this.setName}/>
            </Col>
          </FormGroup>
        </Form>
      </Col>
    );
  }

}
