import request from '@/utils/request'

// 查询岗位列表
export function testApi(query) {
  return request({
    url: '/test/abc',
    method: 'get',
    params: query
  })
}