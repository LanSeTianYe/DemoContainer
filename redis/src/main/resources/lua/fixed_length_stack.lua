local stack_name = KEYS[1]
local stack_length = tonumber(ARGV[1])
local value = ARGV[2]

if nil == stack_name then
    return redis.error_reply("stack key is illegal")
end

if nil == stack_length then
    return redis.error_reply("stack length illegal")
end

if nil == value then
    return redis.error_reply("value is illegal")
end

local response
if (redis.call("LLEN", stack_name) >= stack_length) then
    response = redis.call("RPOP", stack_name)
    response = redis.call("LPUSH", stack_name, value)
else
    response = redis.call("LPUSH", stack_name, value)
end

return redis.status_reply(value)