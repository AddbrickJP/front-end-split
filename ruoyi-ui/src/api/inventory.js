import request from '@/utils/request'

export function listInventory(query) {
  return request({
    url: '/inventory/list',
    method: 'get',
    params: query
  })
}

// 获取库存剩余
export function getInventory(query) {
  return request({
    url: '/productorders/stock',
    method: 'get',
    params: query
  })
}