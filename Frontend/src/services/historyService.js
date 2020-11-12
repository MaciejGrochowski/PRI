import {historyUrl, request} from "./util";


const historyService = {
    getHistories: (requestBody) => getHistories(requestBody),
    getAutocompleteFilters: () => getAutocompleteFilters(),
    getCharactersCreatingHistory: () => getCharactersCreatingHistory(),
    createHistory: data => createHistory(data),
    getHistoryDetails: historyId => getHistoryDetails(historyId),
    getHistoriesByCharacter: (name, id) => getHistoriesByCharacter(name, id)


}

const getHistoriesByCharacter = (name, id) => {
    const url = historyUrl + "/character/" + name + "/" + id;
    return request.get(url);
}

const createHistory = data => {
    const url = historyUrl;
    return request.post(url, data);
}

const getHistoryDetails = historyId => {
    const url = historyUrl + "/" + historyId;
    return request.get(url)
}

const getHistories = requestBody => {
    const url = historyUrl + "/paged";
    return request.get(url, {params: requestBody});
};

const getAutocompleteFilters = () => {
    const url = historyUrl + "/autocomplete/filters";
    return request.get(url);
};

const getCharactersCreatingHistory = () => {
    const url = historyUrl + "/characterstags";
    return request.get(url);
}


export default historyService;