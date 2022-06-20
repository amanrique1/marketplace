import React, { useEffect, useState } from "react";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// core components
import DropdownCategories from "components/Menu/DropdownCategories.js";
import GridItem from "components/Grid/GridItem.js";
import GridFormulario from "components/Grid/GridFormulario";
import Button from "components/CustomButtons/Button.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import CardFooter from "components/Card/CardFooter.js";
import Grid from "@material-ui/core/Grid";
import addImage from "assets/img/addimage.jpg";
import { Input } from "@material-ui/core";
import { createBrowserHistory } from "history";
import InputLabel from '@material-ui/core/InputLabel';
import TextField from '@material-ui/core/TextField';

const history = createBrowserHistory();



const styles = {
  cardCategoryWhite: {
    color: "rgba(255,255,255,.62)",
    margin: "0",
    fontSize: "14px",
    marginTop: "0",
    marginBottom: "0",
  },
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none",
  },
  inputstyle: {
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
  },
};

const useStyles = makeStyles(styles);

export default function AgregarProducto() {
  const [name, setName] = useState("");
  const [category, setCategory] = useState();
  const [totalprice, setTotalPrice] = useState();
  const [taxvalue, setTaxValue] = useState();
  const [currency, setCurrency] = useState("");
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState();
  const [stock, setStock] = useState();
  const [width, setWidth] = useState();
  const [height, setHeight] = useState();
  const [depth, setDepth] = useState();
  const [measureUnit, setMeasureUnit] = useState("");
  const [weight, setWeight] = useState();
  const [weightUnit, setWeightUnit] = useState("");
  const [image, setImage] = useState();
  const [format, setFormat] = useState("");

  const [baseImage, setBaseImage] = useState("");
  const [img, setImg] = useState(addImage);
  

  const location = {
    pathname: "/admin/products",
  };
  const redirecting = () => {
    window.location.reload();
  };

  const selectCategory = (id) => {
    console.log(id);
    setCategory(id);
  };

  const imageinitial = (e) => {
    setImg("  ");
    console.log(e.target.value);
    var formato = e.target.value;
    formato = formato.split(".");
    var nuevo = formato[1].toUpperCase();
    console.log(nuevo);

    setFormat(nuevo);
  };

  const uploadImage = async (e) => {
    const file = e.target.files[0];
    const base64 = await convertBase64(file);
    setBaseImage(base64);
    setImage(base64);
    console.log(base64);
  };

  const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);

      fileReader.onload = () => {
        resolve(fileReader.result);
      };

      fileReader.onerror = (error) => {
        reject(error);
      };
    });
  };

  
  const valueChange = (e) => {
    if (e.target.id === "name") {
      setName(e.target.value);
    } else if (e.target.id === "totalPrice") {
      setTotalPrice(e.target.value);
    } else if (e.target.id === "taxValue") {
      setTaxValue(e.target.value);
    } else if (e.target.id === "currency") {
      setCurrency(e.target.value);
    } else if (e.target.id === "brand") {
      setBrand(e.target.value);
    } else if (e.target.id === "model") {
      setModel(e.target.value);
    } else if (e.target.id === "stock") {
      setStock(e.target.value);
    } else if (e.target.id === "width") {
      setWidth(e.target.value);
    } else if (e.target.id === "height") {
      setHeight(e.target.value);
    } else if (e.target.id === "depth") {
      setDepth(e.target.value);
    } else if (e.target.id === "measureUnit") {
      setMeasureUnit(e.target.value);
    } else if (e.target.id === "weight") {
      setWeight(e.target.value);
    } else if (e.target.id === "weightUnit") {
      setWeightUnit(e.target.value);
    } else if (e.target.id === "image") {
      setImage(e.target.value);
    } 
  };

  useEffect(() => {
    getCategories();
  }, []);

  const getCategories = () => {
    fetch(urlServer + "/inventario/categorias", {
      method: "GET",
      headers: new Headers({
        Accept: "application/json",
        Authorization: "bearer " + localStorage.getItem("token"),
      }),
    })
      .then((res) => res.json())
      .then((json) => {
        console.log(json);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  let urlServer = "http://localhost:8080";
  const addProduct = () => {
    var data = {
      name: name,
      totalPrice: totalprice,
      taxValue: taxvalue,
      currency: currency,
      brand: brand,
      model: model,
      stock: stock,
      measures: {
        width: width,
        height: height,
        depth: depth,
        measureUnit: measureUnit,
      },
      weight: weight,
      weightUnit: weightUnit,
      category: category,
      image: {
        image: image,
        format: format,
      },
    };
    console.log(JSON.stringify(data));
    fetch(urlServer + "/inventario/producto", {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "bearer " + localStorage.getItem("token"),
      },
    }).then((res) => {
      console.log(res.status);
      if (res.status === 204) {
        console.log(res.status);
        history.push(location);
        redirecting();
      }
    });
  };

  const classes = useStyles();
  return (
    <div>
      <GridFormulario>
        <Card>
          <CardHeader color="primary">
            <h4 className={classes.cardTitleWhite}>Agregar Producto</h4>
            <p className={classes.cardCategoryWhite}>
              Complete los Siguientes Datos
            </p>
          </CardHeader>

          <CardBody className={classes.inputstyle}>
            <Grid container spacing={1}>
              <Grid item xs={12} sm={8}>
                <Grid container spacing={7}>
                  <Grid item xs={12} sm={5}>
                    <TextField  error  id="name" label="Nombre" onChange={valueChange} />
                  </Grid>
                  <Grid item xs={12} sm={5}></Grid>
                  <Grid item xs={12} sm={4}>
                  <TextField id="totalPrice" label="Precio"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="taxValue" label="Impuesto"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="currency" label="Moneda"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="brand" label="Marca"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="model" label="Modelo"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="stock" label="Stock"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="width" label="Ancho"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="height" label="Alto"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="depth" label="Largo"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="measureUnit" label="Unidad de Medida"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="weight" label="Peso"  onChange={valueChange} />
                  </Grid>

                  <Grid item xs={12} sm={4}>
                  <TextField id="weightUnit" label="Unidad de Peso"  onChange={valueChange} />
                  </Grid>

                  <Grid
                    item
                    xs={12}
                    sm={4}
                    style={{
                      position: "relative",
                      left: "20px",
                      bottom: "43px",
                    }}
                  >
                    <DropdownCategories selectFunction={selectCategory} />
                  </Grid>
                </Grid>
              </Grid>

              <Grid item xs={12} sm={4}>
                <GridItem xs={6} sm={6} md={10}>
                  <img src={baseImage} height="250px"></img>
                  <input
                    type="image"
                    src={img}
                    style={{ position: "relative", left: "20px" }}
                  ></input>
                </GridItem>

                <Button
                  color="rose"
                  variant="contained"
                  component="label"
                  style={{ position: "relative", left: "50px" }}
                >
                  Cargar Imagen
                  <input
                    id="image"
                    type="file"
                    style={{ display: "none" }}
                    onChange={(e) => {
                      uploadImage(e);
                      imageinitial(e);
                    }}
                  ></input>
                </Button>
              </Grid>
            </Grid>
          </CardBody>

          <CardFooter>
            <GridItem xs={12} sm={12} md={12}>
              <Button color="primary" onClick={addProduct}>
                Agregar Producto
              </Button>
            </GridItem>
          </CardFooter>
        </Card>
      </GridFormulario>
    </div>
  );
}
