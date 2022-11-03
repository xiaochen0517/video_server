import request from '@/utils/request'

// 查询网站用户登录信息列表
export function listUserlogin(query) {
  return request({
    url: '/mov/userlogin/list',
    method: 'get',
    params: query
  })
}

// 查询网站用户登录信息详细
export function getUserlogin(id) {
  return request({
    url: '/mov/userlogin/' + id,
    method: 'get'
  })
}

// 新增网站用户登录信息
export function addUserlogin(data) {
  return request({
    url: '/mov/userlogin',
    method: 'post',
    data: data
  })
}

// 修改网站用户登录信息
export function updateUserlogin(data) {
  return request({
    url: '/mov/userlogin',
    method: 'put',
    data: data
  })
}

// 删除网站用户登录信息
export function delUserlogin(id) {
  return request({
    url: '/mov/userlogin/' + id,
    method: 'delete'
  })
}