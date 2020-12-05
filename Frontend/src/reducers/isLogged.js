export const isLogged = (state = false, action) => { // (1)
    switch (action.type) { // (2)
        case 'LOGIN_CHANGE':
            return action.isLogged;
        default:
            return state
    }
}