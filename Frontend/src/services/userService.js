import {userUrl, request, authorizationRequest} from "./util";


const userService = {
    getUserByUsername: username => getUserByUsername(username),
    editProfile: profileData => editProfile(profileData)
}

const getUserByUsername = username => {
    const url = userUrl + "/user/" + username;
    return request.get(url);
}

const editProfile = profileData => {
    const url = userUrl;
    return authorizationRequest().put(url, profileData);
};


export default userService;