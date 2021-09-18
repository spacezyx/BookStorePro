import config from 'config';
import {postRequest} from "../utils/ajax";

//与后端交互 cart相关
export const addCart = (data,callback) => {
    const url = `${config.apiUrl}/addCart`;
    postRequest(url, data, callback);
};

export const getCarts = (data,callback) => {
    const url = `${config.apiUrl}/getCarts`;
    postRequest(url, data, callback);
};