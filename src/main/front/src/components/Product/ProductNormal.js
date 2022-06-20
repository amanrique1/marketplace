import React from "react";
// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
// core components
import GridContainer from "components/Grid/GridContainer.js";
import CardImage from "components/Card/CardImage.js";
import CardBody from "components/Card/CardBody.js";
//images
import dolex from "assets/img/dolex.jpg"

import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";

const useStyles = makeStyles(styles);

export default function NormalCard({productName, unitaryValue, currency}) {
  const classes = useStyles();
  return(
    <div>
      <GridContainer justify="center">
      <CardImage color="success" image={dolex} imgWidth={"200px"} imgHeight={"200px"}></CardImage>
      </GridContainer>
    <CardBody>
      <h4 className={classes.cardTitle}>{productName}</h4>
      <p className={classes.cardCategory}>
        <span className={classes.successText}>
          55% Descuento
        </span>
      </p>
      <h4 className={classes.cardTitle}>{unitaryValue}{" ("+currency+")"}</h4>
    </CardBody>
  </div>
  )
}
/*Nombre, precio, "impuesto", imagen*/