import { type as ADD_ORDER_PRODUCT } from "../actions/addOrderProduct";
import { type as REMOVE_ORDER_PRODUCT } from "../actions/removeOrderProduct";
import { type as EDIT_ORDER_PRODUCT } from "../actions/editOrderProduct";
import { type as FIND_ORDER_PRODUCT } from "../actions/findOrderProduct";
import { type as REMOVE_ALL_ORDER_PRODUCT } from "../actions/removeAllOrderProduct";

function orderProducts(
  state = {
    orderProducts: [],
  },
  { type, payload }
) {
  switch (type) {
    case FIND_ORDER_PRODUCT: {
      if (!payload) {
        return [];
      }
      return state.orderProducts.filter(
        (n) => n.productId === payload.productId
      );
    }
    case ADD_ORDER_PRODUCT:
      return {
        ...state,
        orderProducts: state.orderProducts.concat(payload),
      };
    case EDIT_ORDER_PRODUCT:
      return {
        ...state,
        orderProducts: state.orderProducts.map((productOrder) =>
          productOrder.productId === payload.productId
            ? {
                ...productOrder,
                productId: payload.productId,
                quantity: payload.quantity,
                unitaryValue: payload.unitaryValue,
                details: payload.details,
                currency: payload.currency,
              }
            : productOrder
        ),
      };
    case REMOVE_ORDER_PRODUCT:
      return {
        ...state,
        orderProducts: state.orderProducts.filter(
          (item) => item.productId !== payload.productId
        ),
      };
    case REMOVE_ALL_ORDER_PRODUCT:
      return {
        ...state,
        orderProducts: state.orderProducts.filter(
          (item) => item.productId === "delete_all_order_product"
        ),
      };
    default:
      return state;
  }
}

export default orderProducts;
