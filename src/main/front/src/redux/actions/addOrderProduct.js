export const type = 'ADD_ORDER_PRODUCT';

const addOrderProduct = payload => {
  return{
    type,
    payload
  }  
}

export default addOrderProduct;

