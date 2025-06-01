import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listRewards(query) {
  return request({
    url: '/reward/rewards/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getRewards(itemId) {
  return request({
    url: '/reward/rewards/' + itemId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addRewards(data) {
  return request({
    url: '/reward/rewards',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateRewards(data) {
  return request({
    url: '/reward/rewards',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delRewards(itemId) {
  return request({
    url: '/reward/rewards/' + itemId,
    method: 'delete'
  })
}
