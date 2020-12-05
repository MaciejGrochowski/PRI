import {apiUrl, authorizationRequest, baseApiUrl, placeUrl, request} from "./util";


const loginService = {
    login: (username, password) => login(username, password),
    logout: () => logout()
}

const login = (username, password) => {
    const url = baseApiUrl + "/authenticate";
    return request.post(url, {username: username, password: password});
}

const logout = () => {
    const url = baseApiUrl + "/logout-user";
    return authorizationRequest().post(url);
}


export default loginService;