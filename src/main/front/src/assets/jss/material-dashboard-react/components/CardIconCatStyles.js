import {
    warningCardHeader,
    successCardHeader,
    dangerCardHeader,
    infoCardHeader,
    primaryCardHeader,
    roseCardHeader,
    grayColor
  } from "assets/jss/material-dashboard-react.js";
  
  const cardIconStyle = {
    cardIcon: {
      "&$warningCardHeader,&$successCardHeader,&$dangerCardHeader,&$infoCardHeader,&$primaryCardHeader,&$roseCardHeader": {
        borderRadius: "100px",
        backgroundColor: grayColor[0],
        padding: "18px",
        marginTop: "5px",
        marginRight: "50px",
        float: "left",
        position:"relative",
        left:"80px",
    
        

      }
    },
    warningCardHeader,
    successCardHeader,
    dangerCardHeader,
    infoCardHeader,
    primaryCardHeader,
    roseCardHeader
  };
  
  export default cardIconStyle;
  