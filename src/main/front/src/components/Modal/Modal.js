import React,{useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import AddIcon from '@material-ui/icons/Add';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import AccessibilityNewOutlinedIcon from '@material-ui/icons/AccessibilityNewOutlined';

const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  margin: {
   
    backgroundColor:"Transparent",
    outline:"none",
    border: "0px solid",
    margin: "auto",
    padding: "4px",
    borderRadius:"40px",
    paddingTop:"10px"
  },
}));

export default function TransitionsModal() {
  const classes = useStyles();
  const [open, setOpen] = useState(false);
  const [nombre,setnombre]=useState("");


  const valueChange = (e) => {
      setnombre(e.target.value)
  }

  const Dato = () => {
    console.log(nombre)
  }

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <AddIcon onClick={handleOpen}></AddIcon>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <div className={classes.paper}>
            <h2 id="transition-modal-title">Agregar Categoria</h2>
            <TextField onChange={valueChange} id="standard-basic" label="Nombre" />
            <p id="transition-modal-description">Icono</p>
            <Button variant="outlined" onClick={""}  >
              <AccessibilityNewOutlinedIcon></AccessibilityNewOutlinedIcon>
            </Button>
            <Button variant="outlined" color="primary" onClick={Dato}>
                Agregar Categoria
            </Button>

          </div>
        </Fade>
      </Modal>
    </div>
  );
}