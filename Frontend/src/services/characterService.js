import axios from 'axios';
import {apiUrl, characterUrl, request} from "./util";


const characterService = {
    getAllCharacters: () => getAllCharacters()
}

const getAllCharacters = () => {
    const url = characterUrl;
    return request.get(url);
}




export default characterService;