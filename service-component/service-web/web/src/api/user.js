import request from '@/router/axios';
const BASE_PREFIX = '/auth'
export const loginByUsername = (username, password, code, redomStr) => request({
    url: BASE_PREFIX+'/oauth/token',
	auth:{
		username : 'webapp',
		password:'webapp',
	},
    method: 'post',
    params: {
        username,
        password,
        code,
        redomStr,
		grant_type : 'password'
    }
})

export const getUserInfo = () => request({
    url: BASE_PREFIX+'/user/info',
    method: 'get'
});

export const RefeshToken = () => request({
    url: '/user/refesh',
    method: 'post'
})

export const getMenu = () => request({
    url: BASE_PREFIX+'/sysMenu/getUserMenu',
    method: 'get',
});

export const getMenuAll = () => request({
    url: '/user/getMenu',
    method: 'get',
    data: {
        type: 0
    }
});

export const getTableData = (page) => request({
    url: '/user/getTable',
    method: 'get',
    data: {
        page
    }
});

export const logout = () => request({
    url: '/user/logout',
    method: 'get'
})