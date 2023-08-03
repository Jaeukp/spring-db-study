-- 해당 스키마(데이터베이스)를 사용
-- 데이터베이스
-- : 데이터베이스 객체를 사용할 수 있는 공간
-- : 객체 - 테이블, 뷰, 인덱스, 저장프로시저 ...
/*
데이터베이스 엔진: MySQL
데이터베이스(스키마): myapp
CREATE SCHEMA myapp;
CREATE DATABASE myapp;
MySQL에서는 스키마와 데이터베이스가 동일한 개념이다.
*/
use myapp;
-- 데이터를 저장할 수 있는 공간
/* create table 테이블 (
	컴럼명 데이터타입 제약조건,
	...,
	추가적인 제약조건(constraint)
)
*/
/* 
	DDL(data definion language)
	create ...
    SQL(Structured Query Language)
    SEQUEL(... Engilsh)
*/
create table contact(
	-- varchar: variable charactor
    -- 기반문자열, 255byte 까지 넣을 수 있다.
	email varchar(255) not null,
	image varchar(255),
    name varchar(255),
    phone varchar(255),
	primary key(email)
) engine=InnoDB;

select length("훈"); -- 3byte
select length("a"); -- 1bytecontact

-- insert into 테이블 values(값목록 ...);
-- 값목록은 순서를 잘 맞춰야함.
insert into contact
value("Park@naver.com", null, "박재욱", "010-6219-6372");
-- 1 row(s) affectd(1 행이 영향을 받았다.)
-- 데이터 1건을 row(행) 또는 record(레코드)
-- 데이터 대한 속성을 column(열) 또는 field(필드)
-- 값 순서를 설정
-- insert into 테이블 (컬럼명목로...) values(값목록...)

insert into contact(name, phone, email, image)
value("홍길동", "010-2828-8184" "mertake7@naver.com", null);
/* 기존에 있는 email로 insert
Error Code: 1062. Duplicate entry 'mertake7@naver.com'
for key 'contact.PRIMARY' 0.000sec
*/
/*
Primary key 제약 조건:
1. 테이블내의 데이터 중에서 같은 값이 중복이 있으며 안 됨
2. null 값이 될 수 없음
*/
-- 목록 조회
select * from contact;

-- 특정 컬럼으로 정렬하여 조회
-- asc(기본값): 순정렬, desc: 역정렬
select * from contact order by name;

-- 데이터베이스의 PK(index, clustered)
-- clustered index에 맞게 데이터가 정렬되어있음
-- index(binaray tree)구조이고, 데이터(linked list) 구조이다.
-- index(목차) 트리를 검색하여 데이터까지 찾아감

-- PK 값으로 1건만 찾아옴
-- where 조건식
-- where 검렴명 = '컬럼값'
select * from contact where email = "mertake7@naver.com";
select * from contact;

-- 조건에 맞는 레코드 삭제
-- where 절의 조건식에는 PK컬럼 기준으로 나오는게 좋음.
-- 실수로 불필요한 레코드가 지워지는 것을 방치할 수 있다.
delete from contact where email = "mertake7@naver.com";
-- delete from contact where name = "박재욱";
delete email from contact where name > '강';

-- 테이블의 데이터 전체 삭제
-- 테이블구조를 재생성(DDL)
-- DML: insert, delete
-- DML: Data Mainpilation(조작) Language
-- truncate는 transaction 로그를 쌓지 않음(복구 불가)
truncate table contact;

create table post (
		no bigint not null auto_increment, 
    content varchar(255), 
    created_time bigint not null, 
    creator_name varchar(255), 
    title varchar(255), 
    primary key (no)
) engine=InnoDB;

insert into post (created_time, title) value(2, "제목");
select * from post;
delete from post where no = 2;

