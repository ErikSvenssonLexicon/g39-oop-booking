

drop table if exists booking;

drop table if exists patient;

drop table if exists premises;

drop table if exists address;

drop table if exists contact_info;

drop table if exists test_table;

drop table if exists user_credentials;



create table if not exists address
(
    id       varchar(255) not null
        primary key,
    street   varchar(255) not null,
    zip_code varchar(20)  not null,
    city     varchar(255) not null
);

create table if not exists contact_info
(
    id    varchar(255) not null
        primary key,
    email varchar(100) not null,
    phone varchar(100) null,
    constraint email_UNIQUE
        unique (email)
);

create table if not exists premises
(
    id              varchar(255) not null
        primary key,
    name            varchar(255) not null,
    fk_contact_info varchar(255) null,
    fk_address      varchar(255) null,
    constraint fk_premises_address1
        foreign key (fk_address) references address (id),
    constraint fk_premises_contact_info1
        foreign key (fk_contact_info) references contact_info (id)
);

create index fk_premises_address1_idx
    on premises (fk_address);

create index fk_premises_contact_info1_idx
    on premises (fk_contact_info);

create table if not exists test_table
(
    id          int auto_increment
        primary key,
    description varchar(255) not null,
    number      int          not null
);

create table if not exists user_credentials
(
    id       varchar(255) not null
        primary key,
    username varchar(100) not null,
    password varchar(255) not null,
    role     varchar(100) not null,
    constraint username_UNIQUE
        unique (username)
);

create table if not exists patient
(
    id                  varchar(255) not null
        primary key,
    ssn                 varchar(12)  not null,
    first_name          varchar(100) not null,
    last_name           varchar(100) not null,
    birth_date          date         not null,
    fk_user_credentials varchar(255) null,
    fk_contact_info     varchar(255) null,
    constraint ssn_UNIQUE
        unique (ssn),
    constraint fk_patient_contact_info1
        foreign key (fk_contact_info) references contact_info (id),
    constraint fk_patient_user_credentials
        foreign key (fk_user_credentials) references user_credentials (id)
);

create table if not exists booking
(
    id            varchar(255) not null
        primary key,
    date_time     datetime     not null,
    price         decimal      not null,
    administrator varchar(255) null,
    vaccine_id    varchar(255) not null,
    vacant        tinyint(1)   not null,
    fk_patient    varchar(255) null,
    fk_premises   varchar(255) null,
    constraint fk_booking_patient1
        foreign key (fk_patient) references patient (id),
    constraint fk_booking_premises1
        foreign key (fk_premises) references premises (id)
);

create index fk_booking_patient1_idx
    on booking (fk_patient);

create index fk_booking_premises1_idx
    on booking (fk_premises);

create index fk_patient_contact_info1_idx
    on patient (fk_contact_info);

create index fk_patient_user_credentials_idx
    on patient (fk_user_credentials);



