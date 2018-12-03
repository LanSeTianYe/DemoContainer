--[[
接收参数并把参数返回
--]]

local keys = "keys :"
local split_char = ""
for i, v in ipairs(KEYS) do
    keys = keys .. split_char .. "[ " .. i .. " ] = " .. v
    split_char = ";"
end

local args = "args: "
split_char = ";"
for i, v in ipairs(ARGV) do
    args = args .. split_char .. "[ " .. i .. " ] = " .. v
    split_char = ";"
end

return { keys, args }
