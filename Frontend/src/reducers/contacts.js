export const contacts = (state = false, action) => { // (1)
    switch (action.type) { // (2)
        case 'LOGIN_CHANGE':
            return action.contacts;
        default:
            return state
    }
}