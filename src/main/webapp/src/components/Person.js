import React, { Component } from 'react';
import { Col, Button } from 'react-bootstrap';
import '../styles/App.css';

export default class Person extends Component {

  constructor(props) {
    super(props);
    this.state = { persons: [] }

    this.listPerson = this.listPerson.bind(this);
    this.removePerson = this.removePerson.bind(this);
  }

  removePerson(person){
    console.log(person);
    fetch(`http://localhost:8080/person/remove?person=${person}`, {
       method: 'POST'
    })
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
    });
  }

  listPerson(){
    fetch('http://localhost:8080/person/list')
    .then(response => response.json())
    .then(persons => {
      this.setState({ persons: persons });
      console.log(this.state);
    });
  }

  componentDidMount() {
    this.listPerson();
  }


  render() {
    return (
      <Col sm={12} md={12} className="Content">
        <Button bsStyle="primary" >Adicionar Pessoa</Button><br></br><br></br>
        <table className="List-table">
          <thead>
          <tr>
            <th>ID</th>
            <th>CPF</th>
            <th>NOME COMPLETO</th>
            <th>ANIVERSÁRIO</th>
            <th>CNPJ</th>
            <th>NOME FANTASIA</th>
            <th>RAZÃO SOCIAL</th>
            <th>AÇÕES</th>
          </tr>
          </thead>
          <tbody>
          {this.state.persons.map(function(person){
            return (
              <tr>
                <td>{person.id}</td>
                <td>{person.cpf}</td>
                <td>{person.fullName}</td>
                <td>{person.birthDate}</td>
                <td>{person.cnpj}</td>
                <td>{person.fantasyName}</td>
                <td>{person.companyName}</td>
                <td><Button bsSize="small" >Editar</Button> <Button bsStyle="danger" bsSize="small" onClick={() => this.removePerson(person.id)}>Remover</Button></td>
              </tr>
            )
          }, this)}
          </tbody>
        </table>
      </Col>
    );
  }

}
