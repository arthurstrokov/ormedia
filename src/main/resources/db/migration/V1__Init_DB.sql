create sequence hibernate_sequence start with 4 increment by 1;
create table film (id bigint not null auto_increment, filename varchar(255), genre varchar(255), title varchar(2147483647), year varchar(255), user_id bigint, primary key (id));
create table film_rating (film_rating_key_id bigint not null auto_increment, film_id bigint, user_id bigint, rating bigint, primary key (film_id, user_id));
create table user_role (user_id bigint not null, roles varchar(255));
create table usr (id bigint not null auto_increment, active boolean not null, email varchar(255), password varchar(255), username varchar(255), primary key (id));
alter table film add constraint film_user_fk foreign key (user_id) references usr;
alter table film_rating add constraint film_rating_film_fk foreign key (film_id) references film;
alter table film_rating add constraint film_rating_user_fk foreign key (user_id) references usr;
alter table user_role add constraint user_role_user_fk foreign key (user_id) references usr