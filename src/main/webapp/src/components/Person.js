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
    fetch('http://localhost:8080/person/remove', {
       method: 'POST',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({person: person})
    })
    .then((response) => {
      if (!response.ok) {
        throw Error();
      }
      this.listPerson();
    })
    .catch((error) => {
      alert('Erro ao remover a pessoa '+person.id+', verifique se esta pessoa não está atrelada a nenhuma conta.');
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
        <Button bsStyle="primary" href="/person/add">Adicionar Pessoa</Button><br></br><br></br>
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
            var d = new Date(person.birthDate);
            let month = String(d.getMonth() + 1);
            let day = String(d.getDate());
            const year = String(d.getFullYear());
            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;
            let date  = day+"/"+month+"/"+year;
            return (
              <tr>
                <td>{person.id}</td>
                <td>{person.cpf}</td>
                <td>{person.fullName}</td>
                <td>{date}</td>
                <td>{person.cnpj}</td>
                <td>{person.fantasyName}</td>
                <td>{person.companyName}</td>
                <td><Button bsSize="small" href={"/person/edit/" + person.id}>Editar</Button> <Button bsStyle="danger" bsSize="small" onClick={() => this.removePerson(person)}>Remover</Button></td>
              </tr>
            )
          }, this)}
          </tbody>
        </table>
      </Col>
    );
  }

}
