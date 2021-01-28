import {CharacterAttribute} from "../../enums/CharacterAttribute";

export const mapTalentsArrayToString = talents => {
    let output = ""
    talents = talents.map(s => s.name)
    for (const i in talents) {
        output = output + talents[i] + ",";
    }
    return output.substring(0, output.length - 1);
}

export const mapSkillsArrayToString = skills => {
    let output = ""
    skills = skills.map(s => s.name + " +" + s.level)
    for (const i in skills) {
        output = output + skills[i] + ",";
    }
    return output.substring(0, output.length - 1);
}

export const generateOneAttributeSuccessHandler = (attrName, response) => {
    if (attrName === CharacterAttribute.BIRTHPLACE.name) return {birthPlace: response.data.birthPlace}
    if (attrName === CharacterAttribute.RACE.name) return {race: response.data.race}
    if (attrName === CharacterAttribute.SEX.name) return {sex: response.data.sex}
    if (attrName === CharacterAttribute.SURNAME.name) return {surname: response.data.surname}
    if (attrName === CharacterAttribute.NAME.name) return {name: response.data.name}
    if (attrName === CharacterAttribute.BASESTATS.name) return {
        baseWeaponSkills: response.data.baseWeaponSkills, baseBallisticSkills: response.data.baseBallisticSkills,
        baseStrength: response.data.baseStrength, baseToughness: response.data.baseToughness,
        baseAgility: response.data.baseAgility, baseIntelligence: response.data.baseIntelligence,
        baseWillPower: response.data.baseWillPower, baseFellowship: response.data.baseFellowship,
        baseAttacks: response.data.baseAttacks, baseWounds: response.data.baseWounds,
        baseMovement: response.data.baseMovement, baseMagic: response.data.baseMagic
    }
    if (attrName === CharacterAttribute.HEIGHT.name) return {height: response.data.height}
    if (attrName === CharacterAttribute.WEIGHT.name) return {weight: response.data.weight}
    if (attrName === CharacterAttribute.EYECOLOR.name) return {eyeColor: response.data.eyeColor}
    if (attrName === CharacterAttribute.HAIRCOLOR.name) return {hairColor: response.data.hairColor}
    if (attrName === CharacterAttribute.BIRTHDATE.name) return {
        dayOfBirth: response.data.dayOfBirth,
        yearOfBirth: response.data.yearOfBird,
        monthOfBirth: response.data.monthOfBirth
    }
    if (attrName === CharacterAttribute.DOMINATINGEMOTIONS.name) return {dominatingEmotions: response.data.dominatingEmotions}
    if (attrName === CharacterAttribute.PREDICTION.name) return {prediction: response.data.prediction}
    if (attrName === CharacterAttribute.CURRENTCAREER.name) return {previousCareers: response.data.previousCareers, currentCareer: response.data.currentCareer}
    if (attrName === CharacterAttribute.ENDSTATS.name) return {endWeaponSkills: response.data.endWeaponSkills, endBallisticSkills: response.data.endBallisticSkills,
        endStrength: response.data.endStrength, endToughness: response.data.endToughness,
        endAgility: response.data.endAgility, endIntelligence: response.data.endIntelligence,
        endWillPower: response.data.endWillPower, endFellowship: response.data.endFellowship,
        endAttacks: response.data.endAttacks, endWounds: response.data.endWounds,
        endMovement: response.data.endMovement, endMagic: response.data.endMagic,
    }
    if (attrName === CharacterAttribute.LIVEPLACE.name) return {livePlace: response.data.livePlace}
    if (attrName === CharacterAttribute.APPERANCE.name) return {apperances: response.data.apperance}
    if (attrName === CharacterAttribute.PERSONALITY.name) return {personalities: response.data.personality}
    if (attrName === CharacterAttribute.TALENTS.name) return {talents: mapTalentsArrayToString(response.data.talents)}
    if (attrName === CharacterAttribute.SKILLS.name) return {skills: mapSkillsArrayToString(response.data.skills)}
    if (attrName === CharacterAttribute.RELIGION.name) return {religion: response.data.religion}
}

export const mapArrayToStringWithoutSpaces = array => {
    let str = ""
    for(let s of array){
        str += s + ","
    }
    return str.substring(0, str.length-1);
}

export const mapFilterArrayToString = (array, options) => {
    let string = ""
    for (const element in array) {
        let name;
        name = array[element]; //ToDo prawdopodobnie jest lepsza metoda, ale wymaga analizy
        string = string + name + ", "
    }
    return string.substring(0, string.length - 2);
}

export const fullRandomGenerateSuccessHandler = response => {
    return {
        name: response.data.name,
        surname: response.data.surname,
        weight: response.data.weight,
        height: response.data.height,
        dayOfBirth: response.data.dayOfBirth,
        yearOfBirth: response.data.yearOfBird,
        birthPlace: response.data.birthPlace,
        livePlace: response.data.livePlace,
        currentCareer: response.data.currentCareer,
        race: response.data.race,
        sex: response.data.sex,
        monthOfBirth: response.data.monthOfBirth,
        eyeColor: response.data.eyeColor,
        hairColor: response.data.hairColor,
        personalities: response.data.personality,
        apperances: response.data.apperance,
        dominatingEmotions: response.data.dominatingEmotions,
        religion: response.data.religion,
        prediction: response.data.prediction,
        previousCareers: response.data.previousCareers,
        skills: mapSkillsArrayToString(response.data.skills),
        talents: mapTalentsArrayToString(response.data.talents),
        endWeaponSkills: response.data.endWeaponSkills, endBallisticSkills: response.data.endBallisticSkills,
        endStrength: response.data.endStrength, endToughness: response.data.endToughness,
        endAgility: response.data.endAgility, endIntelligence: response.data.endIntelligence,
        endWillPower: response.data.endWillPower, endFellowship: response.data.endFellowship,
        endAttacks: response.data.endAttacks, endWounds: response.data.endWounds,
        endMovement: response.data.endMovement, endMagic: response.data.endMagic,
        baseWeaponSkills: response.data.baseWeaponSkills, baseBallisticSkills: response.data.baseBallisticSkills,
        baseStrength: response.data.baseStrength, baseToughness: response.data.baseToughness,
        baseAgility: response.data.baseAgility, baseIntelligence: response.data.baseIntelligence,
        baseWillPower: response.data.baseWillPower, baseFellowship: response.data.baseFellowship,
        baseAttacks: response.data.baseAttacks, baseWounds: response.data.baseWounds,
        baseMovement: response.data.baseMovement, baseMagic: response.data.baseMagic
    }
}