import React, { Component } from 'react';
import { Col, Button, Form, FormGroup, FormControl, ControlLabel } from 'react-bootstrap';
import '../styles/App.css';

export default class Transaction extends Component {

  constructor(props) {
    super(props);
    this.state = { transaction: {}, accounts: [] }

    this.listAccount = this.listAccount.bind(this);
    this.register = this.register.bind(this);

    this.setType = this.setType.bind(this);
    this.setAccountIn = this.setAccountIn.bind(this);
    this.setAccountOut = this.setAccountOut.bind(this);
    this.setValue = this.setValue.bind(this);
    this.setKey = this.setKey.bind(this);
  }

  setType(event) {
    var transaction = this.state.transaction;
    transaction.type = event.target.value
    this.setState({ transaction: transaction });
  }
  setKey(event) {
    var transaction = this.state.transaction;
    transaction.transactionKey = event.target.value
    this.setState({ transaction: transaction });
  }
  setAccountIn(event) {
    var transaction = this.state.transaction;
    transaction.accountInId = event.target.value
    this.setState({ transaction: transaction });
  }
  setAccountOut(event) {
    var transaction = this.state.transaction;
    transaction.accountOutId = event.target.value
    this.setState({ transaction: transaction });
  }
  setValue(event) {
    var transaction = this.state.transaction;
    transaction.value = event.target.value
    this.setState({ transaction: transaction });
  }


  register(){
    console.log(this.state.transaction);
    fetch('http://localhost:8080/transaction/add', {
       method: 'POST',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({transaction: this.state.transaction})
    })
    .then((response) => {
      console.log(response);
      if (!response.ok) {
        throw Error();
      }
      this.props.router.push('/transaction');
    })
    .catch((error) => {
      alert('Erro ao registrar a transação, tente novamente mais tarde.');
    });
  }

  listAccount(){
    fetch('http://localhost:8080/account/list')
    .then(response => response.json())
    .then(accounts => {
      this.setState({ accounts: accounts });
    });
  }

  componentDidMount() {
    this.listAccount();
  }


  render() {
    return (
      <Col sm={12} md={12} className="Content">
        <Form horizontal>
          <FormGroup controlId="type">
            <Col componentClass={ControlLabel} sm={2}>
              TIPO:
            </Col>
            <Col sm={10}>
              <FormControl componentClass="select" onChange={this.setType}>
                <option value="0"></option>
                <option value="TRANSFERÊCIA">TRANSFERÊCIA</option>
                <option value="APORTE">APORTE</option>
              </FormControl>
            </Col>
          </FormGroup>
          {this.state.transaction.type === 'APORTE' ? (
            <FormGroup controlId="key">
                <Col componentClass={ControlLabel} sm={2}>
                  CHAVE DE SEGURANÇA:
                </Col>
                <Col sm={10}>
                  <FormControl type="text" onChange={this.setKey}/>
                </Col>
              </FormGroup>
            ) :
            (<FormGroup controlId="accountIn">
              <Col componentClass={ControlLabel} sm={2}>
                CONTA ORIGEM:
              </Col>
              <Col sm={10}>
                <FormControl componentClass="select" onChange={this.setAccountIn}>
                  <option></option>
                  {this.state.accounts.map(function(account){
                    return (
                      <option value={account.id}>{account.accountName}</option>
                    )
                  }, this)}
                </FormControl>
              </Col>
            </FormGroup>)}

          <FormGroup controlId="accountOut">
            <Col componentClass={ControlLabel} sm={2}>
              CONTA DESTINO:
            </Col>
            <Col sm={10}>
              <FormControl componentClass="select" onChange={this.setAccountOut}>
                <option></option>
                {this.state.accounts.map(function(account){
                  return (
                    <option value={account.id}>{account.accountName}</option>
                  )
                }, this)}
              </FormControl>
            </Col>
            </FormGroup>
          <FormGroup controlId="value">
            <Col componentClass={ControlLabel} sm={2}>
              VALOR:
            </Col>
            <Col sm={10}>
              <FormControl type="text" placeholder="0.00" onChange={this.setValue}/>
            </Col>
          </FormGroup>
          <Button href="/transaction">Cancelar</Button> <Button bsStyle="primary" onClick={(transaction) => this.register(transaction)}>Registrar Transação</Button>
        </Form>
      </Col>
    );
  }

}
