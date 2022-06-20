export const type = 'REMOVE_ORDER_PRODUCT';

const removeOrderProduct = product => {
  return{
    type,
    payload: product
  }  
}

export default removeOrderProduct;
