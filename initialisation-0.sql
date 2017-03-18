create table Users(id Integer Primary Key auto_increment,login Varchar(32) Unique,password Varchar(32),prenom Varchar(255));
create table Friends(de Integer,vers Integer,timestamp Timestamp,primary key(de,vers));
create table Sessions(cle Varchar(32) unique,id Integer,timestamp Timestamp,root Boolean,primary key(cle),index(cle,id,timestamp));