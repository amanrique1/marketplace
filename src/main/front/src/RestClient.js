const urlBase = "http://localhost:8080";

const readUrl = (url = "", queryParams) => {
  url =
    url.startsWith("http://") || url.startsWith("https://")
      ? url
      : `${urlBase}/${url}`;
  if (Object.keys(queryParams).length !== 0) {
    url = url.concat("?");
    Object.keys(queryParams).forEach((key) => {
      url = url.concat(key + "=" + queryParams[key] + "&");
    });
    url = url.slice(0, -1);
  }

  return url;
};

const get = (url = "", headers = {}, queryParams = {}, refresh = false) => {
  var data = {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: "bearer " + localStorage.getItem("token"),
      ...headers,
    },
  };
  console.log(
    "URL: " +
      url +
      " HEADERS: " +
      JSON.stringify(headers) +
      " QUERY PARAMS: " +
      JSON.stringify(queryParams) +
      " DATA: " +
      JSON.stringify(data)
  );

  return fetch(readUrl(url, queryParams), data).then((response) => {
    if (
      response.code === 401 &&
      refresh === false &&
      url.includes("iniciarSesion") === false
    ) {
      get("/auth/RefescarSesion", {
        Authorization: +localStorage.getItem("refreshToken"),
      })
        .then((response) => response.json())
        .then((json) => {
          localStorage.setItem("refreshToken", json.refreshToken);
          localStorage.setItem("token", json.token);
        })
        .then(get(url, headers, {}, true));
    } else {
      return response.json();
    }
  });
};

const post = (
  url = "",
  body = {},
  headers = {},
  queryParams = {},
  refresh = false
) => {
  var data = {
    method: "POST",
    body: JSON.stringify(body),
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: !url.includes("iniciarSesion")
        ? "bearer " + localStorage.getItem("token")
        : "",
      ...headers,
    },
  };
  console.log(
    "URL: " +
      url +
      " HEADERS: " +
      JSON.stringify(headers) +
      " QUERY PARAMS: " +
      JSON.stringify(queryParams) +
      " DATA: " +
      JSON.stringify(data)
  );

  return fetch(readUrl(url, queryParams), data).then((response) => {
    console.log("Response code " + response.status);
    if (
      response.status === 401 &&
      url.includes("iniciarSesion") === false &&
      refresh === false
    ) {
      console.log("Fallo token post");
      get("/auth/RefrescarSesion", {
        Authorization: +localStorage.getItem("refreshToken"),
      })
        .then((response) => response.json())
        .then((json) => {
          localStorage.setItem("refreshToken", json.refreshToken);
          localStorage.setItem("token", json.token);
        })
        .then(post(url, body, headers, {}));
    } else {
      return response;
    }
  });
};

const put = (url = "", body = {}, headers = {}, queryParams = {}) =>
  fetch(readUrl(url, queryParams), {
    method: "PUT",
    body: JSON.stringify(body),
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: "bearer " + localStorage.getItem("token"),
      ...headers,
    },
  }).then((response) => {
    console.log("Response code " + response.status);
    if (response.status === 401) {
      console.log("Fallo token post");
      get("/auth/RefrescarSesion", {
        Authorization: +localStorage.getItem("refreshToken"),
      })
        .then((response) => response.json())
        .then((json) => {
          localStorage.setItem("refreshToken", json.refreshToken);
          localStorage.setItem("token", json.token);
        })
        .then(put(url, body, headers, {}));
    }
  });

export default {
  get,
  post,
  put,
};
