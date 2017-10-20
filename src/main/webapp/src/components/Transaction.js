import React, { Component } from 'react';
import { Col, Button } from 'react-bootstrap';
import '../styles/App.css';

export default class Transaction extends Component {

  constructor(props) {
    super(props);
    this.state = { transactions: [] }

    this.listTransaction = this.listTransaction.bind(this);
    this.reverse = this.reverse.bind(this);
  }

  reverse(transaction){
    fetch('http://localhost:8080/transaction/reverse', {
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
      alert('Erro ao extornar o valor da transação '+transaction.id+', tente novamente mais tarde.');
    });
  }

  listTransaction(){
    fetch('http://localhost:8080/transaction/list')
    .then(response => response.json())
    .then(transactions => {
      console.log(transactions);
      this.setState({ transactions: transactions });
      console.log(this.state);
    });
  }

  componentDidMount() {
    this.listTransaction();
  }


  render() {
    return (
      <Col sm={12} md={12} className="Content">
        <Button bsStyle="primary" href="/transaction/add">Realizar Nova Transação</Button><br></br><br></br>
        <table className="List-table">
          <thead>
          <tr>
            <th>ID</th>
            <th>DATA</th>
            <th>TIPO</th>
            <th>VALOR</th>
            <th>EXTORNADA</th>
            <th>CONTA ORIGEM</th>
            <th>CONTA DESTINO</th>
            <th>AÇÕES</th>
          </tr>
          </thead>
          <tbody>
          {this.state.transactions.map(function(transaction){
            var d = new Date(transaction.transactionDate);
            let month = String(d.getMonth() + 1);
            let day = String(d.getDate());
            const year = String(d.getFullYear());
            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;
            let date  = day+"/"+month+"/"+year;
            return (
              <tr>
                <td>{transaction.id}</td>
                <td>{date}</td>
                <td>{transaction.type}</td>
                <td>{transaction.value}</td>
                <td>{transaction.reversed === true ? 'Sim': 'Não'}</td>
                <td>{transaction.accountInId}</td>
                <td>{transaction.accountOutId}</td>
                <td>{transaction.reversed === true ? '' : (<Button bsStyle="danger" bsSize="small" onClick={() => this.reverse(transaction)}>Extornar</Button>)}</td>
              </tr>
            )
          }, this)}
          </tbody>
        </table>
      </Col>
    );
  }

}
