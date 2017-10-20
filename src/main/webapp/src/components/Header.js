import React, { Component } from 'react';
import { Col } from 'react-bootstrap';
import '../styles/App.css';

export default class Header extends Component {

  render() {
    return (
      <header className="App-header">
        <Col sm={1} md={3}><a href="/">Gerenciador de Contas HUB</a></Col>
        <Col sm={1} md={1}><a href="/person">Pessoas</a></Col>
        <Col sm={1} md={1}><a href="/account">Contas</a></Col>
        <Col sm={1} md={1}><a href="/transaction">Transferências</a></Col>
      </header>
    );
  }

}