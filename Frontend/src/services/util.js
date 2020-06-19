import axios from 'axios';

export const request = axios.create({
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

export const localApiUrl = "http://localhost:8080/app";
// export const localApiUrl = "http://150.254.78.172:443/PRI-0.0.1-SNAPSHOT/app";

export const apiUrl = localApiUrl;

export const characterUrl = apiUrl + "/characters";

export const careerUrl = apiUrl + "/careers";

export const placeUrl = apiUrl + "/places";

export const generatorUrl = apiUrl + "/generator";