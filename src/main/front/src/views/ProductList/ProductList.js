import React, { useState, useEffect } from "react";
// core components
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Product from "components/Product/Product.js";
import ShoppingCart from "components/Product/ShoppingCart.js";
import AddShoppingCartIcon from "@material-ui/icons/AddShoppingCart";
import { makeStyles, withStyles } from "@material-ui/core/styles";
import { properties } from "../../properties.js";
import RestClient from "../../RestClient";
import Button from "@material-ui/core/Button";

const useStyles = makeStyles((theme) => ({
  button: {
    margin: theme.spacing(1),
  },
}));

let urlServer = "http://localhost:8080";
export default function ProductsList() {
  const [showShoppingCart, setShowShoppingCart] = useState(false);
  const [products, setProducts] = useState([]);
  const getListProducts = () => {
    RestClient.get(
      properties.darProducto,
      {},
      { productText: "Dol", limit: 10 }
    )
      .then((json) => {
        console.log(json.products);
        setProducts(json.products);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    getListProducts();
  }, []);
  const classes = useStyles();
  const changeState = (e) => {
    console.log(showShoppingCart);
    setShowShoppingCart(!showShoppingCart);
  };

  const showPickedIcon = (icon) => {
    console.info(icon); // prints {name: "access_alarm", code: "e190"}
  };

  const getProductsBar = () => {
    fetch(urlServer + "/inventario/productos/darProductos?productText=ole", {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
      })
      .catch((e) => {
        console.log(e);
      });
  };
  return (
    <div>
      <GridContainer>
        {products.map((product) => (
          <GridItem xs={12} sm={6} md={6} lg={3} xl={3}>
            <Product
              id={product.id}
              unitaryValue={product.finalPrice}
              currency={product.currency}
              productName={product.name}
            />
          </GridItem>
        ))}
      </GridContainer>
    </div>
  );
}
