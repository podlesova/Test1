create table answers (id CHAR(32) not null, id_user CHAR(32) not null, id_variant CHAR(32) not null, primary key (id));
create table questionnaire (id CHAR(32) not null, name varchar(255) not null, primary key (id));
create table questions (id CHAR(32) not null, is_radio boolean not null, text varchar(255) not null, id_questionnaire CHAR(32) not null, primary key (id));
create table users (id CHAR(32) not null, email varchar(255) not null, is_admin boolean not null, name varchar(255), password varchar(255) not null, primary key (id));
create table variants (id CHAR(32) not null, text varchar(255) not null, id_question CHAR(32) not null, primary key (id));
alter table answers add constraint FKdubk9ka9a0043md3lsxc60ra6 foreign key (id_user) references users ON DELETE CASCADE ON UPDATE CASCADE;
alter table answers add constraint FK2gycuu6k9udwy7hhglr40dgeg foreign key (id_variant) references variants ON DELETE CASCADE ON UPDATE CASCADE;
alter table questions add constraint FKsxqoetd42wc1d12u7tpaimr3a foreign key (id_questionnaire) references questionnaire  ON DELETE CASCADE ON UPDATE CASCADE;
alter table variants add constraint FK7m1q53wu9bva5irbenqeqhfpy foreign key (id_question) references questions ON DELETE CASCADE ON UPDATE CASCADE;