insert into usr (id, active, email, password, username)
values (1, true, 'admin@admin.com', 'a', 'admin');

insert into user_role (user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN');

insert into usr (id, active, email, password, username)
values (2, true, 'user@user.com', 'u', 'user');

insert into user_role (user_id, roles)
values (2, 'USER');