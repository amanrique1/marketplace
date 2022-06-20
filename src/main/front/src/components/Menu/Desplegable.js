import React , { useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';


const useStyles = makeStyles((theme) => ({
  button: {
    display: 'block',
    marginTop: theme.spacing(2),
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
    
  },
}));



export default function ControlledOpenSelect() {
  const classes = useStyles();
  const [categoria, setCategoria] = useState('');
  const [open, setOpen] = React.useState(false);
  const [categorias, setCategorias] = useState([]);
 

  useEffect(() => {
    getCategories();
 });
  
    let urlServer = 'http://localhost:8080'
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
      setCategorias(json);
      
     
    }).catch((error)=>{
      console.log(error)
    }
    )
  };
  

  const handleChange = (event) => {
    setCategoria(event.target.value);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };

  return (
    <div>
      <FormControl className={classes.formControl} style={{marginTop:"27px",width:"230px"}} >
        <InputLabel id="demo-controlled-open-select-label" >Categoria</InputLabel>
       <Select
          labelId="demo-controlled-open-select-label"
          id="demo-controlled-open-select"
          open={open}
          onClose={handleClose}
          onOpen={handleOpen}
          value={categoria}
          onChange={handleChange}
        >
     {   /*  {
           categoria.map(item=>(
           <option key="item.id">{item.nombre}</option> 
           ))
         } */}


        </Select>
      </FormControl>
    </div>
  );
}