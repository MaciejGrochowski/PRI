import {authorizationRequest, placeUrl, request, sessionUrl} from "./util";


const sessionService = {
    createSession: (name, description) => createSession(name, description),
    getSessionsByUsername: username => getSessionsByUsername(username),
    addCharactersToSession: (characterIds, selectedSessionId) => addCharactersToSession(characterIds, selectedSessionId),
    getSessionDetails: hashcode => getSessionDetails(hashcode),
    editSession: (hashcode, name, description) => editSession(hashcode, name, description),
    deleteCharacterFromSession: (hashcode, characterId) => deleteCharacterFromSession(hashcode, characterId),
    setGlobalVisibility: (hashcode, dataVisibility) => setGlobalVisibility(hashcode, dataVisibility)
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

const editSession = (hashcode, name, description) => {
    const url = sessionUrl;
    return authorizationRequest().put(url, {hashcode: hashcode, name: name, description: description})
}

const deleteCharacterFromSession = (hashcode, characterId) => {
    const url = sessionUrl + "/" + hashcode + "/character/" + characterId;
    return authorizationRequest().delete(url);
}

const setGlobalVisibility = (hashcode, dataVisibility) => {
    const url = sessionUrl + "/" + hashcode + "/visibility/global"
    return authorizationRequest().put(url, dataVisibility)
}


export default sessionService;