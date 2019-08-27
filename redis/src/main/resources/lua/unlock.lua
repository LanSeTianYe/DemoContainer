--[[分布式锁,释放锁实现，如果key不存在则释放成功--]]

--[[参数--]]
local key_name = KEYS[1]
local key_value = ARGV[1]

--[[如果key已经不存在则,释放成功--]]
local exists = redis.call("EXISTS", KEYS[1])
if 0 == exists then
    return 1
end

--[[如果key存在,比较当前值和传递过来的keyId是否一致
    一致删除key.
    不一致则不做任何操作,此时也释放成功
--]]
local get_result = redis.call("GET", KEYS[1])
if key_value == get_result then
    redis.call("DEL", KEYS[1])
end

return 1
