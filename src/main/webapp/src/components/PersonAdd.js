import React, { Component } from 'react';
import { Col, Button, Form, FormGroup, FormControl, ControlLabel, Radio } from 'react-bootstrap';
import '../styles/App.css';

export default class PersonAdd extends Component {

  constructor(props) {
    super(props);
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

  addPerson(){
    console.log(this.state);
    fetch('http://localhost:8080/person/add', {
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
      alert('Erro ao criar a nova pessoa, verifique se todos os campos estão preenchidos corretamente.');
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
                <FormControl type="text" placeholder="" onChange={this.setCpf}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="fullName">
              <Col componentClass={ControlLabel} sm={2}>
                NOME COMPLETO:
              </Col>
              <Col sm={10}>
                <FormControl type="text" placeholder="" onChange={this.setFullName}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="birthDate">
              <Col componentClass={ControlLabel} sm={2}>
                ANIVERSÁRIO:
              </Col>
              <Col sm={10}>
                <FormControl type="text" placeholder="DD/MM/AAAA" onChange={this.setBirthDate}/>
              </Col>
            </FormGroup>
          </div>) : (<div>
            <FormGroup controlId="cnpj">
              <Col componentClass={ControlLabel} sm={2}>
                CNPJ:
              </Col>
              <Col sm={10}>
                <FormControl type="text" placeholder="" onChange={this.setCnpj}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="fantasyName">
              <Col componentClass={ControlLabel} sm={2}>
                NOME FANTASIA:
              </Col>
              <Col sm={10}>
                <FormControl type="text" placeholder="" onChange={this.setFantasyName}/>
              </Col>
            </FormGroup>
            <FormGroup controlId="companyName">
              <Col componentClass={ControlLabel} sm={2}>
                RAZÃO SOCIAL:
              </Col>
              <Col sm={10}>
                <FormControl type="text" placeholder="" onChange={this.setCompanyName}/>
              </Col>
            </FormGroup>
          </div>)}
          <Button href="/person">Cancelar</Button> <Button bsStyle="primary" onClick={() => this.addPerson()}>Criar Pessoa</Button>
        </Form>
       
      </Col>
    );
  }

}
