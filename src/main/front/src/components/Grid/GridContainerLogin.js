import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import Grid from "@material-ui/core/Grid";

const styles = {
  grid: {
    width: "100vw",
    height: "100vh"
  }
};

const useStyles = makeStyles(styles);

export default function GridContainerLogin(props) {
  const classes = useStyles();
  const { children, image, ...rest } = props;
  return (
    <Grid container {...rest} className={classes.grid} style={{ backgroundImage: 'url(' + image + ')' }}>
      {children}
    </Grid>
  );
}

GridContainerLogin.propTypes = {
  children: PropTypes.node
};
