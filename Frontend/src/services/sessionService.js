import {authorizationRequest, placeUrl, request, sessionUrl} from "./util";


const sessionService = {
    createSession: (name, description) => createSession(name, description),
    getSessionsByUsername: username => getSessionsByUsername(username),
    addCharactersToSession: (characterIds, selectedSessionId) => addCharactersToSession(characterIds, selectedSessionId),
    getSessionDetails: hashcode => getSessionDetails(hashcode)
}

const createSession = (name, description) => {
    const url = sessionUrl;
    return authorizationRequest().post(url, {name: name, description: description});
}

const getSessionsByUsername = username => {
    const url = sessionUrl + "/" + username;
    return authorizationRequest().get(url);
}

const addCharactersToSession = (characterIds, selectedSessionId) => {
    const url = sessionUrl + "/characters";
    return authorizationRequest().post(url, {characterIds: characterIds, selectedSessionId: selectedSessionId})
}

const getSessionDetails = hashcode => {
    const url = sessionUrl + "/details/" + hashcode;
    return authorizationRequest().get(url);
}


export default sessionService;