import {createStore, combineReducers} from 'redux';
import orderProducts from "../reducers/orderProducts";

const reducer = combineReducers({
  orderProducts
})

const store = createStore(reducer,window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

export default store;