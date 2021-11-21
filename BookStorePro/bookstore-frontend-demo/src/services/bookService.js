import config from 'config';
import {postRequest, postRequest_v2} from "../utils/ajax";


export const getBooks = (data, callback) => {
    const url = `${config.apiUrl}/getBooks`;
    postRequest(url, data, callback);
};

export const getBook = (id, callback) => {
    const data = {id: id};
    const url = `${config.apiUrl}/getBook`;
    postRequest_v2(url, data, callback);
};

export const getBookImage = (id, callback) => {
    const data = {id: id};
    const url = `${config.apiUrl}/getBookImage`;
    postRequest_v2(url, data, callback);
};

export const visitTimes = (data, callback) => {
    const url = `${config.apiUrl}/visitTimes`;
    postRequest(url, data, callback);
};

export const searchDescriptions = (text, callback) => {
    const data = {text: text};
    const url = `${config.apiUrl}/searchDescriptions`;
    postRequest_v2(url, data, callback);
};

export const getByTags = (tag, callback) => {
    const data = {tag: tag};
    const url = `${config.apiUrl}/getByTags`;
    postRequest_v2(url, data, callback);
};

export const findAuthor = (name, callback) => {
    const data = {name: name};
    const url = `${config.zuulUrl}/FINDAUTHOR/FindAuthor`;
    postRequest_v2(url, data, callback);
};
