import {textsPolish} from "../../commons/texts-pl"

export const validationName = name => {
    if (name.match("^[A-Z]([a-z]?)*$") || name.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: textsPolish.nameValidationText}
    }
};

export const validationSurname = surname => {
    if (surname.match("^[A-Z][a-z]+$|^von [A-Z][a-z]+$|^Von [A-Z][a-z]+$") || surname.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: textsPolish.surnameValidationText}
    }
};

export const validationDayOfBorn = day => {
        if (day.match("^[1-2][0-9]$|^[1-9]$|^3[0-4]$") || day.match("^(?![\\s\\S])")) {
            return {errorText: "", errorState: false}
        } else {
            return {errorState: true, errorText: textsPolish.dayOfBornValidationText}
        }
};

export const validationYearOfBorn = year => {
    if (year.match("^0$|^[1-2][0-9]{0,3}$|^[3-9][0-9]{0,2}$|^3000$") || year.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: textsPolish.yearOfBornValidationText}
    }
};

export const validationHeight = height => {
    if (height.match("^[1-2][0-9]{2,2}$|^[5-9][0-9]{1,1}$|^300$") || height.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: textsPolish.heightValidationText}
    }
};

export const validationWeight = weight => {
    if (weight.match("^[1-7][0-9]{2,2}$|^[1-9][0-9]{1,1}$|^800$") || weight.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: textsPolish.weightValidationText}
    }
};
