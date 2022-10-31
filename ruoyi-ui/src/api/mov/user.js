import request from '@/utils/request'

// 查询网站用户列表
export function listUser(query) {
  return request({
    url: '/mov/user/list',
    method: 'get',
    params: query
  })
}

// 查询网站用户详细
export function getUser(id) {
  return request({
    url: '/mov/user/' + id,
    method: 'get'
  })
}

// 新增网站用户
export function addUser(data) {
  return request({
    url: '/mov/user',
    method: 'post',
    data: data
  })
}

// 修改网站用户
export function updateUser(data) {
  return request({
    url: '/mov/user',
    method: 'put',
    data: data
  })
}

// 删除网站用户
export function delUser(id) {
  return request({
    url: '/mov/user/' + id,
    method: 'delete'
  })
}
