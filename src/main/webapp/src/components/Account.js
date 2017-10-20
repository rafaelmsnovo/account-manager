import React, { Component } from 'react';
import { Col, Button } from 'react-bootstrap';
import '../styles/App.css';

export default class Account extends Component {

  constructor(props) {
    super(props);
    this.state = { accounts: [] }

    this.listAccount = this.listAccount.bind(this);
    this.removeAccount = this.removeAccount.bind(this);
  }

  removeAccount(account){
    console.log(account);
    fetch(`http://localhost:8080/account/remove?person=${account}`, {
       method: 'POST'
    })
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
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
    this.listAccount();
  }


  render() {
    return (
      <Col sm={12} md={12} className="Content">
        <Button bsStyle="primary" >Adicionar Conta</Button><br></br><br></br>
        <table className="List-table">
          <thead>
          <tr>
            <th>ID</th>
            <th>NOME</th>
            <th>DATA DE CRIAÇÃO</th>
            <th>STATUS</th>
            <th>SALDO</th>
            <th>PESSOA</th>
            <th>PAI</th>
            <th>AÇÕES</th>
          </tr>
          </thead>
          <tbody>
          {this.state.accounts.map(function(account){
            return (
              <tr>
                <td>{account.id}</td>
                <td>{account.accountName}</td>
                <td>{account.createDate}</td>
                <td>{account.status}</td>
                <td>{account.balance}</td>
                <td>{account.person.id}</td>
                <td>{account.accountParent}</td>
                <td><Button bsSize="small" >Editar</Button> <Button bsStyle="danger" bsSize="small" onClick={() => this.removeAccount(account.id)}>Remover</Button></td>
              </tr>
            )
          }, this)}
          </tbody>
        </table>
      </Col>
    );
  }

}
