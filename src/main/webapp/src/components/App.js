import React, { Component } from 'react';
import '../styles/App.css';
import Header from './Header';
import Person from './Person';
import PersonAdd from './PersonAdd';
import PersonEdit from './PersonEdit';
import Account from './Account';
import AccountAdd from './AccountAdd';
import AccountEdit from './AccountEdit';
import Transaction from './Transaction';
import TransactionAdd from './TransactionAdd';

import {Router,Route,browserHistory} from 'react-router';


export default class App extends Component {

  render() {
    return (
      <div className="App">
        <Header/>
        <Router history={browserHistory}>
            <Route path="/person" component={Person} />
            <Route path="/person/add" component={PersonAdd} />
            <Route path="/person/edit/:id" component={PersonEdit} />
            <Route path="/account" component={Account} />
            <Route path="/account/add" component={AccountAdd} />
            <Route path="/account/edit/:id" component={AccountEdit} />
            <Route path="/transaction" component={Transaction} />
            <Route path="/transaction/add" component={TransactionAdd} />
        </Router>
      </div>

    );
  }

}
