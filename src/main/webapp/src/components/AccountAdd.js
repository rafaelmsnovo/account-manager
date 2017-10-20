import React, { Component } from 'react';
import { Col, Button, Form, FormGroup, FormControl, ControlLabel } from 'react-bootstrap';
import '../styles/App.css';

export default class AccountAdd extends Component {

  constructor(props) {
    super(props);
    this.state = { persons: [], account: {}, accounts: [] }

    this.listPerson = this.listPerson.bind(this);
    this.listAccount = this.listAccount.bind(this);
    this.addAccount = this.addAccount.bind(this);
    this.setName = this.setName.bind(this);
    this.setPerson = this.setPerson.bind(this);
    this.setStatus = this.setStatus.bind(this);
    this.setParent = this.setParent.bind(this);
  }

  addAccount(){
    console.log(this.state);
    fetch('http://localhost:8080/account/add', {
       method: 'POST',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({account: this.state.account})
    })
    .then((response) => {
      if (!response.ok) {
        throw Error();
      }
      this.props.router.push('/account');
    })
    .catch((error) => {
      alert('Erro ao criar a nova conta, verifique se todos os campos estão preenchidos corretamente.');
    });
  }

  setName(event) {
    var account = this.state.account;
    account.accountName = event.target.value
    this.setState({ account: account });
  }
  setPerson(event) {
    var account = this.state.account;
    account.personId = event.target.value
    this.setState({ account: account });
  }
  setStatus(event) {
    var account = this.state.account;
    account.status = event.target.value
    this.setState({ account: account });
  }
  setParent(event) {
    var account = this.state.account;
    account.accountParentId = event.target.value
    this.setState({ account: account });
  }

  listPerson(){
    fetch('http://localhost:8080/person/list')
    .then(response => response.json())
    .then(persons => {
      this.setState({ persons: persons });
    });
  }

  listAccount(){
      fetch('http://localhost:8080/account/list')
      .then(response => response.json())
      .then(accounts => {
        this.setState({ accounts: accounts });
        console.log(this.state);
      });
    }

  componentDidMount() {
    this.listPerson();
    this.listAccount();
  }

  render() {
    return (
      <Col sm={12} md={12} className="Content">
        <Form horizontal>
          <FormGroup controlId="accountName">
            <Col componentClass={ControlLabel} sm={2}>
              NOME:
            </Col>
            <Col sm={10}>
              <FormControl type="text" placeholder="" onChange={this.setName}/>
            </Col>
          </FormGroup>
          <FormGroup controlId="person">
            <Col componentClass={ControlLabel} sm={2}>
              PESSOA:
            </Col>
            <Col sm={10}>
              <FormControl componentClass="select" onChange={this.setPerson}>
                <option></option>
                {this.state.persons.map(function(person){
                    return (
                        <option value={person.id}>{person.fullName}{person.companyName}</option>
                    )
                }, this)}
              </FormControl>
            </Col>
          </FormGroup>
          <FormGroup controlId="status">
            <Col componentClass={ControlLabel} sm={2}>
              STATUS:
            </Col>
            <Col sm={10}>
              <FormControl componentClass="select" onChange={this.setStatus}>
                <option value="0"></option>
                <option value="ATIVA">ATIVA</option>
                <option value="BLOQUEADA">BLOQUEADA</option>
                <option value="CANCELADA">CANCELADA</option>
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
          <Button href="/account">Cancelar</Button> <Button bsStyle="primary" onClick={(account) => this.addAccount(account)}>Criar Conta</Button>
        </Form>
       
      </Col>
    );
  }

}
