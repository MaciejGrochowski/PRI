export const validationName = name => {
    if (name.match("^[A-Z]([a-z]?)*$") || name.match("^(?![\\s\\S])")) {
        return {errorText: "", errorState: false}
    } else {
        return {errorState: true, errorText: "Imię powinno zaczynać się z wielkiej litery i zaweierać litery A-Z."} //ToDo teksty do text-polish
    }
};

export const validationSurname = surname => {
    //ToDo body of func
}

//ToDo validation other textfields