import {apiUrl, authorizationRequest, baseApiUrl, placeUrl, request} from "./util";


const loginService = {
    login: (username, password) => login(username, password),
    logout: () => logout(),
    register: requestBody => register(requestBody)
}

const login = (username, password) => {
    const url = baseApiUrl + "/authenticate";
    return request.post(url, {username: username, password: password});
}

const logout = () => {
    const url = baseApiUrl + "/logout-user";
    return authorizationRequest().post(url);
}

const register = requestBody => {
    const url = baseApiUrl + "/register";
    return request.post(url, requestBody);
}


export default loginService;