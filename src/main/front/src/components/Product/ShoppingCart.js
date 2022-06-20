import React, { useState, useEffect } from "react";
// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
// core components
import GridContainer from "components/Grid/GridContainer.js";
import CardImage from "components/Card/CardImage.js";
import CardBody from "components/Card/CardBody.js";
import Button from "@material-ui/core/Button";
import GridItem from "components/Grid/GridItem.js";
//images
import dolex from "assets/img/dolex.jpg";
import ProductCart from "./ProductCart.js";
import { connect } from "react-redux";
import { withRouter } from "react-router";
import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";
import RestClient from "../../RestClient";
import Modal from "@material-ui/core/Modal";
import TextField from "@material-ui/core/TextField";
import { properties } from "../../properties";
import removeAllOrderProduct from "../../redux/actions/removeAllOrderProduct";

function getModalStyle() {
  const top = 50;
  const left = 50;

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`,
  };
}

const useStyles = makeStyles((theme) => ({
  paper: {
    position: "absolute",
    width: 500,
    backgroundColor: theme.palette.background.paper,
    border: "2px solid #000",
    boxShadow: theme.shadows[5],
    padding: theme.spacing(1, 1, 4),
    alignItems: "center",
    display: "inline-block",
  },
}));

function ShoppingCart({ orderProducts, removeAllOrderProduct }) {
  const classes = useStyles();

  var currencyBalance = {};
  var currencyBalanceKeys = [];

  const [currencyBalanceGlobal, setCurrencyBalanceGlobal] = useState({});
  const [currencyBalanceKeysGlobal, setCurrencyBalanceKeysGlobal] = useState(
    []
  );
  const [modalStyle] = useState(getModalStyle);
  const [open, setOpen] = useState(false);
  const [country, setCountry] = useState("");
  const [state, setState] = useState("");
  const [city, setCity] = useState("");
  const [address, setAddress] = useState("");
  const [details, setDetails] = useState("");

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    currencyBalance = {};
    currencyBalanceKeys = [];
    orderProducts.orderProducts.map((e) => {
      currencyBalance[e.currency] =
        currencyBalance[e.currency] !== undefined
          ? currencyBalance[e.currency] + e.unitaryValue * e.quantity
          : e.unitaryValue * e.quantity;
    });
    currencyBalanceKeys = Object.keys(currencyBalance);
    console.log(currencyBalanceKeys);
    setCurrencyBalanceGlobal(currencyBalance);
    setCurrencyBalanceKeysGlobal(currencyBalanceKeys);
  }, [orderProducts]);

  const sendPost = () => {
    var listAux = [];
    orderProducts.orderProducts.map((e) => {
      var objectAux = {
        productId: e.productId,
        quantity: e.quantity,
        details: e.details,
      };
      listAux.push(objectAux);
    });
    var data = {
      reviewNeeded: false,
      orderProducts: listAux,
      deliveryAddress: {
        country,
        city,
        state,
        address,
        details,
      },
    };

    RestClient.post(properties.ordenDeCompra, data, {}, {})
      .then((response) => {
        console.log(response);
        setCountry("");
        setCity("");
        setAddress("");
        setDetails("");
        setState("");
        setOpen(false);
        removeAllOrderProduct();
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const body = (
    <form style={modalStyle} className={classes.paper}>
      <GridContainer
        direction="row"
        justify="center"
        alignItems="center"
        spacing={1}
      >
        <h2 id="simple-modal-title">Dirección de entrega</h2>
        <GridItem xs={8}>
          <TextField
            fullWidth
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
            id="country"
            label="Pais"
            value={country}
            onChange={(e) => setCountry(e.target.value)}
          />
        </GridItem>
        <GridItem xs={8}>
          <TextField
            fullWidth
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
            id="city"
            label="Ciudad"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />
        </GridItem>
        <GridItem xs={8}>
          <TextField
            fullWidth
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
            id="state"
            label="Estado"
            value={state}
            onChange={(e) => setState(e.target.value)}
          />
        </GridItem>
        <GridItem xs={8}>
          <TextField
            fullWidth
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
            id="address"
            label="Dirección"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
        </GridItem>
        <GridItem xs={8}>
          <TextField
            fullWidth
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
            id="details"
            label="Detalles"
            value={details}
            onChange={(e) => setDetails(e.target.value)}
          />
        </GridItem>
        <GridItem xs={8} xs-offset={2}>
          <Button
            variant="contained"
            color="primary"
            fullWidth
            onClick={sendPost}
          >
            Confirmar
          </Button>
        </GridItem>
      </GridContainer>
    </form>
  );

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
      >
        {body}
      </Modal>
      {orderProducts.orderProducts.map((product) => (
        <ProductCart
          id={product.productId}
          quantityP={product.quantity}
          unitaryValue={product.unitaryValue}
          detailsP={product.details}
          currency={product.currency}
        ></ProductCart>
      ))}
      {orderProducts.orderProducts.length > 0 ? (
        <div>
          <GridContainer direction="row" justify="center" alignItems="center">
            <h3>Total:</h3>
            <ul>
              {currencyBalanceKeysGlobal.map((key) => {
                return (
                  <li>
                    <h3>
                      {currencyBalanceGlobal[key]}
                      {" ("}
                      {key}
                      {") "}
                    </h3>
                  </li>
                );
              })}
            </ul>
            <br></br>
          </GridContainer>
          <div align="center">
            <Button variant="contained" color="primary" onClick={handleOpen}>
              Comprar
            </Button>
          </div>
        </div>
      ) : (
        <div align="center">
          <h2>El carrito de compras está vacío</h2>
        </div>
      )}
    </div>
  );
}
const mapStateToProps = (state) => ({
  orderProducts: state.orderProducts,
});
const mapDispatchToProps = (dispatch) => ({
  removeAllOrderProduct: () => dispatch(removeAllOrderProduct()),
});

const wrapper = connect(mapStateToProps, mapDispatchToProps);
const component = wrapper(ShoppingCart);

export default withRouter(component);
