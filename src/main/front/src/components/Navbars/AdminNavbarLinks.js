import React, { useEffect, useState } from "react";
import classNames from "classnames";
import {Link} from "react-router-dom"
import { createBrowserHistory } from 'history';
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import MenuItem from "@material-ui/core/MenuItem";
import MenuList from "@material-ui/core/MenuList";
import Grow from "@material-ui/core/Grow";
import Paper from "@material-ui/core/Paper";
import ClickAwayListener from "@material-ui/core/ClickAwayListener";
import Hidden from "@material-ui/core/Hidden";
import Poppers from "@material-ui/core/Popper";
import Divider from "@material-ui/core/Divider";
import { Input } from "@material-ui/core";
// @material-ui/icons
import Person from "@material-ui/icons/Person";
import Notifications from "@material-ui/icons/Notifications";
import ShoppingCart from "@material-ui/icons/ShoppingCart";
import Search from "@material-ui/icons/Search";
// core components
import Button from "components/CustomButtons/Button.js";

import styles from "assets/jss/material-dashboard-react/components/headerLinksStyle.js";
import RestClient from "../../RestClient"

const useStyles = makeStyles(styles);

export default function AdminNavbarLinks(props) {
  useEffect(() => {
    getListProducts()
  }, [])
  const classes = useStyles();
  const [openOptions, setOpenOptions] = React.useState(null);
  const [openNotification, setOpenNotification] = React.useState(null);
  const [openProfile, setOpenProfile] = React.useState(null);
  const [searchText, setSearchText] = useState("");
  const [optionListBack, setOptionListBack] = React.useState(null);
  const [products, setProducts] = React.useState(null);
  const handleClickOptions = (event) => {
    if (openOptions && openOptions.contains(event.target)) {
      setOpenOptions(null);
    } else {
      setOpenOptions(event.currentTarget);
    }
  };
  const handleCloseOptions = () => {
    setOpenOptions(null);
  };
  const handleClickNotification = (event) => {
    if (openNotification && openNotification.contains(event.target)) {
      setOpenNotification(null);
    } else {
      setOpenNotification(event.currentTarget);
    }
  };
  const handleCloseNotification = () => {
    setOpenNotification(null);
  };
  const handleClickProfile = (event) => {
    if (openProfile && openProfile.contains(event.target)) {
      setOpenProfile(null);
    } else {
      setOpenProfile(event.currentTarget);
    }
  };
  const handleCloseProfile = () => {
    setOpenProfile(null);
  };
  const valueChange = (e) => {
    if (e.target.id === "searchText") {
      setSearchText(e.target.value);
      let filteredList = products.filter(product => (product.name).toUpperCase().includes(e.target.value.toUpperCase()));
      setOptionListBack(filteredList);
    }
  };

  function ListOptions() {
    const optionsMap = optionListBack.map((options) => 
      <MenuItem onClick={redirectToProducts} className={classes.dropdownItem}>
        {options.name}
      </MenuItem>
    );
    return(
      <MenuList role="menu">{optionsMap}</MenuList>
    )
  }
 //LLenar Lista
  const getListProducts = () => {
    /*RestClient.get("/inventario/productos", {}, {})
    .then((res) => res.json())
    .then((json) => {
      setOptionListBack(json.products)
      setProducts(json.products);
      console.log(optionListBack)
    }).catch((e) => {
      console.log(e);
    })*/
    }
  //Redirección con lupa
  const history = createBrowserHistory();
  const location = {
    pathname: '/admin/products'
  }
  const redirectToProducts = () => {
    history.replace(location);
    redirecting();
  }
  return (
    <div>
      <div className={classes.searchWrapper}>
        <div className={classes.manager}>
          <Input
            id="searchText"
            placeholder="Search"
            onChange={valueChange}
            onClick={handleClickOptions}
            className={classes.buttonLink}
            value={searchText}
          >
            <span>6</span>
            <Hidden mdUp implementation="css">
              <p onClick={handleCloseOptions} className={classes.linkText}>
                Options
              </p>
            </Hidden>
          </Input>
          <Poppers
            open={Boolean(openOptions)}
            anchorEl={openOptions}
            transition
            disablePortal
            className={
              classNames({ [classes.popperClose]: !openOptions }) +
              " " +
              classes.popperNav
            }
          >
            {({ TransitionProps, placement }) => (
              <Grow
                {...TransitionProps}
                id="notification-menu-list-grow"
                style={{
                  transformOrigin:
                    placement === "bottom" ? "center top" : "center bottom",
                }}
              >
                <Paper>
                  <ClickAwayListener onClickAway={handleCloseOptions}>
                    <ListOptions></ListOptions>
                  </ClickAwayListener>
                </Paper>
              </Grow>
            )}
          </Poppers>
        </div>
        <Button
          color="white"
          aria-label="edit"
          justIcon
          round
          onClick={redirectToProducts}
        >
          <Search />
        </Button>
      </div>
      <Link to="/admin/shoppingCart"><Button
        color={window.innerWidth > 959 ? "transparent" : "white"}
        justIcon={window.innerWidth > 959}
        simple={!(window.innerWidth > 959)}
        aria-label="ShoppingCart"
        className={classes.buttonLink}
      >
        <ShoppingCart className={classes.icons} />
        <Hidden mdUp implementation="css">
          <p className={classes.linkText}>Carrito de compras</p>
          <p className={classes.linkText}>ShoppingCart</p>
        </Hidden>
      </Button>
      </Link>
      <div className={classes.manager}>
        <Button
          color={window.innerWidth > 959 ? "transparent" : "white"}
          justIcon={window.innerWidth > 959}
          simple={!(window.innerWidth > 959)}
          aria-owns={openNotification ? "notification-menu-list-grow" : null}
          aria-haspopup="true"
          onClick={handleClickNotification}
          className={classes.buttonLink}
        >
          <Notifications className={classes.icons} />
          <span className={classes.notifications}>5</span>
          <Hidden mdUp implementation="css">
            <p onClick={handleCloseNotification} className={classes.linkText}>
              Notification
            </p>
          </Hidden>
        </Button>
        <Poppers
          open={Boolean(openNotification)}
          anchorEl={openNotification}
          transition
          disablePortal
          className={
            classNames({ [classes.popperClose]: !openNotification }) +
            " " +
            classes.popperNav
          }
        >
          
          {({ TransitionProps, placement }) => (
            <Grow
              {...TransitionProps}
              id="notification-menu-list-grow"
              style={{
                transformOrigin:
                  placement === "bottom" ? "center top" : "center bottom",
              }}
            >
              <Paper>
                <ClickAwayListener onClickAway={handleCloseNotification}>
                  <MenuList role="menu">
                    <MenuItem
                      onClick={handleCloseNotification}
                      className={classes.dropdownItem}
                    >
                      Mike John responded to your email
                    </MenuItem>
                    <MenuItem
                      onClick={handleCloseNotification}
                      className={classes.dropdownItem}
                    >
                      You have 5 new tasks
                    </MenuItem>
                    <MenuItem
                      onClick={handleCloseNotification}
                      className={classes.dropdownItem}
                    >
                      You{"'"}re now friend with Andrew
                    </MenuItem>
                    <MenuItem
                      onClick={handleCloseNotification}
                      className={classes.dropdownItem}
                    >
                      Another Notification
                    </MenuItem>
                    <MenuItem
                      onClick={handleCloseNotification}
                      className={classes.dropdownItem}
                    >
                      Another One
                    </MenuItem>
                  </MenuList>
                </ClickAwayListener>
              </Paper>
            </Grow>
          )}
        </Poppers>
      </div>
      <div className={classes.manager}>
        <Button
          color={window.innerWidth > 959 ? "transparent" : "white"}
          justIcon={window.innerWidth > 959}
          simple={!(window.innerWidth > 959)}
          aria-owns={openProfile ? "profile-menu-list-grow" : null}
          aria-haspopup="true"
          onClick={handleClickProfile}
          className={classes.buttonLink}
        >
          <Person className={classes.icons} />
          <Hidden mdUp implementation="css">
            <p className={classes.linkText}>Profile</p>
          </Hidden>
        </Button>
        <Poppers
          open={Boolean(openProfile)}
          anchorEl={openProfile}
          transition
          disablePortal
          className={
            classNames({ [classes.popperClose]: !openProfile }) +
            " " +
            classes.popperNav
          }
        >
          {({ TransitionProps, placement }) => (
            <Grow
              {...TransitionProps}
              id="profile-menu-list-grow"
              style={{
                transformOrigin:
                  placement === "bottom" ? "center top" : "center bottom",
              }}
            >
              <Paper>
                <ClickAwayListener onClickAway={handleCloseProfile}>
                  <MenuList role="menu">
                    <MenuItem
                      onClick={handleCloseProfile}
                      className={classes.dropdownItem}
                    >
                      Profile
                    </MenuItem>
                    <MenuItem
                      onClick={handleCloseProfile}
                      className={classes.dropdownItem}
                    >
                      Settings
                    </MenuItem>
                    <Divider light />
                    <MenuItem
                      onClick={handleCloseProfile}
                      className={classes.dropdownItem}
                    >
                      Logout
                    </MenuItem>
                  </MenuList>
                </ClickAwayListener>
              </Paper>
            </Grow>
          )}
        </Poppers>
      </div>
    </div>
  );
}

const redirecting = () => {
  window.location.reload();
}
