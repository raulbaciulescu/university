import {BASE_URL} from "./constants";

function status(response) {
    console.log("response status " + response.status);
    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response);
    } else {
        return Promise.reject(new Error(response.statusText));
    }
}

function json(response) {
    return response.json()
}

export function addFlight(flight) {
    console.log("inainte de fetch post" + JSON.stringify(flight));

    let headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/json");

    let antet = {
        method: "POST",
        headers: headers,
        mode: "cors",
        body: JSON.stringify(flight)
    }

    return fetch(BASE_URL, antet)
        .then(status)
        .then(response => {
            return response.text;
        })
        .catch(error => {
            console.log("Request failed ", error);
            return new Promise.reject(error);
        })
}

export function updateFlight(flight) {
    console.log("inainte de fetch put" + JSON.stringify(flight));

    let headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/json");

    let antet = {
        method: "PUT",
        headers: headers,
        mode: "cors",
        body: JSON.stringify(flight)
    }
    const flightUpdateUrl = BASE_URL + "/" + flight.id;
    console.log("URL pentru update   " + flightUpdateUrl)
    return fetch(flightUpdateUrl, antet)
        .then(status)
        .then(response => {
            return response.text;
        })
        .catch(error => {
            console.log("Request failed ", error);
            return new Promise.reject(error);
        })
}

export function deleteFlight(id) {
    console.log("inainte de fetch delete")
    let headers = new Headers();
    headers.append("Accept", "application/json");

    let antet = {
        method: "DELETE",
        headers: headers,
        mode: "cors"
    };

    const flightDeleteUrl = BASE_URL + "/" + id;
    console.log("URL pentru delete   " + flightDeleteUrl)
    return fetch(flightDeleteUrl, antet)
        .then(status)
        .then(response => {
            console.log("Delete status " + response.status);
            return response.text();
        })
        .catch(e => {
            console.log("error "+e);
            return Promise.reject(e);
        });
}

export function getFlights() {
    let headers = new Headers();
    headers.append("Accept", "application/json");
    let init = {
        method: "GET",
        headers: headers,
        mode: "cors"
    };
    let request = new Request(BASE_URL, init);

    console.log('Inainte de fetch GET pentru ' + BASE_URL)

    return fetch(request)
        .then(status)
        .then(json)
        .then(data => {
            console.log("Request succeeded with JSON response", data);
            return data;
        })
        .catch(error => {
            console.log("Request failed", error);
            return Promise.reject(error);
        });
}