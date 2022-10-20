INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (1, '億男', '川村元氣');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (2, '異境之書：安伯托‧艾可最後人文藝術巨作', '安伯托‧艾可');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (3, '窮查理的投資哲學與選股金律', '崔恩‧葛瑞芬');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (4, '演算法星球：七天導覽行程，一次弄懂演算法', '賽巴斯提安･史帝樂');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (5, '戴美樂小姐的婚禮', '王定國');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (6, '超級預測：洞悉思考的藝術與科學，在不確定的世界預見未來優勢', ' 菲利普．泰特洛克');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (7, '貓狗的逆襲：荊棘滿途的公民之路', '大衛‧葛林姆');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (8, '我還是想你，媽媽：101個失去童年的孩子', '斯維拉娜‧亞歷塞維奇');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (9, '咖啡的科學', '崔斯坦．史蒂文森');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (10, '被討厭的勇氣：自我啟發之父「阿德勒」的教導', '岸見一郎');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (11, '魔法精油寶典：102種植物香氣的能量運用', '史考特‧康寧罕');
INSERT INTO `book` (`bookid`, `name`, `author`) VALUES (12, '天生變態：一個擁有變態大腦的天才科學家', '詹姆斯‧法隆');


INSERT INTO `member` (`id`, `e_Mail`, `usr_Name`,`usr_Pwd`) VALUES (1, '1@gmail.com', '川村元氣','pwd1');
INSERT INTO `member` (`id`, `e_Mail`, `usr_Name`,`usr_Pwd`) VALUES (2, '2@gmail.com', '安伯托‧艾可','pwd2');
INSERT INTO `member` (`id`, `e_Mail`, `usr_Name`,`usr_Pwd`) VALUES (3, '3@gmail.com', '崔恩‧葛瑞芬','pwd3');


INSERT INTO `user` (`id`, `email`, `subscription_Id`) VALUES (1, 'user1@gmail.com', 100);
INSERT INTO `channel` (`id`, `code`, `subscription_Id`) VALUES (1, 'chanelcode01', 100);
INSERT INTO `subscription` (`id`, `code`) VALUES (100,  100);
