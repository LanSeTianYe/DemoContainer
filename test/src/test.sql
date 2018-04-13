select count(1) from wx_material_news where authorized_appid = 'wx7f900e8dbdf6b939' and with_link = 1 and status = 1


select count(1) from wx_material_news wxn, wx_material_article wma where wxn.news_id = wma.material_news_id
and wxn.authorized_appid = 'wx7f900e8dbdf6b939' and wxn.with_link = 1 and wxn.status = 1 and wma.status = 1;
