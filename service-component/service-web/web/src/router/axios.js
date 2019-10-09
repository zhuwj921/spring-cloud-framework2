/**
 * 全站http配置
 *
 * header参数说明
 * serialize是否开启form表单提交
 * isToken是否需要token
 */
import axios from 'axios'
import store from '@/store/';
import router from '@/router/router'
import {baseUrl} from '@/config/env'
import { serialize } from '@/util/util'
import { getToken } from '@/util/auth'
import { Message } from 'element-ui'
import website from '@/const/website';
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
const service = axios.create({
	baseURL:baseUrl, // api的base_url
	timeout: 60000 // 请求超时时间
})
//返回其他状态吗
service.defaults.validateStatus = function(status) {
    return status >= 200 && status <= 500; // 默认的
};
//跨域请求，允许保存cookie
service.defaults.withCredentials = true;
// NProgress Configuration
NProgress.configure({
    showSpinner: false
});
//HTTPrequest拦截
service.interceptors.request.use(config => {

    NProgress.start() // start progress bar
    const isToken = (config.data || {}).isToken === false
    if (getToken() && !isToken) {
        config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带token--['Authorization']为自定义key 请根据实际情况自行修改
    }
    //headers中配置serialize为true开启序列化
    // if (config.methods === 'post' && config.headers.serialize) {
    //     config.data = serialize(config.data);
    //     delete config.data.serialize;
    // }
    return config
}, error => {
    return Promise.reject(error)
});
//HTTPresponse拦截
service.interceptors.response.use(res => {
    NProgress.done();
    const status = Number(res.status) || 200;
    const statusWhiteList = website.statusWhiteList || [];
    const message = res.data.msg || '未知错误';
    //如果请求为200则放过，否者默认统一处理,或者在website中配置statusWhiteList白名单自行处理
    if (status !== 200 && !statusWhiteList.includes(status)) {
        Message({
            message: message,
            type: 'error'
        })
        return Promise.reject(new Error(message))
    }
    //如果是401则跳转到登录页面
    if (status === 401) store.dispatch('FedLogOut').then(() => router.push({ path: '/login' }));

    // 如果是白名单类型放入catch自行处理
    if (status !== 200) return Promise.reject(res);
    return res.data.result;
}, error => {
    console.log(error);
    NProgress.done();
    return Promise.reject(new Error(error));
})

export default service;