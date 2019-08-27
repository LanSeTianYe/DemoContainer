--[[分布式锁,获取锁实现,设置值并设置过期时间--]]

--[[参数--]]
local key_name = KEYS[1]
local lock_id_key_name = ARGV[2]
local expire_time = ARGV[1]

--[[初始化锁ID--]]
local exists = redis.call("EXISTS", lock_id_key_name)
if 0 == exists then
    redis.call("SET", lock_id_key_name, 10000)
end

--[[获取锁，获取成功返回锁ID，获取失败返回空字符串--]]
local lock_id = redis.call("INCR", lock_id_key_name)
local set_result = redis.call("SETNX", key_name, lock_id)
if 1 == set_result then
    redis.call("EXPIRE", key_name, expire_time)
    return lock_id .. ''
else
    return ''
end