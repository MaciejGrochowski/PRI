import {generatorUrl, request} from "./util";


const generatorService = {
    save: characterInput => save(characterInput),
    fullRandomGenerate: () => fullRandomGenerate(),
    generateOneAttribute: (name, character) => generateOneAttribute(name, character)
}

const save = characterInput => {
    const url = generatorUrl + "/save";
    return request.post(url, characterInput);
}

const fullRandomGenerate = () => {
    const  url = generatorUrl + "/details";
    return request.get(url);
}

const validInputAttributes = character => {
    for (var key in character) {
        if (character.hasOwnProperty(key)) {
            if(typeof character[key] === 'string' || character[key] instanceof String){
                character[key] = character[key].split("[").join("")
                character[key] = character[key].split("]").join("")
                // character[key] = character[key].toString().replaceAll("[", "")
                // character[key] = character[key].toString().replaceAll("]", "")
            }
        }
    }
    return character
}

const generateOneAttribute = (name, character) => {
    name = name.replace(/\s+/g, '-').toLowerCase();
    // character = encodeURI(JSON.stringify(character));
    character = validInputAttributes(character)
    const url = generatorUrl + "/attribute/" + name;
    return request.get(url, {params: character});
}

export default generatorService;