import request from '@/utils/request'

// 查询岗位列表

export function listOrder(query) {
  return request({
    url: '/order/list',
    method: 'get',
    params: query
  })
}

//  
export function getOrder(query) {
  return request({
    url: '/productorders/hotmap',
    method: 'get',
    params: query
  })
}
