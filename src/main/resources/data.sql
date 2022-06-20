INSERT INTO roles (id, role)
VALUES (1, 'ADMIN'),
       (2, 'MODERATOR'),
       (3, 'CLIENT');

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

INSERT INTO pictures(url, deck_id)
VALUES ('resources/static/images/high_victorian_green.jpg', 1),
       ('resources/static/images/monarchs1_1024x1024.webp', 2);

