import axios from 'axios';

export const request = axios.create({
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

export const baseApiUrl = "http://localhost:8080";

 export const localApiUrl = baseApiUrl + "/app";
 // export const localApiUrl = "https://janietakiork.projektstudencki.pl:8443/PRI-0.0.1-SNAPSHOT/app";

//export const localApiUrl = "https://localhost:443/app"

export const apiUrl = localApiUrl;

export const characterUrl = apiUrl + "/characters";

export const careerUrl = apiUrl + "/careers";

export const placeUrl = apiUrl + "/places";

export const historyUrl = apiUrl + "/histories"

export const generatorUrl = apiUrl + "/generator";