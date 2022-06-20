import React, { useEffect, useState } from "react";
import GridContainer from "components/Grid/GridContainer.js";
import AddCategories from "components/Category/AddCategories";
import Category from "components/Category/Category";

export default function ControlledOpenSelect(props) {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    getCategories();
  }, []);

  let urlServer = "http://localhost:8080";
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
        setCategories(json.categories);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <GridContainer>
        <AddCategories></AddCategories>
        {categories != null && categories.length > 0
          ? categories.map((item) => <Category addcategory={item}></Category>)
          : console.log("Sin dato")}
      </GridContainer>
    </div>
  );
}
