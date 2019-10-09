import request from '@/router/axios';
const BASE_PREFIX = '/auth'

export const getUserData = (page) => request({
    url: BASE_PREFIX+'/sysUser/findPage',
    method: 'get',
    params: page
})


export const userSave = (data) => request({
    url: BASE_PREFIX+'/sysUser/save',
    method: 'post',
    data: data
})

export const userUpdate = (data) => request({
    url: BASE_PREFIX+'/sysUser/update',
    method: 'put',
    data: data
})


export const userDelete = (params) => request({
    url: BASE_PREFIX+'/sysUser/delete',
    method: 'delete',
    params: params
})


export const getRoleData = (page) => request({
    url: BASE_PREFIX+'/sysRole/findPage',
    method: 'get',
    params: page
})


export const roleSave = (data) => request({
    url: BASE_PREFIX+'/sysRole/save',
    method: 'post',
    data: data
})

export const roleUpdate = (data) => request({
    url: BASE_PREFIX+'/sysRole/update',
    method: 'put',
    data: data
})


export const roleDelete = (params) => request({
    url: BASE_PREFIX+'/sysRole/delete',
    method: 'delete',
    params: params
})


export const menutMenuAll = (params) => request({
    url: BASE_PREFIX+'/sysMenu/getMenuAll',
    method: 'get',
    params: params
})
