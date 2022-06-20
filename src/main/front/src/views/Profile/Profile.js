import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import CustomInput from "components/CustomInput/CustomInput.js";
import Button from "components/CustomButtons/Button.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardAvatar from "components/Card/CardAvatar.js";
import CardBody from "components/Card/CardBody.js";
import CardFooter from "components/Card/CardFooter.js";

import avatar from "assets/img/faces/marc.jpg";

const styles = {
  cardCategoryWhite: {
    color: "rgba(255,255,255,.62)",
    margin: "0",
    fontSize: "14px",
    marginTop: "0",
    marginBottom: "0"
  },
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none"
  }
};

const useStyles = makeStyles(styles);

export default function UserProfile() {
  const classes = useStyles();
  return (
    <div>
      <GridContainer>
        <GridItem xs={12} sm={12} md={8}>
          <Card>
            <CardHeader color="primary">
              <h4 className={classes.cardTitleWhite}>Perfil</h4>
              <p className={classes.cardCategoryWhite}>Detalles</p>
            </CardHeader>
            <CardBody>
              <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Nombre:</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>Javier Díaz</InputLabel>
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Cargo:</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>Solicitante</InputLabel>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                <br/>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Teléfono:</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>3000000000</InputLabel>
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Correo:</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>correo@email.com</InputLabel>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                <br/>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Solicitudes vigentes:</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>4</InputLabel>
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Solicitudes totales:</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>343</InputLabel>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                <br/>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Proveedores utilizados:</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>6</InputLabel>
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                <br/>
                  <InputLabel style={{ color: "#AAAAAA" }}>Artículos totales en Inventario</InputLabel>
                  <br/>
                  <InputLabel style={{ color: "#000000" }}>12366</InputLabel>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                <br/>
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={12}>
                  <CustomInput
                    labelText="Recomendados:"
                    id="company-disabled"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      disabled: true
                    }}
                  />
                </GridItem>
              </GridContainer>
            </CardBody>
            <CardFooter>
              <Button color="primary">Actualizar Datos</Button>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={12} md={4}>
          <Card profile>
            <CardAvatar profile> 
              <a href="#pablo" onClick={e => e.preventDefault()}>
                <img src={avatar} alt="..." />
              </a>
            </CardAvatar>
            <CardBody profile>
              <h6 className={classes.cardCategory}>SOLICITANTE</h6>
              <h4 className={classes.cardTitle}>Andrés Manrique</h4>
              <p className={classes.description}>
                Desarrolladora de software
              </p>
              <Button color="primary" round>
                Guardar
              </Button>
            </CardBody>
          </Card>
        </GridItem>
      </GridContainer>
    </div>
  );
}
