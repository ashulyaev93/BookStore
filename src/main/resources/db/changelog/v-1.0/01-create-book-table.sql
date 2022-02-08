create table bookshop.book (
     id bigserial not null,
     title varchar(150) not null,
     author varchar(150) not null,
     description varchar(150),
     constraint book_pk primary key (id));
GO

insert into bookshop.book (id, title, author, description)
        values(0,'java','alex','java book');
GO

