import axios from 'axios';
import {apiUrl, characterUrl, request} from "./util";


const characterService = {
    getAllCharacters: () => getAllCharacters(),
    getCharactersByCountAndPage: (count, page) => getCharactersByCountAndPage(count, page),
    getCountOfCharacters: () => getCountOfCharacters(),
    getCharacters: (requestBody) => getCharacters(requestBody)
}

const getCharacters = requestBody => {
    const url = characterUrl + "/paged";
    return request.post(url, requestBody )

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