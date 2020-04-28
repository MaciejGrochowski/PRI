import {placeUrl, request} from "./util";


const placeService = {
    getAllPlaceNames: () => getAllPlaceNames()
}

const getAllPlaceNames = () => {
    const url = placeUrl + "/names/all";
    return request.get(url);
}


export default placeService;