drop table if exists TASKS;
create table TASKS
(
    id          int primary key auto_increment,
    description varchar(255) not null,
    done        bit
)
