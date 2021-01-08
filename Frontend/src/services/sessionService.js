import {authorizationRequest, placeUrl, request, sessionUrl} from "./util";


const sessionService = {
    createSession: (name, description) => createSession(name, description),
    getSessionsByUsername: username => getSessionsByUsername(username)
}

const createSession = (name, description) => {
    const url = sessionUrl;
    return authorizationRequest().post(url, {name: name, description: description});
}

const getSessionsByUsername = username => {
    const url = sessionUrl + "/" + username;
    return request.get(url);
}


export default sessionService;