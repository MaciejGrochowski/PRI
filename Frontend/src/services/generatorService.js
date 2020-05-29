import {generatorUrl, request} from "./util";


const generatorService = {
    save: characterInput => save(characterInput),
    fullRandomGenerate: () => fullRandomGenerate()
}

const save = characterInput => {
    const url = generatorUrl + "/save";
    return request.post(url, characterInput);
}

const fullRandomGenerate = () => {
    const  url = generatorUrl + "/details";
    return request.get(url);
}


export default generatorService;