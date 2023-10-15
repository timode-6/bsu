create table bookings (
    booking_id serial primary key,
    amount_of_tenants integer default 1,
    check_in_time timestamp not null,
    check_out_time timestamp not null,
    client_name varchar(20) not null,
    client_surname varchar(20) not null,
    client_phone_number varchar(20) not null,
    client_email varchar(40),
    room_number integer not null
);

//////////////////////////////////////////////////

create type user_type as enum ('manager', 'receptionist', 'administrator');

create table users (
    nickname varchar(50) not null unique,
    "password" varchar(50) not null,
    user_type user_type not null
);

///////////////////////////////////////////////////////

insert into bookings (amount_of_tenants, check_in_time, check_out_time, client_name, client_surname, client_phone_number,
client_email, room_number)
values
(3, '2023.04.10 21:30', '2023.04.12 13:00', 'Michail', 'Bezdarev', '+375-29-477-18-92', 'mbz228@gmail.com', 1408),
(1, '2023.05.01 12:30', '2023.05.10 15:00', 'Olga', 'Dudkina', '+375-29-666-90-01', 'megalodon2@gmail.com', 12),
(1, '2023.04.03 11:00', '2023.04.28 11:00', 'Gennadiy', 'Gorin', '+375-29-000-00-00', 'ggorin00@gmail.com', 47)

////////////////////////////////////////////////////////

insert into users (nickname, "password", user_type)
values
('s1mple', 'fiejf8292jifkd', 'manager'),
('tfman', '72837rh38r7LDKSDFLDSFMLSK', 'administrator'),
('bobr11', '123456789', 'receptionist')


