import {apiUrl, authorizationRequest, baseApiUrl, placeUrl, request} from "./util";
import {has} from "immutable";


const loginService = {
    login: (username, password) => login(username, password),
    logout: () => logout(),
    register: requestBody => register(requestBody),
    forgetPassword: requestBody => forgetPassword(requestBody),
    activateAccount: (username, uuid) => activateAccount(username, uuid),
    changePassword: (username, hashcode, newPassword) => changePassword(username, hashcode, newPassword)
}

const login = (username, password) => {
    const url = baseApiUrl + "/authenticate";
    return request.post(url, {username: username, password: password});
}

const logout = (token) => {
    const url = baseApiUrl + "/logout-user";
    return authorizationRequest().post(url, {token: token});
}

const register = requestBody => {
    const url = baseApiUrl + "/register";
    return request.post(url, requestBody);
}

const forgetPassword = requestBody => {
    const url = baseApiUrl + "/forgot-password";
    return request.post(url, requestBody);
}

const activateAccount = (username, uuid) => {
    const url = baseApiUrl + "/activate-user/" + username + "/" + uuid;
    return request.get(url);
}

const changePassword = (username, hashcode, newPassword) => {
    const url = baseApiUrl + "/change-password";
    return request.post(url, {username: username, hashcode: hashcode, newPassword: newPassword})
}

export default loginService;