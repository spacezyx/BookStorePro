import config from 'config';
import {postRequest} from "../utils/ajax";


//与后端交互 order相关
export const addOrder = (data,callback) => {
    const url = `${config.apiUrl}/addOrder`;
    postRequest(url, data, callback);
};


export const getOrder = (data,callback) => {
    const url = `${config.apiUrl}/getOrder`;
    postRequest(url,data, callback);
};