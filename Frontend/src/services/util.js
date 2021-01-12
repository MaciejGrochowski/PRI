import axios from 'axios';
import jwt from 'jsonwebtoken';
import Cookie from "js-cookie";



export const request = axios.create({
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

export const isValidToken = token => {
    let tokenInfo = getInfoFromToken(token);
    if(tokenInfo===null) return false;
    const expSec = tokenInfo.exp;
    return expSec*1000 > Date.now();

};

export const logoutCookie = () => {
    Cookie.remove("token");
}

export const getToken = () =>  Cookie.get("token") ? JSON.parse(Cookie.get("token")).token : "";

export const authorizationRequest = () => {
    let token = getToken();

    if(isValidToken(token)){
    return axios.create({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    })
    }
    else return request;

};

export const getInfoFromToken = token => {
    return jwt.decode(token);
}


export const baseApiUrl = "https://janietakiork.projektstudencki.pl:8443/PRI-0.0.1-SNAPSHOT/";
// export const baseApiUrl = "http://localhost:8080";


export const localApiUrl = baseApiUrl + "/app";


export const apiUrl = localApiUrl;

export const characterUrl = apiUrl + "/characters";

export const careerUrl = apiUrl + "/careers";

export const placeUrl = apiUrl + "/places";

export const userUrl = apiUrl + "/users"

export const historyUrl = apiUrl + "/histories"

export const generatorUrl = apiUrl + "/generator";

export const sessionUrl = apiUrl + "/sessions"