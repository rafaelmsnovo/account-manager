import React, { Component } from 'react';
import { Col, Button, Form, FormGroup, FormControl, ControlLabel, Radio } from 'react-bootstrap';
import '../styles/App.css';

export default class PersonAdd extends Component {

  constructor(props) {
    super(props);

    var paramPerson = {};
    paramPerson.id = props.routeParams.id;
    this.findPerson(paramPerson);

    this.state = { person: {}, type: "true" }

    this.addPerson= this.addPerson.bind(this);
    this.setType = this.setType.bind(this);
    this.setFullName = this.setFullName.bind(this);
    this.setBirthDate = this.setBirthDate.bind(this);
    this.setCpf = this.setCpf.bind(this);
    this.setCnpj = this.setCnpj.bind(this);
    this.setFantasyName = this.setFantasyName.bind(this);
    this.setCompanyName = this.setCompanyName.bind(this);
  }

  findPerson( person ){
    fetch('http://localhost:8080/person/get', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({person: person})
    })
    .then(response => response.json())
    .then(person => {
      this.setState({ person: person });
      console.log(this.state);
    });
  }

  addPerson(){
    console.log(this.state);
    fetch('http://localhost:8080/person/edit', {
       method: 'POST',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json'
       },
       body: JSON.stringify({person: this.state.person})
    })
    .then((response) => {
      if (!response.ok) {
        throw Error();
      }
      this.props.router.push('/person');
    })
    .catch((error) => {
      alert('Erro ao editar a pessoa, verifique se todos os campos estão preenchidos corretamente.');
    });
  }


  setType(event) {
    this.setState({ type: event.target.value });
  }
  setFullName(event) {
    var person = this.state.person;
    person.fullName = event.target.value
    this.setState({ person: person });
  }
  setBirthDate(event) {
    var person = this.state.person;
    var dateParts = event.target.value.split("/");
    var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
    person.birthDate = dateObject.getTime();
    this.setState({ person: person });
  }
  setCpf(event) {
    var person = this.state.person;
    person.cpf = event.target.value
    this.setState({ person: person });
  }
  setCnpj(event) {
    var person = this.state.person;
    person.cnpj = event.target.value
    this.setState({ person: person });
  }
  setFantasyName(event) {
    var person = this.state.person;
    person.fantasyName = event.target.value
    this.setState({ person: person });
  }
  setCompanyName(event) {
    var person = this.state.person;
    person.companyName = event.target.value
    this.setState({ person: person });
  }

  componentDidMount() {

  }

  render() {
    return (
      <Col sm={12} md={12} className="Content">
        <Form horizontal>
          <FormGroup>
            <Col componentClass={ControlLabel} sm={2}>
              TIPO DE PESSOA:
            </Col>
            <Col sm={10} className="Input-radio">
              <Radio name="radioGroup" onChange={this.setType} value="true">Física</Radio>
              <Radio name="radioGroup" onChange={this.setType} value="false">Jurídica</Radio>
            </Col>
          </FormGroup>
          {this.state.type === "true" ? ( <div>
            <FormGroup controlId="cpf">
              <Col componentClass={ControlLabel} sm={2}>
                CPF:
              </Col>
              <Col sm={10}>
                <FormControl type="text" value={this.state.person.cpf} onChange={this.setCpf}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="fullName">
              <Col componentClass={ControlLabel} sm={2}>
                NOME COMPLETO:
              </Col>
              <Col sm={10}>
                <FormControl type="text" value={this.state.person.fullName} onChange={this.setFullName}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="birthDate">
              <Col componentClass={ControlLabel} sm={2}>
                ANIVERSÁRIO:
              </Col>
              <Col sm={10}>
                <FormControl type="text" placeholder="DD/MM/AAAA" value={this.state.person.birthDate} onChange={this.setBirthDate}/>
              </Col>
            </FormGroup>
          </div>) : (<div>
            <FormGroup controlId="cnpj">
              <Col componentClass={ControlLabel} sm={2}>
                CNPJ:
              </Col>
              <Col sm={10}>
                <FormControl type="text" value={this.state.person.cnpj} onChange={this.setCnpj}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="fantasyName">
              <Col componentClass={ControlLabel} sm={2}>
                NOME FANTASIA:
              </Col>
              <Col sm={10}>
                <FormControl type="text" value={this.state.person.fantasyName} onChange={this.setFantasyName}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="companyName">
              <Col componentClass={ControlLabel} sm={2}>
                RAZÃO SOCIAL:
              </Col>
              <Col sm={10}>
                <FormControl type="text" value={this.state.person.companyName} onChange={this.setCompanyName}/>
              </Col>
            </FormGroup>
          </div>)}
          <Button href="/person">Cancelar</Button> <Button bsStyle="primary" onClick={() => this.addPerson()}>Atualizar Pessoa</Button>
        </Form>
       
      </Col>
    );
  }

}
