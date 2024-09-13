create table if not exists category(

    id serial primary key,
    name varchar(255),
    description varchar(255)


);

create table if not exists product (

   id serial primary key,
   name varchar(255),
   description varchar(255),
   available_quantity double precision not null,
   price numeric(38, 2),
   category_id integer
                constraint fk1 references category

);
