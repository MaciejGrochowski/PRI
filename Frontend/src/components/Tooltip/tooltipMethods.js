

export function actualPlaceText(showRace, showProfession, showSex, showBirthPlace, showReligion, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showProfession) textContent += "profesja, ";
    if(showSex) textContent += "płeć, ";
    if(showBirthPlace) textContent += "miejsce urodzenia, ";
    if(showReligion) textContent += "religia, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}

export function nameText(showRace, showSex, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showSex) textContent += "płeć, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}

export function birthYearText(showBirthYear, content){
    let textContent = content
    if(showBirthYear) return textContent += "rasa."
}

export function raceText(showRace, content){
    let textContent = content
    if(showRace) return textContent += "miejsce urodzenia."
}

export function skillText(showRace, showProfession, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showProfession) textContent += "profesja, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}

export function emotionText(showRace, showBirthYear, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showBirthYear) textContent += "rok urodzenia, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}

export function religionText(showRace, showProfession, showSex, showBirthPlace, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showProfession) textContent += "profesja, ";
    if(showSex) textContent += "płeć, ";
    if(showBirthPlace) textContent += "miejsce urodzenia, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}

export function characterText(showRace, showProfession, showBirthYear, showReligion, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showProfession) textContent += "profesja, ";
    if(showBirthYear) textContent += "rok urodzenia, ";
    if(showReligion) textContent += "religia, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}

export function professionText(showRace, showSex, showBirthPlace, showBasicStats, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showSex) textContent += "płeć, ";
    if(showBirthPlace) textContent += "miejsce urodzenia, ";
    if(showBasicStats) textContent += "bazowe statystki bojowe, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}

export function appearanceText(showRace, showSex, showProfession, showBirthYear, showWeight, showHeight, showReligion, content){
    let textContent = content
    if(showRace) textContent += "rasa, ";
    if(showSex) textContent += "płeć, ";
    if(showProfession) textContent += "profesja, ";
    if(showBirthYear) textContent += "miejsce urodzenia, ";
    if(showWeight) textContent += "wzrost, ";
    if(showHeight) textContent += "waga, ";
    if(showReligion) textContent += "religia, ";
    textContent = textContent.substring(0,textContent.length-2)
    textContent += "."
    return textContent
}