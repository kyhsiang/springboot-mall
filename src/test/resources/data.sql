-- product
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('天廚川味兒麻婆豆腐醬調理包200g', 'FOOD', 'https://cs-d.ecimg.tw/items/DBAA0AA71357193/000001_1620178370.jpg', 59, 10, '特選花椒、鮮製豬絞肉、無添加防腐劑', '2022-03-01 02:41:28', '2022-03-01 02:41:32');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('《牛頭牌》原味沙茶醬(737g)', 'FOOD', 'https://cs-d.ecimg.tw/items/DBAA0OA30978540/000001_1478096465.jpg', 239, 10, '炒牛肉、炒三鮮甚至炒飯，都可以讓您美味滿桌喔！', '2022-03-19 17:00:00', '2022-03-22 18:00:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('味王-咖哩牛肉 調理包200g (24入/箱)', 'FOOD', 'https://cs-d.ecimg.tw/items/DBBY0YA9008ZT23/000001_1664764437.jpg', 1080, 10, '日本頂級咖哩', '2022-03-19 18:30:00', '2022-03-19 18:30:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('【小廚師】泰式黃咖哩雞調理包(220g/盒)', 'FOOD', 'https://cs-b.ecimg.tw/items/DBBY0YA900C0IV3/000001_1637567633.jpg', 200, 50, '製成獨特香氣的黃咖哩', '2022-03-20 09:00:00', '2022-03-24 15:00:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('NS《Ring-con:健身環大冒險》中文版', 'GAME', 'https://cs-b.ecimg.tw/items/DGCW131900B4I2B/000001_1659687190.jpg', 1790, 10, '體感操作冒險遊戲', '2022-03-20 09:20:00', '2022-03-20 09:20:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('NS《集合啦！動物森友會》中文版', 'GAME', 'https://cs-e.ecimg.tw/items/DGCW131900B4I6O/000001_1611819498.jpg', 1350, 10, '園藝、釣魚、島上探索、居家環境佈置與可愛動物交流，等著你來發掘體驗', '2022-03-20 12:30:00', '2022-03-20 12:30:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('Switch遊戲《寶可夢 朱》中文版', 'GAME', 'https://cs-b.ecimg.tw/items/DGCW131900FRC5A/000001_1669606700.jpg', 1450, 10, '《寶可夢》系列最新作!!第九世代全新的冒險', '2022-12-21 20:10:00', '2022-12-22 10:50:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('Switch遊戲《寶可夢 紫》中文版', 'GAME', 'https://cs-c.ecimg.tw/items/DGCW131900FRC5K/000001_1668995319.jpg', 1550, 10, '《寶可夢》系列最新作!!第九世代全新的冒險', '2022-12-21 23:30:00', '2022-12-22 23:30:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('透明的螺旋', 'BOOK', 'https://im1.book.com.tw/image/getImage?i=https://www.books.com.tw/img/001/093/03/0010930326.jpg&v=62cd4d5ck&w=348&h=348', 332, 20, '《偵探伽利略》全新長篇傑作', '2022-11-21 23:30:00', '2022-11-22 23:30:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('解憂雜貨店【暖心紀念版】', 'BOOK', 'https://im2.book.com.tw/image/getImage?i=https://www.books.com.tw/img/001/059/48/0010594847.jpg&v=5b618bb8k&w=348&h=348', 277, 20, '東野圭吾最令人感動落淚的作品！', '2022-10-21 23:30:00', '2022-10-22 23:30:00');

-- user
INSERT INTO user (email, password, created_date, last_modified_date) VALUES ('admin@gmail.com', '202cb962ac59075b964b07152d234b70', '2022-06-30 10:30:00', '2022-06-30 10:30:00');
INSERT INTO user (email, password, created_date, last_modified_date) VALUES ('test@gmail.com', '202cb962ac59075b964b07152d234b70', '2022-06-30 10:40:00', '2022-06-30 10:40:00');

-- order, order_item
INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date) VALUES (2, 2198, '2022-06-30 11:10:00', '2022-06-30 11:10:00');
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (1, 2, 1, 239);
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (1, 6, 1, 1350);
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (1, 9, 1, 332);
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (1, 10, 1, 277);