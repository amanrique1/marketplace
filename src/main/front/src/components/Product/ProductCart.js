// @material-ui/core
// core components
import GridContainer from "components/Grid/GridContainer.js";
import CardBody from "components/Card/CardBody.js";
//images
import dolex from "assets/img/dolex.jpg"
import React, { useState } from "react";
// @material-ui/core
import { Input } from "@material-ui/core";
// core components
import GridItem from "components/Grid/GridItem.js";
import Button from "components/CustomButtons/Button.js";
import  addOrderProduct  from '../../redux/actions/addOrderProduct';
import  editOrderProduct  from '../../redux/actions/editOrderProduct';
import  removeOrderProduct  from '../../redux/actions/removeOrderProduct';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { withRouter } from "react-router";
import { TextField} from "@material-ui/core";

const useStyles = makeStyles({
  buttonsContainer: {
   display: 'flex',
   alignItems: 'center'
  }
});

function ProductCart({editOrderProduct, removeOrderProduct, id, quantityP, unitaryValue, detailsP , currency}) {

  const [quantity, setQuantity] = useState(quantityP);
  const [details, setDetails] = useState(detailsP );
  const classes = useStyles();
  const valueChange = (e) => {
    if(e.target.id === 'quantity'){
      editOrderProduct({productId:id, quantity:e.target.value, unitaryValue, currency, details})
      setQuantity(quantity)
    }
    else if(e.target.id === 'details'){
      editOrderProduct({productId:id, quantity, unitaryValue, currency, details})
      setDetails(details)
    }
  }
  const increment = () => {
    editOrderProduct({productId:id, quantity:quantity + 1, unitaryValue, currency, details})
    setQuantity(quantity + 1)
    console.log(quantity);
  }
  const reduce = () => {
    if(quantity===1){
      removeOrderProduct({productId:id})

    }else{
      setQuantity(quantity - 1)
      editOrderProduct({productId:id, quantity:quantity - 1, unitaryValue, currency, details})
    }
    console.log(quantity);
  }
  const mystyle = {
    backgroundColor: '#e0e0e0' 
  };
  

  return(
    <GridContainer direction="row"  
        justify="center"
        alignItems="center" style={mystyle}>
      <GridContainer direction="row" 
          justify="center"
          alignItems="center" xs={5}>
      <img color="success" src={dolex} width="150px" height="150px"md={2} ></img>
        <CardBody md={2} xs={2} sm={2} md={2} lg={2} xl={2} style={mystyle}>
          <h4 multiline className={classes.cardTitle}>Dolex 500 mg</h4>
          <TextField
              id="details"
              label="Detalles"
              placeholder="Detalles"
              multiline
              variant="outlined"
              value={details}
              onChange={valueChange}
              maxLength={50}
              InputLabelProps={{
                shrink: true,
              }}
          />
          </CardBody>
      </GridContainer>

        <GridContainer
          direction="row"
          justify="center"
          alignItems="center" xs={3}>
          <GridItem xs={5} sm={3} md={3}>
            <Button color="primary" onClick={reduce} round>-</Button>
          </GridItem>
          <GridItem xs={5} sm={3} md={3}>
            <Input id="quantity" inputProps={{min: 0, style: { textAlign: 'center' }}} defaultValue={quantityP} value={quantityP} onChange={valueChange}/>
          </GridItem>
          <GridItem xs={3} sm={3} md={3}>
            <Button color="primary" onClick={increment} round>+</Button>
          </GridItem>
        </GridContainer>
      
      <h4 xs={4} style={mystyle}>Total: ${unitaryValue*quantityP}{" ("+currency+")"}</h4>
    </GridContainer>
  )
}

const mapStateToProps = state => ({  
  orderProducts: state.orderProducts  
}); 

const mapDispatchToProps = (dispatch) => ({
  addOrderProduct:(payload) => dispatch(addOrderProduct(payload)),
  editOrderProduct:(payload) => dispatch(editOrderProduct(payload)),
  removeOrderProduct:(payload) => dispatch(removeOrderProduct(payload))
});


const wrapper = connect(mapStateToProps, mapDispatchToProps);
const component = wrapper(ProductCart);

export default withRouter(component);