create table user
(
   iduser integer not null auto_increment,
   uuidperson UUID not null,
   uuiduser UUID not null,
   username varchar(255) not null,
   password varchar(255) not null,
   logo varchar(255) ,
   pin varchar(255) ,
   securityimage varchar(255) ,
   authmetod varchar(255),
   email varchar(255),
   primary key(iduser)
);

create table session
(
   uuidsession UUID not null,
   uuiduser UUID not null,
   startsession timestamp not null,
   endsession timestamp not null
  
);

