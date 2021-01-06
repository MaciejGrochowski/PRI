import {apiUrl, authorizationRequest, baseApiUrl, placeUrl, request} from "./util";


const loginService = {
    login: (username, password) => login(username, password),
    logout: (token) => logout(token),
    register: requestBody => register(requestBody),
    forgetPassword: requestBody => forgetPassword(requestBody)
}

const login = (username, password) => {
    const url = baseApiUrl + "/authenticate";
    return request.post(url, {username: username, password: password});
}

const logout = (token) => {
    const url = baseApiUrl + "/logout-user";
    return authorizationRequest().post(url, {token: token);
}

const register = requestBody => {
    const url = baseApiUrl + "/register";
    return request.post(url, requestBody);
}

const forgetPassword = requestBody => {
    const url = baseApiUrl + "/forgot-password";
    return request.post(url, requestBody);
}


export default loginService;
