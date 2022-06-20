import React, { useState, useEffect } from "react";
// @material-ui/core
import { TextField, Input } from "@material-ui/core";
// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Card from "components/Card/Card.js";
import CardFooter from "components/Card/CardFooter.js";
import Button from "components/CustomButtons/Button.js";
import ProductNormal from "components/Product/ProductNormal.js";
import ProductHover from "components/Product/ProductHover.js";
import { connect } from "react-redux";
import addOrderProduct from "../../redux/actions/addOrderProduct";
import editOrderProduct from "../../redux/actions/editOrderProduct";
import removeOrderProduct from "../../redux/actions/removeOrderProduct";

import { withRouter } from "react-router";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
  buttonsContainer: {
    display: "flex",
    alignItems: "center",
  },
});

function Product({
  addOrderProduct,
  editOrderProduct,
  removeOrderProduct,
  id,
  unitaryValue,
  orderProduct,
  currency,
  productName
}) {
  const [showDetails, setShowDetails] = useState(false);
  const showDetailsCard = () => setShowDetails(!showDetails);
  const [quantity, setQuantity] = useState(0);
  const [details, setDetails] = useState("");
  useEffect(() => {
    if (orderProduct !== undefined) {
      setQuantity(orderProduct.quantity);
    } else {
      setQuantity(0);
    }
  });
  const valueChange = (e) => {
    if (e.target.id === "quantity") {
      editOrderProduct({ productId: id, quantity: e.target.value, details, unitaryValue, currency});
      setQuantity(e.target.value);
    }else if(e.target.id === "details") {
      editOrderProduct({ productId: id, quantity, details: e.target.value, unitaryValue, currency});
      setDetails(e.target.value);
    }
  };
  const increment = () => {
    if (quantity === 0) {
      addOrderProduct({ productId: id, quantity: 1, unitaryValue, currency });
      setQuantity(1);
    } else {
      editOrderProduct({ productId: id, quantity: quantity + 1, unitaryValue, currency });
      setQuantity(quantity + 1);
    }
    console.log(quantity + 1);
  };
  const reduce = () => {
    if (quantity === 1) {
      setQuantity(0);
      removeOrderProduct({ productId: id });
    } else {
      editOrderProduct({ productId: id, quantity: quantity - 1, unitaryValue, currency });
      setQuantity(quantity - 1);
    }
    console.log(quantity - 1);
  };
  return (
    <div>
      <Card>
        <div onClick={showDetailsCard}>
          {showDetails ? <ProductHover /> : <ProductNormal productName={productName} currency={currency} unitaryValue={unitaryValue}/>}
        </div>
        <CardFooter>
          <div>
            {quantity === 0 ? (
              <GridContainer>
                <GridItem xs={12}>
                  <Button color="primary" onClick={increment}>
                    Agregar
                  </Button>
                </GridItem>
              </GridContainer>
            ) : (
              <div className="center">
                <GridContainer
                  direction="row"
                  justify="center"
                  alignItems="center"
                >
                  <GridItem>
                    <TextField
                      id="details"
                      label="Detalles"
                      placeholder="Detalles"
                      multiline
                      variant="outlined"
                      value={details}
                      onChange={valueChange}
                      maxLength={50}
                    />
                  </GridItem>
                </GridContainer>
                <br></br>
                <div>
                  <GridContainer
                    direction="row"
                    justify="center"
                    alignItems="center"
                  >
                    <GridItem xs={3} sm={3} md={3}>
                      <Button color="primary" onClick={reduce} round>
                        -
                      </Button>
                    </GridItem>
                    <GridItem xs={3} sm={3} md={3}>
                      <Input
                        id="quantity"
                        inputProps={{ min: 0, style: { textAlign: "center" } }}
                        defaultValue={quantity}
                        value={quantity}
                        onChange={valueChange}
                      />
                    </GridItem>
                    <GridItem xs={3} sm={3} md={3}>
                      <Button color="primary" onClick={increment} round>
                        +
                      </Button>
                    </GridItem>
                  </GridContainer>
                </div>{" "}
              </div>
            )}
          </div>
        </CardFooter>
      </Card>
    </div>
  );
}

const mapStateToProps = (state, ownProps) => ({
  orderProduct: state.orderProducts.orderProducts.find(
    (element) => element.productId === ownProps.id
  ),
});

const mapDispatchToProps = (dispatch) => ({
  addOrderProduct: (payload) => dispatch(addOrderProduct(payload)),
  editOrderProduct: (payload) => dispatch(editOrderProduct(payload)),
  removeOrderProduct: (payload) => dispatch(removeOrderProduct(payload)),
});

const wrapper = connect(mapStateToProps, mapDispatchToProps);
const component = wrapper(Product);

export default withRouter(component);
