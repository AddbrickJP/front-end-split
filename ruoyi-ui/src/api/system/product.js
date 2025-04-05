import request from '@/utils/request'

// 查询岗位列表
export function testApi(query) {
  return request({
    url: '/test/abc',
    method: 'get',
    params: query
  })
}

export function listProduct(query) {
  return request({
    url: '/product/list',
    method: 'get',
    params: query
  })
}

// 获取商品类型
export function getProductType() {
  return request({
    url: '/product/counttypes',
    method: 'get'
  })
}

// 获取顶级列表
export function getTopList() {
  return request({
    url: '/productorders/list',
    method: 'get'
  })
}

// 获取分类销量
export function getSales(query) {
  return request({
    url: '/productorders/countcategory',
    method: 'get',
    params: query
  })
}

// 获取前10销量
export function getTop10(query) {
  return request({
    url: '/productorders/top10',
    method: 'get',
    params: query
  })
}

// 获取每月销售额
export function getMonthSales(query) {
  return request({
    url: '/productorders/month',
    method: 'get',
    params: query
  })
}