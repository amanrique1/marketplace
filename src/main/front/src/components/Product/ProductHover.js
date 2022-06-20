import React from "react";
// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
// core components
import CardBody from "components/Card/CardBody.js";

import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";

const useStyles = makeStyles(styles);

export default function HoverCard() {
  const classes = useStyles();
  return(
  <CardBody chart>
      <h4 className={classes.cardTitle}>Nombre:</h4> 
      <br/>
      <h4 className={classes.cardTitle}>Proveedor</h4>
      <br/>
      <h4 className={classes.cardTitle}>Descripción</h4>
      <br/>
      <h4 className={classes.cardTitle}>Ciudad Proveedor</h4>
  </CardBody>
  )
}
/*Modelo, Stok, Medidas, Peso, Categoría
*/

