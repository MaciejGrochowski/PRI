import {historyUrl, request} from "./util";


const historyService = {
    getHistories: (requestBody) => getHistories(requestBody),
    getAutocompleteFilters: () => getAutocompleteFilters()
}

const getHistories = (requestBody) => {
    const url = historyUrl + "/paged";
    return request.get(url, {params: requestBody});
};

const getAutocompleteFilters = () => {
    const url = historyUrl + "/autocomplete/filters";
    return request.get(url);
}


export default historyService;