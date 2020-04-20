import {careerUrl, request} from "./util";


const careerService = {
    getAllCareerNames: () => getAllCareerNames()
}

const getAllCareerNames = () => {
    const url = careerUrl + "/names/all";
    return request.get(url);
}


export default careerService;