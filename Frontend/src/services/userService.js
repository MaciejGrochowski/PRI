import {userUrl, request} from "./util";


const userService = {
    getUserByUsername: username => getUserByUsername(username)
}

const getUserByUsername = username => {
    const url = userUrl + "/user/" + username;
    return request.get(url);
}


export default userService;