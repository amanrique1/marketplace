export const type = 'FIND_ORDER_PRODUCT';

const findOrderProduct = payload => {
  return{
    type,
    payload
  }  
}

export default findOrderProduct;

