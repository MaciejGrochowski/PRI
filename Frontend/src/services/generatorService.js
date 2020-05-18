import {generatorUrl, request} from "./util";


const generatorService = {
    save: characterInput => save(characterInput)
}

const save = characterInput => {
    const url = generatorUrl + "/save";
    console.log(characterInput);
    return request.post(url, characterInput);
}


export default generatorService;