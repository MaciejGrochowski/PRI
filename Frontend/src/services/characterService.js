import axios from 'axios';
import {apiUrl, authorizationRequest, characterUrl, request} from "./util";


const characterService = {
    getAllCharacters: () => getAllCharacters(),
    getCharactersByCountAndPage: (count, page) => getCharactersByCountAndPage(count, page),
    getCountOfCharacters: () => getCountOfCharacters(),
    getCharacters: (requestBody) => getCharacters(requestBody),
    getAutocompleteFilters: () => getAutocompleteFilters(),
    getCharacterById: id => getCharacterById(id)
}

const getCharacterById = id => {
    const url = characterUrl + "/character/" + id;
    return request.get(url)

}

const getAutocompleteFilters = () => {
    const url = characterUrl + "/autocomplete/filters"
    return request.get(url)
}

const getCharacters = requestBody => {
    const url = characterUrl + "/paged";
    return authorizationRequest().get(url, {params: requestBody} )
}

const getCountOfCharacters = () => {
    const url = characterUrl + "/count";
    return request.get(url);
}

const getAllCharacters = () => {
    const url = characterUrl;
    return request.get(url);
}

const getCharactersByCountAndPage = (count, page) => {
    const url = characterUrl + "/" + count + "/" + page;
    return request.get(url);
}

export default characterService;