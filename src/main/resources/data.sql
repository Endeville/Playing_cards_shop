INSERT INTO roles (id, role)
VALUES (1, 'ADMIN'),
       (2, 'MODERATOR'),
       (3, 'CLIENT');

INSERT INTO categories(id, category)
VALUES (1, 'STANDARD'),
       (2, 'GAME'),
       (3, 'MAGIC'),
       (4, 'CARDISTRY'),
       (5, 'VINTAGE'),
       (6, 'POKER'),
       (7, 'GAFFED'),
       (8, 'MARKED');

INSERT INTO users(id, username, email, password, rating, role_id)
VALUES (1, 'Endeville', 'victor.popdonchev@gmail.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 5, 3),
       (2, 'Vipop', 'xvipop@gmail.com',
        '9f6095cc9b392438eb577c67382a951ae945df94ab649e268f1fc2b527c678daebf5609944280cf8', 5, 1);

INSERT INTO addresses(city, country, street, telephone, user_id)
VALUES  ('Sofia', 'Bulgaria', 'Vitoshka', '0882867098', 1),
        ('Plovidv', 'Bulgaria', 'Neshto1', '0882867092', 1),
        ('Varna', 'Bulgaria', 'Nshto2', '0882867094', 2);

-- INSERT INTO users_addresses(user_entity_id, addresses_id)
-- VALUES (1, 1),
--        (1, 2),
--        (2, 3);

INSERT INTO creators(id, name, description)
VALUES (1, 'Joe White', 'Self taught, Specializing in branding and packaging design.
Has a unique and secret process. Using Flash animation and programming techniques to create illustrations, visual effects and custom lettering.
For work enquiries, Please email
yeoldestudiouk@gmail.com'),
    (2, 'Curtis Jinkins', 'Independent Graphic Design Professional. Works at Neighborhood Studio.');

INSERT INTO distributors(id, brand, description, url)
VALUES (1, 'Theory11', 'Our team is composed of the best of the best minds in the magic industry - from performers to creators and consultants.
As a result, our team has consulted and created magic for the majority of magic projects on stage, film, or television over the past decade',
        'https://www.theory11.com/');


INSERT INTO decks (title, country_of_origin, description, approved, creator_id, distributor_id)
VALUES ('High Victorian Green', 'USA', 'somethign idk', true, 1, 1),
       ('Monarchs Navy Blue', 'USA', 'monarchs ofc', true, 2, 1);

INSERT INTO decks_categories(decks_id, categories_id)
VALUES (1, 2),
       (1, 1),
       (2, 1),
       (2, 2),
       (2, 3);

INSERT INTO offers(id, price, quantity, description, status, deck_id, seller_id)
VALUES (1, 20, 4, "Great offer. You won't find anything better", 'APPROVED', 1, 1),
       (2, 5, 2, "Better hurry the offer won't be available soon.", 'LIMITED', 2, 1);

INSERT INTO pictures(url, deck_id, offer_id)
VALUES ('https://res.cloudinary.com/dykamqwpf/image/upload/v1657796050/playing_cards_project/high_victorian_green_t27q49.jpg',
        1, 1),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1657796051/playing_cards_project/monarchs1_1024x1024_rltbye.webp',
        2, 2),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_creator_wgjltr.jpg', null, null),
       ('https://res.cloudinary.com/dykamqwpf/image/upload/v1658170325/default_distributor_ogp1ju.png', null, null);

UPDATE distributors
SET picture_id=4
WHERE id = 1;

UPDATE creators
SET picture_id=3
WHERE id = 1
   OR 2;
