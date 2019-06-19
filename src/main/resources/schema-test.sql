drop table if exists contract;
drop table if exists team;
drop table if exists player;

create table contract
(
    contract_id int not null AUTO_INCREMENT,
    transfer_price int not null,
    season int not null,
    player_id int not null,
    team_id int not null,
    team_commission int not null,
    total_amount int not null,
    PRIMARY KEY ( contract_id )
);

create table player
(
    player_id int not null AUTO_INCREMENT,
    birthdate date not null,
    career_start_date date not null,
    name varchar(100) not null,
    PRIMARY KEY ( player_id )
);

create table team
(
    team_id int not null AUTO_INCREMENT,
    currency varchar(100) not null,
    name varchar(100) not null,
    PRIMARY KEY ( team_id )
);
