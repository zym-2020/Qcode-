import axios from 'axios';
import sf from 'string-format';

const axiosInstance = axios.create({
    baseURL: '/example',
    timeout: 200000

});


export const get = (url, params, responseType = null, pathVariable = null) => {
    return axiosInstance.get(sf(url, pathVariable), {
        params: params,
        responseType: responseType
    });
};

export const post = (url, data, pathVariable = null) =>
    axiosInstance.post(sf(url, pathVariable), data, {
        
    });

export const put = (url, data, pathVariable = null) =>
    axiosInstance.put(sf(url, pathVariable), data, {
        
    });

export const patch = (url, data, pathVariable = null) =>
    axiosInstance.patch(sf(url, pathVariable), data, {
        
    });

export const del = (url, params, data, pathVariable = null) =>
    axiosInstance.delete(sf(url, pathVariable), {
        params: params,
        data: data,
        
    });