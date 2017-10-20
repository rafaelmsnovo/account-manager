import React, { Component } from 'react';
import '../styles/App.css';
import Header from './Header';
import Person from './Person';
import Account from './Account';


export default class App extends Component {

  render() {
    return (
      <div className="App">
        <Header/>
        <Person/>
        <Account/>
      </div>

    );
  }

}
