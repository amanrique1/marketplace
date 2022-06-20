/*!

=========================================================
* Material Dashboard React - v1.9.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-react
* Copyright 2020 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/material-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
// @material-ui/icons
import Dashboard from "@material-ui/icons/Dashboard";
import Person from "@material-ui/icons/Person";
import LibraryBooks from "@material-ui/icons/LibraryBooks";
import BubbleChart from "@material-ui/icons/BubbleChart";
import LocationOn from "@material-ui/icons/LocationOn";
import Notifications from "@material-ui/icons/Notifications";
import AddIcon from '@material-ui/icons/Add';
import LocalMallIcon from '@material-ui/icons/LocalMall';
// core components/views for Admin layout
import OrdendeCompra from "views/OrdendeCompra/OrdendeCompra.js"
import DashboardPage from "views/Dashboard/Dashboard.js";
import UserProfile from "views/UserProfile/UserProfile.js";
import TableList from "views/TableList/TableList.js";
import Typography from "views/Typography/Typography.js";
import Icons from "views/Icons/Icons.js";
import Maps from "views/Maps/Maps.js";
import NotificationsPage from "views/Notifications/Notifications.js";
import AgregarProducto from "views/AgregarProducto/AgregarProducto"
import ProductList from "views/ProductList/ProductList";
import Profile from "views/Profile/Profile";
import Categorilist from "views/CategoriList/CategoriList";
import ShoppingCart from "components/Product/ShoppingCart";
// core components/views for RTL layout

const dashboardRoutes = [
  {
    path: "/dashboard",
    name: "Dashboard",
    icon: Dashboard,
    component: DashboardPage,
    layout: "/admin"
  },
  {
    path: "/shoppingCart",
    name: "Carrito de compras",
    icon: Dashboard,
    component: ShoppingCart,
    layout: "/admin"
  },
  {
    path: "/products",
    name: "Productos",
    icon: "bookmarks",
    component: ProductList,
    layout: "/admin"
  },
  {
    path: "/profile",
    name: "Profile",
    icon: Person,
    component: Profile,
    layout: "/admin"
  },
  {
    path: "/user",
    name: "User Profile",
    icon: Person,
    component: UserProfile,
    layout: "/admin"
  },
  {
    path: "/table",
    name: "Table List",
    icon: "content_paste",
    component: TableList,
    layout: "/admin"
  },
  {
    path: "/typography",
    name: "Typography",
    icon: LibraryBooks,
    component: Typography,
    layout: "/admin"
  },
  {
    path: "/icons",
    name: "Icons",
    icon: BubbleChart,
    component: Icons,
    layout: "/admin"
  },
  {
    path: "/maps",
    name: "Maps",
    icon: LocationOn,
    component: Maps,
    layout: "/admin"
  },
  {
    path: "/notifications",
    name: "Notifications",
    icon: Notifications,
    component: NotificationsPage,
    layout: "/admin"
  },
  {
    path: "/AgregarProducto",
    name: "AgregarProducto",
    icon: AddIcon,
    component: AgregarProducto,
    layout: "/admin"
  },
  {
    path: "/OrdendeCompra",
    name: "OrdendeCompra",
    icon: LocalMallIcon,
    component: OrdendeCompra,
    layout: "/admin"

  },{
    path: "/Categorias",
    name: "Categoria",
    icon: LocalMallIcon,
    component: Categorilist,
    layout: "/admin"

  }

];

export default dashboardRoutes;
