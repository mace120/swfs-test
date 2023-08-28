CREATE DATABASE IF NOT EXISTS swfs;

create table IF NOT EXISTS roles
(
    id        bigint      not null auto_increment,
    authority varchar(25) not null,
    primary key (id)
) engine=InnoDB;

create table IF NOT EXISTS users (
  id bigint not null auto_increment, 
  address varchar(255), 
  created_by varchar(255), 
  created_date datetime, 
  email varchar(255) not null, 
  father_name varchar(255), 
  full_name varchar(255), 
  is_active bit, 
  is_deleted bit, 
  password varchar(255), 
  phone varchar(255), 
  updated_by varchar(255), 
  updated_date datetime, 
  uuid varchar(255), 
  primary key (id)
) engine = InnoDB;

create table IF NOT EXISTS user_role
(
    user_id bigint not null,
    role_id bigint not null
) engine=InnoDB;

alter table user_role
    add constraint FKt7e7djp752sqn6w22i6ocqy6q foreign key (role_id) references roles (id);
alter table user_role
    add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users (id);
    
create table IF NOT EXISTS api_log
(
    id               bigint not null auto_increment,
    creation_date    datetime,
    method           varchar(15),
    request_body     TEXT,
    request_headers  TEXT,
    response_body    TEXT,
    response_headers TEXT,
    status_code      integer,
    status_text      TEXT,
    uri              varchar(255),
    primary key (id)
) engine = InnoDB;


