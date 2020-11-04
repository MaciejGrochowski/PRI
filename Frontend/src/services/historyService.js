import {historyUrl, request} from "./util";


const historyService = {
    getHistories: (requestBody) => getHistories(requestBody),
    getAutocompleteFilters: () => getAutocompleteFilters(),
    getCharactersCreatingHistory: () => getCharactersCreatingHistory()
}

const getHistories = (requestBody) => {
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