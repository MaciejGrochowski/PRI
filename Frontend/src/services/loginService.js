import {apiUrl, baseApiUrl, placeUrl, request} from "./util";


const loginService = {
    login: (username, password) => login(username, password)
}

const login = (username, password) => {
    const url = baseApiUrl + "/authenticate";
    return request.post(url, {username: username, password: password});
}


export default loginService;