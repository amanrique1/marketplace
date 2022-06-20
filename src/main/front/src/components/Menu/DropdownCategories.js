import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";

const useStyles = makeStyles((theme) => ({
  button: {
    display: "block",
    marginTop: theme.spacing(2),
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
}));

export default function ControlledOpenSelect(props) {
  const classes = useStyles();
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(-1);
  const { selectFunction } = props;

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

  const handleChange = (event) => {
    setSelectedCategory(event.target.value);
    selectFunction(event.target.value);
  };

  return (
    <div>
      <FormControl
        className={classes.formControl}
        style={{ marginTop: "27px", width: "230px" }}
      >
        <InputLabel id="demo-controlled-open-select-label">
          Categoria
        </InputLabel>
        <Select
          labelId="demo-controlled-open-select-label"
          id="demo-controlled-open-select"
          value={selectedCategory}
          onChange={handleChange}
        >
          <MenuItem value={-1}>none</MenuItem>
          {categories != null && categories.length > 0 ? (
            categories.map((item) => (
              <MenuItem key={item.id} value={item.id}>
                {item.name}
              </MenuItem>
            ))
          ) : (
            <MenuItem>no existe dato</MenuItem>
          )}
        </Select>
      </FormControl>
    </div>
  );
}
