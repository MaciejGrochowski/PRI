import {textsPolish} from "../../commons/texts-pl";


//ToDo validation functions

export const validationUsername = username => {
    return {errorText: "", errorState: false}
    if (username.match("^[A-Z]([a-z]?)*$") || username.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};


export const validationMail = mail => {
    return {errorText: "", errorState: false}
    if (mail.match("^[A-Z]([a-z]?)*$") || mail.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
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
    return {errorText: "", errorState: false}
    if (facebook.match("^[A-Z]([a-z]?)*$") || facebook.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};

export const validationDiscord = discord => {
    return {errorText: "", errorState: false}
    if (discord.match("^[A-Z]([a-z]?)*$") || discord.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};

export const validationDescription = description => {
    return {errorText: "", errorState: false}
    if (description.match("^[A-Z]([a-z]?)*$") || description.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "exampleError"}
    }
};