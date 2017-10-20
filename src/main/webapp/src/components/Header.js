import React, { Component } from 'react';
import { Col } from 'react-bootstrap';
import '../styles/App.css';

export default class Header extends Component {

  render() {
    return (
      <header className="App-header">
        <Col sm={1} md={3}>Gerenciador de Contas HUB</Col>
        <Col sm={1} md={1}>Pessoas</Col>
        <Col sm={1} md={1}>Contas</Col>
        <Col sm={1} md={1}>Transferências</Col>
      </header>
    );
  }

}