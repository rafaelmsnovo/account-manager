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
    fetch('http://localhost:8080/account/remove', {
       method: 'POST',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({account: account})
    })
    .then((response) => {
      if (!response.ok) {
        throw Error();
      }
      this.listAccount();
    })
    .catch((error) => {
      alert('Erro ao remover a conta '+account.id+', verifique se esta conta não está atrelada a nenhuma outra conta.');
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
        <Button bsStyle="primary" href='/account/add'>Adicionar Conta</Button><br></br><br></br>
        <table className="List-table">
          <thead>
          <tr>
            <th>ID</th>
            <th>NOME</th>
            <th>DATA DE CRIAÇÃO</th>
            <th>STATUS</th>
            <th>SALDO</th>
            <th>PESSOA</th>
            <th>TIPO</th>
            <th>AÇÕES</th>
          </tr>
          </thead>
          <tbody>
          {this.state.accounts.map(function(account){
            var d = new Date(account.createDate);
            let month = String(d.getMonth() + 1);
            let day = String(d.getDate());
            const year = String(d.getFullYear());
            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;
            let date  = day+"/"+month+"/"+year;
            return (
              <tr>
                <td>{account.id}</td>
                <td>{account.accountName}</td>
                <td>{date}</td>
                <td>{account.status}</td>
                <td>{account.balance}</td>
                <td>{account.personId}</td>
                <td>{account.accountParentId === null ? 'MATRIZ' : 'FILIAL: ' + account.accountParentId}</td>
                <td><Button bsSize="small" href={"/account/edit/" + account.id}>Editar</Button> <Button bsStyle="danger" bsSize="small" onClick={() => this.removeAccount(account)}>Remover</Button></td>
              </tr>
            )
          }, this)}
          </tbody>
        </table>
      </Col>
    );
  }

}
