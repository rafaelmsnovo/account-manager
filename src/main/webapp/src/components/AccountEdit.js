import React, { Component } from 'react';
import { Col, Button, Form, FormGroup, FormControl, ControlLabel } from 'react-bootstrap';
import '../styles/App.css';

export default class AccountEdit extends Component {

    constructor(props) {
      super(props);

      var paramAccount = {};
      paramAccount.id = props.routeParams.id;
      this.findAccount(paramAccount);

      this.state = { persons: [], account: {} }

      this.listPerson = this.listPerson.bind(this);
      this.addAccount = this.addAccount.bind(this);
      this.setName = this.setName.bind(this);
      this.setPerson = this.setPerson.bind(this);
      this.setStatus = this.setStatus.bind(this);
    }

    findAccount( account ){
      fetch('http://localhost:8080/account/get', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({account: account})
      })
      .then(response => response.json())
      .then(account => {
        this.setState({ account: account });
        //this.setState({ personId: account.personId });
        console.log(this.state);
      });
    }

    addAccount(){
      console.log(this.state);
      fetch('http://localhost:8080/account/edit', {
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
        alert('Erro ao editar a nova conta, verifique se todos os campos estão preenchidos corretamente.');
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

    listPerson(){
      fetch('http://localhost:8080/person/list')
      .then(response => response.json())
      .then(persons => {
        this.setState({ persons: persons });
      });
    }

    componentDidMount() {
      this.listPerson();
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
                <FormControl type="text" value={this.state.account.accountName} onChange={this.setName}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="person">
              <Col componentClass={ControlLabel} sm={2}>
                PESSOA:
              </Col>
              <Col sm={10}>
                <FormControl componentClass="select" value={this.state.account.personId} onChange={this.setPerson}>
                  <option value="0"></option>
                  {this.state.persons.map(function(person){
                      return (
                          <option value={person.id} >{person.fullName}{person.companyName}</option>
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
                <FormControl componentClass="select" value={this.state.account.status} onChange={this.setStatus}>
                  <option value="0"></option>
                  <option value="ATIVA">ATIVA</option>
                  <option value="BLOQUEADA">BLOQUEADA</option>
                  <option value="CANCELADA">CANCELADA</option>
                </FormControl>
              </Col>
            </FormGroup>
            <Button href="/account">Cancelar</Button> <Button bsStyle="primary" onClick={(account) => this.addAccount(account)}>Atualizar Conta</Button>
          </Form>

        </Col>
      );
    }

}
