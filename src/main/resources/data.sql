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

INSERT INTO pictures(url)
VALUES ('https://res.cloudinary.com/dykamqwpf/image/upload/v1657796050/playing_cards_project/high_victorian_green_t27q49.jpg'),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1657796051/playing_cards_project/monarchs1_1024x1024_rltbye.webp'),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg'),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png'),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1659366892/bicycle-high-victorian-green-cardistry_dp4xmx.jpg'),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1659366890/index_idqbtb.jpg'),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1659366890/Photo2B17-6-18252C2B122B232B50_bli0vw.jpg'),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1659366893/9f300ffcff9efd4d98b416e45496cc2ddfa3ec62_original_ca08vn.jpg');

INSERT INTO users(id, username, email, password, rating, role_id)
VALUES (1, 'Endeville', 'victor.popdonchev@gmail.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 5, 2),
       (2, 'Vipop', 'xvipop@gmail.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 5, 1);

INSERT INTO addresses(city, country, street, telephone, user_id)
VALUES  ('Sofia', 'Bulgaria', 'Vitoshka', '0882867098', 1),
        ('Plovidv', 'Bulgaria', 'Neshto1', '0882867092', 1),
        ('Varna', 'Bulgaria', 'Nshto2', '0882867094', 2);

INSERT INTO creators(id, name, description)
VALUES (1, 'Joe White', 'Self taught, Specializing in branding and packaging design.
Has a unique and secret process. Using Flash animation and programming techniques to create illustrations, visual effects and custom lettering.
For work enquiries, Please email
yeoldestudiouk@gmail.com'),
    (2, 'Curtis Jinkins', 'Independent Graphic Design Professional. Works at Neighborhood Studio.'),
       (3, 'Chris Ramsay', 'Canadian magician and YouTuber and television producer, known for creating and starring in the TruTV stunt magic show Big Trick Energy. His YouTube channel, featuring puzzle solves, cardistry and magic has over 6 million subscribers.');

INSERT INTO distributors(id, brand, description, url)
VALUES (1, 'Theory11', 'Our team is composed of the best of the best minds in the magic industry - from performers to creators and consultants.
As a result, our team has consulted and created magic for the majority of magic projects on stage, film, or television over the past decade',
        'https://www.theory11.com/'),
       (2, 'Ellusionist',
        'At Ellusionist, we have one goal: to give you the power to perform magic beyond belief. We want to make you the life of any party. We want to make you into a performer. To achieve that goal, we built this site with YOU in mind.',
        'https://ellusionist.com/');


INSERT INTO decks (title, country_of_origin, description, approved,recommended_price, creator_id, distributor_id, picture_id)
VALUES ('High Victorian Green', 'USA', 'somethign idk', true, 5, 1, 1, 1),
       ('Monarchs Navy Blue', 'USA', 'monarchs ofc', true, 10, 2, 1, 2),
       ('Knights', 'USA', 'The deck itself is superb with a clever marking system. The art work is stunning, the feel is great!', true, 12,3, 2, 7);

INSERT INTO decks_categories(decks_id, categories_id)
VALUES (1, 2),
       (1, 1),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4);


INSERT INTO offers(id, price, quantity, description, status, deck_id, seller_id, picture_id)
VALUES (1, 20, 4, 'Great offer. You will not find anything better', 'APPROVED', 1, 1, 5),
       (2, 5, 2, 'Better hurry the offer will not be available soon.', 'LIMITED', 2, 1, 6),
       (3, 9, 3, 'Wonderful offer for a limited time.', 'LIMITED', 3, 2, 8);

UPDATE distributors
SET picture_id=4
WHERE picture_id IS NULL;

UPDATE creators
SET picture_id=3
WHERE picture_id IS NULL;
