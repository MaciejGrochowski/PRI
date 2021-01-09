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
    else {
        return {errorState: true, errorText: textsPolish.registerTooltipTexts.username}
    }
};


export const validationMail = mail => {
    if (mail.match(("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
        || mail.match("^(?![\\s\\S])"))
        && mail.match("^.{2,200}$")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.emailNotCorrect}
    }
};

export const validationPassword = password => {
    if ((password.match("^(?![\\s\\S])"))){
        return {errorState: false, errorText: ""}
    }
    if ((password.match("^.{0,2}$"))){
        return {errorState: true, errorText: polishCodeErrors.registerErrors.passwordTooShort}
    }
    if ((password.match("^.{21,}$"))){
        return {errorState: true, errorText: polishCodeErrors.registerErrors.passwordTooLong}
    }
    if (password.match("^([a-zA-Z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*)*$")) {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.passwordMissingNumber}
    }
    if (password.match("^([a-zA-Z]*\\d*)*$")) {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.passwordMissingSpecialSign}
    }
    if (password.match("^([a-z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*\\d*)*$")) {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.passwordMissingCapitalLetter}
    }
    else {
        return {errorState: false, errorText: ""}
    }
};

export const validationFacebook = facebook => {
    if (facebook.match("https://www.facebook.com/.*") || facebook.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.facebookNotCorrect}
    }
};

export const validationDiscord = discord => {
    if (discord.match("^.[^#@:`']{1,31}#\\d\\d\\d\\d$") || discord.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.discordNotCorrect}
    }
};

export const validationDescription = description => {
    if (description.match("^.{0,1000}$") || description.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: polishCodeErrors.registerErrors.descriptionTooLong}
    }
};