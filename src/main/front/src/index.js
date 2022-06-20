/*!

=========================================================
* Material Dashboard React - v1.9.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-react
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/material-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";
import ReactDOM from "react-dom";
import { createBrowserHistory } from "history";
import { Router, Route, Switch, Redirect } from "react-router-dom";
import store from "./redux/store/store";
import { Provider } from "react-redux";
// core components
import Admin from "layouts/Admin.js";
import Login from "components/Login/Login";
import ShoppingCart from "components/Product/ProductCart";
import routes from "routes.js";
//import RouteManager from "components/RouteManager/RouteManager";
import "assets/css/material-dashboard-react.css?v=1.9.0";

const hist = createBrowserHistory();
ReactDOM.render(
  <Provider store={store}>
    <Router history={hist}>
      <Switch>
        <Route path="/admin/:component" component={Admin} />
        <Route path="/admin" component={Admin} />
        <Route path="/login" component={Login} />
        <Redirect from="/" to="/admin" />
      </Switch>
    </Router>
  </Provider>,
  document.getElementById("root")
);
