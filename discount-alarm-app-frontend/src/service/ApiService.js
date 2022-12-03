import {API_BASE_URL} from "../api-config";

export function call(api, method, request) {
  let options = {
    headers: new Headers({
      "Content-Type": "application/json",
    }),
    url: API_BASE_URL + api,
    method: method,
  };
  if (request) {
    options.body = JSON.stringify(request);
  }
  return fetch(options.url, options).then((response) => {
    if (response.status === 200 || response.status === 201) {
      return response.json();
    }
  }).catch((error) => {
    console.log("http error");
  });
}