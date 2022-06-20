export const type = 'EDIT_ORDER_PRODUCT';

const editOrderProduct = payload => {
  return{
    type,
    payload
  }  
}

export default editOrderProduct;