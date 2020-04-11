import axios from 'axios';

export const request = axios.create({
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

export const localApiUrl = "http://localhost:8080/app";

export const apiUrl = localApiUrl;

export const characterUrl = apiUrl + "/characters";