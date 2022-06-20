import React , { useEffect, useState } from "react";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// core components
import Desplegable from "components/Menu/Desplegable.js"
import GridItem from "components/Grid/GridItem.js";
import GridFormulario from "components/Grid/GridFormulario"
import Upload from "components/Upload/Upload"
import CustomInput from "components/CustomInput/CustomInput.js";
import Button from "components/CustomButtons/Button.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import CardFooter from "components/Card/CardFooter.js";
import Grid from '@material-ui/core/Grid';



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
  },

};

const useStyles = makeStyles(styles);

export default function AgregarProducto() {

  const [name, setName] = useState("");
  const [categoria, setCategoria] = useState("");
  const [precio, setPrice] = useState("");
  const [taxvalue, setTaxValue] = useState("");
  const [currency, setCurrency] = useState("");
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [stock, setStock] = useState("");
  const [width, setWidth] = useState("");
  const [height,setHeight]= useState("");
  const [depth,setDepth]= useState("");
  const [measureUnit,setMeasureUnit]=useState("");
  const [weight,setWeight]=useState("");
  const [weightUnit,setWeightUnit]=useState("");
  const [proveedor,setProveedor]=useState("");
  const [imagen,setImagen]=useState("");

  const Date = (e) => {
   
      if(e.target.id === 'name'){
        setName(e.target.value)
      }else if(e.target.id==='price'){
        setPrice(e.target.value)
      }else if(e.target.id==='taxvalue'){
        setTaxValue(e.target.value)
      }else if(e.target.id==='currency'){
        setCurrency(e.target.value)
      }else if(e.target.id==='brand'){
        setBrand(e.target.value)
      }else if(e.target.id==='model'){
        setModel(e.target.value)
      }else if(e.target.id==='stock'){
        setStock(e.target.value)
      }else if(e.target.id==='width'){
        setWidth(e.target.value)
      }else if(e.target.id==='height'){
        setHeight(e.target.value)
      }else if(e.target.id==='depth'){
        setDepth(e.target.value)
      }else if(e.target.id==='measureUnit'){
        setMeasureUnit(e.target.value)
      }else if(e.target.id==='weight'){
        setWeight(e.target.value)
      }else if(e.target.id==='weightunit'){
        setWeightUnit(e.target.value)
      }else if(e.target.id==='imagen'){
        setImagen(e.target.value)
      }
  }

  useEffect(() => {
  getCategories();
  });

  
 const getCategories =()=>{
  fetch(urlServer + '/inventario/categorias', {
    method: 'GET',
    headers:new Headers({
      Accept: 'application/json',
     Authorization:'bearer '+localStorage.getItem('token')
    })
  
  })
  .then((res) => res.json())
  .then((json) => {
    console.log(json);
    
   
  }).catch((error)=>{
    console.log(error)
  }
  )
 };


  let urlServer = 'http://localhost:8080'
  const upload = () => {
    fetch(urlServer + '/admin/AgregarProducto', {
      method: 'POST',
     /*  body: JSON.stringify(data), */
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }
    })
    .then((res) => res.json())
    .then((json) => {
     
    })
  }


  const classes = useStyles();
  return (
    <div>
      <GridFormulario>
          <Card>
            <CardHeader color="primary">
              <h4 className={classes.cardTitleWhite}>Agregar Producto</h4>
              <p className={classes.cardCategoryWhite}>Complete los Siguientes Datos</p>
            </CardHeader>

            <CardBody>
            <Grid container spacing={1}>
                   <Grid item xs={10} sm={9}>
                       <Grid container spacing={3}>
                       <Grid item xs={8} sm={5}>
                           <CustomInput
                              onChange={Date}
                               labelText="name"
                                id="username"
                               formControlProps={{
                             fullWidth: true
                           }}
                           />  
                       </Grid>
                        <Grid item xs={2} sm={5}>
                        </Grid>
                        <Grid item xs={3} sm={5}>
                        <CustomInput
                          onChange={Date}
                            labelText="Precio Total"
                    
                            formControlProps={{
                            fullWidth: true
                           }}
                         />
                        </Grid>

                        <Grid item xs={3} sm={5}>
                        <CustomInput
                           onChange={Date}
                          labelText="Valor taxvalue"
                           id="username"
                           formControlProps={{
                            fullWidth: true
                           }}
                        />
                        </Grid>

                    
                        <Grid item xs={3} sm={5}>
                          <CustomInput
                             onChange={Date}
                              labelText="currency"
                             id="username"
                              formControlProps={{
                             fullWidth: true
                             }}
                          />

                        </Grid>

                        <Grid item xs={3} sm={5}>
                            <CustomInput
                               onChange={Date}
                               labelText="brand"
                               id="username"
                              formControlProps={{
                               fullWidth: true
                                }}
                            />
                        </Grid>

                
                        <Grid item xs={3} sm={5}>
                        <CustomInput
                            onChange={Date}
                           labelText="model"
                           id="username"
                           formControlProps={{
                          fullWidth: true
                           }}
                          />
                        </Grid>
                        
                        <Grid item xs={3} sm={5}>
                        <CustomInput
                         onChange={Date}
                          labelText="Stock"
                           id="username"
                           formControlProps={{
                          fullWidth: true
                          }}
                        />
                        </Grid>

                        <Grid item xs={3} sm={4}>
                           <CustomInput
                          onChange={Date}
                           labelText="Medida width"
                             id="username"
                            formControlProps={{
                             fullWidth: true
                             }}
                           />
                        </Grid>

                        
                        <Grid item xs={3} sm={4}>
                           <CustomInput
                          onChange={Date}
                           labelText="Medida height "
                             id="username"
                            formControlProps={{
                             fullWidth: true
                             }}
                           />
                        </Grid>

                        
                        <Grid item xs={3} sm={4}>
                           <CustomInput
                          onChange={Date}
                           labelText="Medida Profuncidad"
                             id="username"
                            formControlProps={{
                             fullWidth: true
                             }}
                           />
                        </Grid>                        
                        <Grid item xs={3} sm={4}>
                           <CustomInput
                          onChange={Date}
                           labelText="Unidad de Medida"
                             id="username"
                            formControlProps={{
                             fullWidth: true
                             }}
                           />
                        </Grid>
                        
                        <Grid item xs={3} sm={4}>
                           <CustomInput
                          onChange={Date}
                           labelText="weight"
                             id="username"
                            formControlProps={{
                             fullWidth: true
                             }}
                           />
                        </Grid>
                        
                        <Grid item xs={3} sm={4}>
                           <CustomInput
                          onChange={Date}
                           labelText="Unidad de weight"
                             id="username"
                            formControlProps={{
                             fullWidth: true
                             }}
                           />
                        </Grid>
                        
                        <Grid item xs={3} sm={4}>
                           <CustomInput
                          onChange={Date}
                           labelText="Proveedor"
                             id="username"
                            formControlProps={{
                             fullWidth: true
                             }}
                           />
                        </Grid>

                        <Grid item xs={2} sm={5}>
                        <Desplegable>
                        </Desplegable>
                        </Grid>



                      </Grid>
                       
                    </Grid>
          
                    <Grid item xs={6} sm={3}>
                    <Upload></Upload>
                    </Grid>
       
                 </Grid>

            </CardBody>

            <CardFooter>
              
              <Button color="primary" onClick={upload}>Agregar Producto</Button>
              
            </CardFooter>
          </Card>
        
        <GridItem xs={12} sm={12} md={4}>
        
        </GridItem>
      </GridFormulario>
    </div>
  );
}
