import React, { useEffect }  from "react";
// @material-ui/core
import Icon from "@material-ui/core/Icon";

// @material-ui/icons
import Update from "@material-ui/icons/Update";

// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import CardHeader from "components/Card/CardHeader.js";
import CardFooter from "components/Card/CardFooter.js";
import CardIconCategoria from "components/Card/CardIconCategoria.js"
import CardCategoria from "components/Card/CardCategoria"

import Modal from"components/Modal/Modal.js"

export default function Categoria()  { 

  useEffect(() => {
    console.log("imprimo categorias");
  }, [
    console.log("consulta back")])
  
  return (
    <div>
   <GridContainer>
        <GridItem xs={12} sm={6} md={5}>
        <CardCategoria  >
            <CardHeader color="info" stats icon   >
              <CardIconCategoria  color="info"  >
               <Modal></Modal>
              </CardIconCategoria>
            </CardHeader>
            <CardFooter stats>
            <div style={{ position:"relative",left:"70px", textAlign: "center",
                flex: 1}} >
              </div>
            </CardFooter>
          </CardCategoria>
        </GridItem>
      </GridContainer>
    </div>
  );
}
