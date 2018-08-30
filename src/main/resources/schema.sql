create table user
(
   iduser integer not null,
   uuidperson varchar(255) not null,
   uuiduser varchar(255) not null,
   username varchar(255) not null,
   password varchar(255) not null,
   logo varchar(255) ,
   pin varchar(255) ,
   securityimage blob ,
   primary key(iduser)
);