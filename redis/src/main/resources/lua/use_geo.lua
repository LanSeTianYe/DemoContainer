local result = {}

local location = function(name, longitude, latitude)
    return { name = name, longitude = longitude, latitude = latitude }
end

local earth = {}
local key = "china"
table.insert(earth, location("beijing", 116, 39))
table.insert(earth, location("zhengzhou", 113, 34))
table.insert(earth, location("chongqing", 106, 29))

for _, v in ipairs(earth) do
    redis.call("GEOADD", key, v.longitude, v.latitude, v.name)
end

local city_list = redis.call("ZRANGE", key, 0, -1, "WITHSCORES")
local geo_hash = redis.call("GEOHASH", "china", "zhengzhou", "chongqing", "beijing")
local geo_dist = {}
geo_dist.dist_from_beijing_to_zhengzhou = redis.call("GEODIST", key, "zhengzhou", "beijing", "km") .. " (km)"
geo_dist.dist_from_beijing_to_chongqign = redis.call("GEODIST", key, "beijing", "chongqing", "km") .. " (km)"
local geo_pos = redis.call("GEOPOS", key, "beijing", "zhengzhou", "chongqing")
local geo_radius = redis.call("GEORADIUS", key, 110, 30, 2000, "km", "WITHDIST", "WITHCOORD", "WITHHASH", "ASC")
local geo_radius_by_member = redis.call("GEORADIUSBYMEMBER", key, "beijing", 2000, "km", "ASC")

result.city_list = city_list
result.geo_hash = geo_hash
result.geo_dist = geo_dist
result.geo_pos = geo_pos
result.geo_radius = geo_radius
result.geo_radius_by_member = geo_radius_by_member

return cjson.encode(result)

