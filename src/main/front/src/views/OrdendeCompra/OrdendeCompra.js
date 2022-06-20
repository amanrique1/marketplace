import React, { useEffect, useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// core components
import GridItem from "components/Grid/GridItem.js";
import GridFormulario from "components/Grid/GridFormulario.js";
import Card from "components/Card/Card.js";
import GridContainer from "components/Grid/GridContainer.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import Prueba from "components/Menu/Prueba.js";
import { properties } from "../../properties.js";
import RestClient from "../../RestClient";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import Primary from "components/Typography/Primary.js";
import Fab from "@material-ui/core/Fab";
import AddIcon from "@material-ui/icons/Add";
import EditIcon from "@material-ui/icons/Edit";
import RemoveIcon from "@material-ui/icons/Remove";
import ZoomInIcon from "@material-ui/icons/ZoomIn";
import ZoomOutIcon from "@material-ui/icons/ZoomOut";
import DropdownAcciones from "components/Menu/DropdownAcciones";
import MenuItem from "@material-ui/core/MenuItem";
import FormHelperText from "@material-ui/core/FormHelperText";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import { Button } from "@material-ui/core";

const styles = {
  cardCategoryWhite: {
    "&,& a,& a:hover,& a:focus": {
      color: "rgba(255,255,255,.62)",
      margin: "0",
      fontSize: "14px",
      marginTop: "0",
      marginBottom: "0",
    },
    "& a,& a:hover,& a:focus": {
      color: "#FFFFFF",
    },
  },
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none",
    "& small": {
      color: "#777",
      fontSize: "65%",
      fontWeight: "400",
      lineHeight: "1",
    },
  },
};

const useStyles = makeStyles(styles);

export default function TableList() {
  const [purchaseOrders, setPurchaseOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState("");
  const [editing, setEditing] = useState(false);
  const [selectedOrderToEdit, setSelectedOrderToEdit] = useState("");
  const [newPurchaseOrderState, setNewPurchaseOrderState] = useState("");
  const [filterState, setFilterState] = useState("INGRESADO");
  const [isProvider, setIsProvider] = useState(false);

  var stateList = [
    "INGRESADO",
    "PENDIENTE DE O/C",
    "ENVIO PENDIENTE",
    "ENVIADO",
    "ENTREGADO",
    "PASADO A REPORTE",
    "FACTURADO",
  ];

  useEffect(() => {
    //getPurchaseOrders();
    let role = localStorage.getItem("role");
    if (role === "PROVEEDOR" || role === "TESTER") {
      getPurchaseOrdersFilterByState(filterState);
      setIsProvider(true);
    } else if (role === "SOLICITANTE") {
      getPurchaseOrders();
    } else {
    }
  }, []);

  const getPurchaseOrders = () => {
    RestClient.get(properties.darOrdenDeCompraPersona, {}, { limit: 10 }).then(
      (json) => {
        console.log(json.purchaseOrders);
        setPurchaseOrders(json.purchaseOrders);
      }
    );
  };

  const getPurchaseOrdersFilterByState = (state) => {
    RestClient.get(properties.darOrdenDeCompra + state, {}, { limit: 10 }).then(
      (json) => {
        console.log(json.purchaseOrders);
        setPurchaseOrders(json.purchaseOrders);
      }
    );
  };

  const updatePurchaseOrderState = (state) => {
    RestClient.put(
      properties.cambiarEstadoOrdenDeCompra +
        selectedOrderToEdit +
        "/cambiarEstado",
      { newState: newPurchaseOrderState },
      {}
    );
  };

  const classes = useStyles();
  return (
    <GridFormulario>
      <GridItem xs={12} sm={12} md={12}>
        <Card>
          <CardHeader color="primary">
            <h4 className={classes.cardTitleWhite}>
              {" "}
              Historial Ordenes de Compras
            </h4>
            <p className={classes.cardCategoryWhite}>Ordenes de compra</p>
          </CardHeader>
          {isProvider && (
            <CardBody>
              <FormControl className={classes.formControl}>
                <InputLabel id="demo-simple-select-label">Estado</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={filterState}
                  onChange={(e) => {
                    setFilterState(e.target.value);
                    getPurchaseOrdersFilterByState(e.target.value);
                  }}
                >
                  {stateList.map((state) => (
                    <MenuItem value={state.replace(" ", "_")}>{state}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </CardBody>
          )}
        </Card>
      </GridItem>
      {purchaseOrders.map((e, key) => {
        return (
          <GridItem xs={6} sm={6} md={6}>
            <Card>
              <CardBody>
                <GridContainer>
                  <GridItem xs={11}>
                    <div>
                      <h4>
                        <Primary>{"ESTADO: "}</Primary> {e.state}
                        {"    "}
                        {isProvider && (
                          <Fab
                            size="small"
                            color="primary"
                            aria-label="edit"
                            onClick={(event) => {
                              setEditing(!editing);
                              setSelectedOrderToEdit(e.id);
                            }}
                          >
                            <EditIcon />
                          </Fab>
                        )}
                        {"    "}
                        {selectedOrder !== key ? (
                          <Fab
                            size="small"
                            color="primary"
                            aria-label="add"
                            onClick={(e) => {
                              console.log(key);
                              setSelectedOrder(key);
                            }}
                          >
                            <AddIcon />
                          </Fab>
                        ) : (
                          <Fab
                            size="small"
                            color="primary"
                            aria-label="minus"
                            onClick={(e) => setSelectedOrder("")}
                          >
                            <RemoveIcon />
                          </Fab>
                        )}
                      </h4>
                      {editing && isProvider && (
                        <div>
                          <FormControl className={classes.formControl}>
                            <h6>
                              Por favor, elija el estado al que quiere pasar la
                              orden de compra
                            </h6>
                            <Select
                              labelId="demo-simple-select-label"
                              id="demo-simple-select"
                              value={newPurchaseOrderState}
                              onChange={(e) => {
                                setNewPurchaseOrderState(e.target.value);
                              }}
                            >
                              {stateList.map((state) => (
                                <MenuItem value={state}>{state}</MenuItem>
                              ))}
                            </Select>
                          </FormControl>
                          <br></br>
                          <br></br>
                          <Button
                            size="small"
                            variant="contained"
                            color="primary"
                            className="center-align"
                            onClick={(e) => {
                              getPurchaseOrdersFilterByState(filterState);
                              updatePurchaseOrderState();
                              setEditing(!editing);
                              setSelectedOrderToEdit("");
                            }}
                          >
                            Guardar
                          </Button>
                        </div>
                      )}
                      {selectedOrder === key && (
                        <div>
                          <h4>
                            <Primary>HISTORIAL DE ESTADOS</Primary>
                          </h4>
                          <ol>
                            {e.stateChanges.map((ec) => (
                              <li>
                                <div>
                                  <h6>{"ESTADO: " + ec.state}</h6>
                                  <h6>
                                    {"FECHA DE MODIFICACIÓN: " +
                                      ec.modDate.replace("T", " ")}
                                  </h6>
                                  <h6>
                                    {"NOMBRE DEL ENCARGADO: " + ec.modifierName}
                                  </h6>
                                </div>
                              </li>
                            ))}
                          </ol>
                        </div>
                      )}
                      <h4>
                        <Primary>{"FECHA DE CREACIÓN: "}</Primary>
                        {e.creationDate.replace("T", " ")}
                      </h4>
                      <h4>
                        <Primary>{"DIRECCIÓN DE ENTREGA: "}</Primary>{" "}
                        {e.location.address +
                          " (" +
                          e.location.country +
                          ", " +
                          e.location.state +
                          ", " +
                          e.location.city +
                          ")"}
                      </h4>

                      <div>
                        <h4>
                          <Primary>LISTA DE PRODUCTOS</Primary>
                        </h4>
                        <ol>
                          {e.orderProducts.map((f) => (
                            <li>
                              <div>
                                <h6>
                                  {"Nombre: " + f.product.name}
                                  <br></br>
                                  {"Cantidad: " + f.quantity}
                                  <br></br>
                                  {"Total: " +
                                    f.totalValue +
                                    " (" +
                                    f.currency +
                                    ")"}
                                </h6>
                              </div>
                            </li>
                          ))}
                        </ol>
                      </div>
                    </div>
                  </GridItem>
                </GridContainer>
              </CardBody>
            </Card>
          </GridItem>
        );
      })}
    </GridFormulario>
  );
}
