insert into users (id, email, name, is_admin, password) values ('533a4559e55c18b38456555563322003', '123456@mail.ru',   'Иванов Иван Иванович', true, '$2y$12$ljPjJWUFELYzhX0AtvugU.TV8srgGE92dvnagW/bVbhGV0jtgJqH.');
insert into users (id, email, name, is_admin, password) values ('533a4559e55c18b38456555563322013', 'ytrewq@gmail.com', 'Петров Петр Петрович', false,'$2y$12$mTwk0IiGb7eM/.U77SmCHOD.sY.uZzjHn8WkSEYk2ZYlfzwpNhlOa');

insert into questionnaire (id, name) values ('533a4559e55c18b38456555563322001', 'Кино');
insert into questionnaire (id, name) values ('533a4559e55c18b38456555563322002', 'Музыка');

insert into questions (id, id_questionnaire, text, is_radio) values ('533a4559e55c18b38456555563322011', '533a4559e55c18b38456555563322001', 'Любимый жанр', true);
insert into questions (id, id_questionnaire, text, is_radio) values ('533a4559e55c18b38456555563322021', '533a4559e55c18b38456555563322001', 'Любимый режиссер', true);
insert into questions (id, id_questionnaire, text, is_radio) values ('533a4559e55c18b38456555563322031', '533a4559e55c18b38456555563322001', 'Любимый актер', true);
insert into questions (id, id_questionnaire, text, is_radio) values ('533a4559e55c18b38456555563322012', '533a4559e55c18b38456555563322002', 'Любимый жанр', false);
insert into questions (id, id_questionnaire, text, is_radio) values ('533a4559e55c18b38456555563322022', '533a4559e55c18b38456555563322002', 'Любимый исполнитель', false);
insert into questions (id, id_questionnaire, text, is_radio) values ('533a4559e55c18b38456555563322032', '533a4559e55c18b38456555563322002', 'Любимая группа', false);

insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322111', '533a4559e55c18b38456555563322011', 'Триллер');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322211', '533a4559e55c18b38456555563322011', 'Фантастика');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322311', '533a4559e55c18b38456555563322011', 'Драма');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322121', '533a4559e55c18b38456555563322021', 'Спилберг');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322221', '533a4559e55c18b38456555563322021', 'Михалков');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322321', '533a4559e55c18b38456555563322021', 'Кустурица');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322131', '533a4559e55c18b38456555563322031', 'Том Круз');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322231', '533a4559e55c18b38456555563322031', 'Дэвид Суше');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322331', '533a4559e55c18b38456555563322031', 'Константин Хабенский');

insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322112', '533a4559e55c18b38456555563322012', 'Рок');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322212', '533a4559e55c18b38456555563322012', 'Поп');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322312', '533a4559e55c18b38456555563322012', 'Джаз');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322122', '533a4559e55c18b38456555563322022', 'Курт Кобейн');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322222', '533a4559e55c18b38456555563322022', 'Женя Белоусов');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322322', '533a4559e55c18b38456555563322022', 'Луи Армстронг');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322132', '533a4559e55c18b38456555563322032', 'Queen');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322232', '533a4559e55c18b38456555563322032', 'Little Big');
insert into variants (id, id_question, text) values ('533a4559e55c18b38456555563322332', '533a4559e55c18b38456555563322032', 'На-на');

