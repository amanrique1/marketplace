import React, { useState } from "react";
import { createBrowserHistory } from "history";
// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
import Link from "@material-ui/core/Link";
// @material-ui/icons
import AccessTime from "@material-ui/icons/AccessTime";
// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainerLogin from "components/Grid/GridContainerLogin.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import CardFooter from "components/Card/CardFooter.js";
import Button from "components/CustomButtons/Button.js";

import styles from "assets/jss/material-dashboard-react/views/loginStyle.js";
import bgImage from "assets/img/loginbg.jpg";
import { Input } from "@material-ui/core";
import { properties } from "../../properties.js";
import RestClient from "../../RestClient";
const useStyles = makeStyles(styles);

const redirecting = () => {
  window.location.reload();
};

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const valueChange = (e) => {
    if (e.target.id === "email") {
      setEmail(e.target.value);
    } else {
      setPassword(e.target.value);
    }
  };
  const history = createBrowserHistory();
  const location = {
    pathname: "/admin",
  };
  var data = {
    email: email,
    contrasenia: password,
  };

  const login = () => {
    RestClient.post(properties.login, data, {}, {})
      .then((res) => res.json())
      .then((json) => {
        localStorage.setItem("token", json.accessToken);
        localStorage.setItem("role", json.role);
        console.log(json);
        if (json.accessToken) {
          history.push(location);
          redirecting();
        }
      });
  };
  const classes = useStyles();

  return (
    <div>
      <GridContainerLogin image={bgImage} justify="center" alignItems="center">
        <GridItem xs={12} sm={8} md={4}>
          <Card chart>
            <CardHeader color="info">Iniciar Sesión</CardHeader>
            <CardBody>
              <h4 className={classes.cardTitle}>Correo electrónico</h4>
              <Input
                fullWidth
                margin="normal"
                id="email"
                label="Correo electrónico"
                type="email"
                onChange={valueChange}
              ></Input>
              <br />
              <br />
              <h4 className={classes.cardTitle}>Contraseña</h4>
              <Input
                fullWidth
                margin="normal"
                id="password"
                label="Contraseña"
                type="password"
                onChange={valueChange}
              ></Input>
              <br />
              <br />
              <div align="center">
                <a href="" className={classes.block - 191}>
                  ¿Olvidaste tu contraseña?
                </a>
              </div>
              <br></br>
              <div align="center">
                <Link to={location}>
                  <Button onClick={login} color="primary">
                    Iniciar Sesión
                  </Button>
                </Link>
              </div>
            </CardBody>
            <CardFooter chart>
              <div className={classes.stats}>
                <AccessTime /> Andrés Manrique - 2020
              </div>
            </CardFooter>
          </Card>
        </GridItem>
      </GridContainerLogin>
    </div>
  );
}
export default Login;
