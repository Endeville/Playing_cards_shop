INSERT INTO roles (id, role)
VALUES (1, 'ADMIN'),
       (2, 'CLIENT');

INSERT INTO categories(id, category)
VALUES (1, 'STANDARD'),
       (2, 'GAME'),
       (3, 'MAGIC'),
       (4, 'CARDISTRY'),
       (5, 'VINTAGE'),
       (6, 'POKER'),
       (7, 'GAFFED'),
       (8, 'MARKED');

INSERT INTO pictures(id, public_id, url)
VALUES (1, 'high_victorian_green_t27q49',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1657796050/playing_cards_project/high_victorian_green_t27q49.jpg'),
       (2, 'monarchs1_1024x1024_rltbye',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1657796051/playing_cards_project/monarchs1_1024x1024_rltbye.webp'),
       (3, 'default_creator_wgjltr',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg'),
       (4, 'default_distributor_ogp1ju',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png'),
       (5, 'bicycle-high-victorian-green-cardistry_dp4xmx',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1659366892/bicycle-high-victorian-green-cardistry_dp4xmx.jpg'),
       (6, 'index_idqbtb', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659366890/index_idqbtb.jpg'),
       (7, 'Photo2B17-6-18252C2B122B232B50_bli0vw',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1659366890/Photo2B17-6-18252C2B122B232B50_bli0vw.jpg'),
       (8, '9f300ffcff9efd4d98b416e45496cc2ddfa3ec62_original_ca08vn',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1659366893/9f300ffcff9efd4d98b416e45496cc2ddfa3ec62_original_ca08vn.jpg'),
       (9, 'BICYC_okznaw', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659608671/BICYC_okznaw.jpg'),
       (10, 'index_e1e79n', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659608670/index_e1e79n.jpg'),
       (11, 'unnamed_jjlv3o', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659609851/unnamed_jjlv3o.jpg'),
       (12, 't11_-_FB_Cover_-_9_elhnzy',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1659609986/t11_-_FB_Cover_-_9_elhnzy.webp'),
       (13, 'New_E_logo-07_brrdfa',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610045/New_E_logo-07_brrdfa.webp'),
       (14, 'c9797442bd4f6dcf3b701dbc4710e4be_d8lpup',
        'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610102/c9797442bd4f6dcf3b701dbc4710e4be_d8lpup.png'),
       (15, 'index1_bqzddd', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610449/index1_bqzddd.jpg'),
       (16, 's-l500_vyqavo', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610451/s-l500_vyqavo.jpg'),
       (17, 'memento_1_pj6emq', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610697/memento_1_pj6emq.jpg'),
       (18, 's-l5010_grlyhb', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610697/s-l5010_grlyhb.jpg'),
       (19, 'index3_fqdfka', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610913/index3_fqdfka.jpg'),
       (20, 'index4_xyqwta', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659610932/index4_xyqwta.jpg'),
       (21, '1st-Playing-Cards-v1-by-Chris-Ramsay_j3lqet', 'https://res.cloudinary.com/dykamqwpf/image/upload/v1659624841/1st-Playing-Cards-v1-by-Chris-Ramsay_j3lqet.jpg');

INSERT INTO users(id, username, email, password, rating, role_id)
VALUES (1, 'Endeville', 'victor.popdonchev@gmail.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 5, 2),
       (2, 'Vipop', 'xvipop@gmail.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 5, 1),
       (3, 'User1', 'user1@user.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 3, 2),
       (4, 'User2', 'user2@user.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 4, 2),
       (5, 'Admin1', 'admin1@admin.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 4, 1);

INSERT INTO addresses(city, country, street, telephone, user_id)
VALUES ('Sofia', 'Bulgaria', 'Vitoshka', '0882865091', 1),
       ('Plovidv', 'Bulgaria', 'Street', '0882865092', 1),
       ('Varna', 'Bulgaria', 'Street0', '0882865093', 2),
       ('City1', 'Country1', 'Street1', '0882865094', 3),
       ('City2', 'Country2', 'Street2', '088286509', 3),
       ('City3', 'Country3', 'Street3', '08828650954', 4),
       ('City4', 'Country4', 'Street4', '0882865096', 5),
       ('City5', 'Country5', 'Street5', '0882865097', 5);

INSERT INTO creators(id, name, description, picture_id)
VALUES (1, 'Joe White', 'Self taught, Specializing in branding and packaging design.
Has a unique and secret process. Using Flash animation and programming techniques to create illustrations, visual effects and custom lettering.
For work enquiries, Please email
yeoldestudiouk@gmail.com', null),
       (2, 'Curtis Jinkins', 'Independent Graphic Design Professional. Works at Neighborhood Studio.', null),
       (3, 'Chris Ramsay',
        'Canadian magician and YouTuber and television producer, known for creating and starring in the TruTV stunt magic show Big Trick Energy. His YouTube channel, featuring puzzle solves, cardistry and magic has over 6 million subscribers.',
        11),
       (4, 'Joe Lewis', 'American playing cards designer known for his extravagant and original gaffed decks.', null);

INSERT INTO distributors(id, brand, description, url, picture_id)
VALUES (1, 'Theory11', 'Our team is composed of the best of the best minds in the magic industry - from performers to creators and consultants.
As a result, our team has consulted and created magic for the majority of magic projects on stage, film, or television over the past decade',
        'https://www.theory11.com/', 12),
       (2, 'Ellusionist',
        'At Ellusionist, we have one goal: to give you the power to perform magic beyond belief. We want to make you the life of any party. We want to make you into a performer. To achieve that goal, we built this site with YOU in mind.',
        'https://ellusionist.com/', 13),
       (3, 'Bicycle', 'The United States Playing Card Company creates the world’s best playing cards.
For over 130 years, we have been called ‘First in Fun’ for bringing people together in the name of good games and great company.
Our cards have paved the path to freedom for POWs in World War II and kept presidents entertained in the oval office. They’ve passed through the hands of generations, uniting loved ones around games that provide a lifetime of shared joy.',
        'https://bicyclecards.com/', 14);


INSERT INTO decks (id, title, country_of_origin, description, recommended_price, creator_id, distributor_id, picture_id)
VALUES (1, 'High Victorian Green', 'USA',
        'High Victorian Playing Cards were inspired by the intricate, breathtaking style of the Victorian era. Every element was designed from scratch with painstaking, relentless attention to detail.',
        5, 1, 1, 1),
       (2, 'Monarchs Navy Blue', 'USA',
        'The world''s finest, playing cards fit for a king. The gold metallic foil is striking, with a vintage, timeless aesthetic. They''re absolutely breathtaking - and they feel as good as they look.',
        10, 2, 1, 2),
       (3, 'Knights', 'USA',
        'The deck itself is superb with a clever marking system. The art work is stunning, the feel is great!', 12, 3,
        2, 7),
       (4, 'Bicycle gaffed', 'USA',
        'It contains more than 30 visual magic effects, including the familiar Three Card Monte, royal flush, vanish pack, kiss card, LOVE cards, fingerprint card, etc.',
        4, 2, 3, 9),
       (5, '1st v1', 'Canada',
        'These Playing Cards are made with the highest quality stock by the United States Playing Card Company. Crushed and Air Cushioned finish. The tuck case is made of smooth paper that feels like velvet with a gold foil ring rapping around and 1ST debossed and cut out in the front of the deck.',
        70, 3, 2, 15),
       (6, 'Memento mori', 'USA',
        'Each card within a suit was one of the thirteen phases of the moon. The fifty-two cards in a deck would represent the fifty-two weeks of the year and together, this would represent just one year of your life. A constant reminder of our fleeting time on earth.',
        15, 3, 2, 17),
       (7, '1st v3', 'USA',
        'Printed by the United States Playing Card Company, they feature beautiful Metalux foiling on the backs and some faces of the cards. The Deck comes in Mnemonica stack with a duplicate 4 of Spades and a playing card reveal on the King of Hearts. Both jokers are also foil stamped and identical.',
        40, 3, 2, 19);

INSERT
INTO decks_categories(decks_id, categories_id)
VALUES (1, 2),
       (1, 1),
       (1, 6),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (4, 3),
       (4, 7),
       (5, 1),
       (5, 2),
       (5, 3),
       (5, 4),
       (6, 1),
       (6, 2),
       (6, 3),
       (6, 4),
       (7, 1),
       (7, 2),
       (7, 3),
       (7, 4),
       (7, 8);

INSERT INTO offers(id, price, quantity, description, status, deck_id, seller_id, picture_id)
VALUES (1, 20, 4, 'Great offer. You will not find anything better', 'AVAILABLE', 1, 1, 5),
       (2, 5, 2, 'Better hurry the offer will not be available soon.', 'LIMITED', 2, 1, 6),
       (3, 9, 3, 'Wonderful offer for a limited time.', 'LIMITED', 3, 2, 8),
       (4, 60, 2, '1st v1 is one of the most popular decks these past years. I have several spare ones so I decided to share them with you. :)', 'LIMITED', 5, 4, 16),
       (5, 30, 1, 'Very rare deck at an affordable price. It is unsealed but in mint condition.', 'LIMITED', 5, 3, 21),
       (6, 18, 4, 'Wonderful deck by chris ramsay. Really nice to the touch. Handles perfectly.', 'AVAILABLE', 6, 1, 18);

INSERT INTO cart_items(id, quantity, customer_id, offer_id)
VALUES (1, 1, 1, 5),
       (2, 2, 4, 3),
       (3, 1, 1, 4),
       (5, 2, 3, 3);

INSERT INTO requests(id, content, created, creator_id)
VALUES (1, 'I love your site keep up the great work', CAST('2020-08-03 1:50:30' AS DATETIME), 1),
    (2, 'I would like to get my money back. I got scammed.', CAST('2022-07-26 12:13:43' AS DATETIME), 3),
    (3, 'I am still waiting for my money', CAST('2022-08-03 9:33:50' AS DATETIME), 3),
    (4, 'I would suggest that you add notifications to the site so that whenever an order status is changed, we the clients get notified', CAST('2022-07-05 7:30:23' AS DATETIME), 4);

UPDATE distributors
SET picture_id=4
WHERE picture_id IS NULL;

UPDATE creators
SET picture_id=3
WHERE picture_id IS NULL;
