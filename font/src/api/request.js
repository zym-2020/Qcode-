import { get, post, patch, del } from './axios';

export async function getAllStudents() {
    return await get(`/student/getAllStudents`)
}

export async function insertGeometry(jsonData) {
    return await post(`/geometry/insertGeometry`, jsonData)
}

export async function insertMultipleGeometry(jsonData) {
    return await post(`/geometry/insertMultipleGeometry`, jsonData)
}

export async function getAllGeometry() {
    return await get(`/geometry/getAllGeometry`)
}