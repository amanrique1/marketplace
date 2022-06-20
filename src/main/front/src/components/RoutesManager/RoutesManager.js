import React, { useEffect } from "react";
import { createBrowserHistory } from "history";

const RoutesMap = () => {
  const history = createBrowserHistory();

  useEffect(() => {
    console.log("path: " + window.location.pathname);
    history.push(window.location.pathname);
  }, []);
  return <div></div>;
};

export default RoutesMap;
