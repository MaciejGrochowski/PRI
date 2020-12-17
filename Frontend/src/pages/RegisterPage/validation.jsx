import {polishCodeErrors, textsPolish} from "../../commons/texts-pl";


//ToDo validation functions

export const validationUsername = username => {
    if(username.match("^(?![\\s\\S])") || username.match("^.{3,20}$")){
        return {errorText: "", errorState: false}
    }
    if ((username.match("^.{0,2}$"))){
        return {errorState: true, errorText: polishCodeErrors.registerErrors.usernameTooShort}
    }
    if ((username.match("^.{21,}$"))){
        return {errorState: true, errorText: polishCodeErrors.registerErrors.usernameTooLong}
    }
};


export const validationMail = mail => {
    if (mail.match("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
        || mail.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.emailNotCorrect}
    }
};

export const validationPassword = password => {
    if (password.match("^[A-Z]([a-z]?)*$") || password.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};

export const validationFacebook = facebook => {
    if (facebook.match("^[A-Z]([a-z]?)*$") || facebook.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};

export const validationDiscord = discord => {
    if (discord.match("^[A-Z]([a-z]?)*$") || discord.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};

export const validationDescription = description => {
    if (description.match("^[A-Z]([a-z]?)*$") || description.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};