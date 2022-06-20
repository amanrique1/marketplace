import React,{useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import GridItem from 'components/Grid/GridItem';
import addImage from "assets/img/addimage.jpg"

const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        margin: theme.spacing(1),
      },
    },
    input: {
      display: 'none',
    },
}));
  

export default function UploadButtons() {
const classes = useStyles();
  const  [baseImage,setBaseImage] = useState("")
  const [img,setImg]= useState(addImage)

  const imagen=(e)=>{
    setImg("  ")
  }

  const uploadImage= async (e)=>{
      const file =e.target.files[0];
      const base64 = await convertBase64(file);
      setBaseImage(base64);

  }



  const convertBase64=(file)=>{
      return new Promise((resolve,reject)=>{

        const fileReader = new FileReader();
        fileReader.readAsDataURL(file);

        fileReader.onload= ()=>{
            resolve(fileReader.result);
        }
        
        fileReader.onerror =((error)=>
        {
            reject(error);
        })
      })

  }
  
  return (
      
   <div className="App">
    <div className={classes.root}>
      <GridItem xs={5} sm={5} md={4}>
      <img src={baseImage}  height="300px"  ></img>
      </GridItem>

      <input  type="image"  src={img} ></input>
      
      <input onChange={(e)=>{
           uploadImage(e);
           imagen(e);
       }}
        accept="image/*"
        className={classes.input}
        id="contained-button-file"
        multiple
        type="file"
      />
      
      
      <label htmlFor="contained-button-file">
        <Button variant="contained" color="secondary" component="span">
          Agregar Imagen
        </Button>
      </label>
      
      <label htmlFor="icon-button-file">
      </label>
    </div>
   </div>
  );
}