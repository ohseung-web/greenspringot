-- DB springBootDB
create database springBootDB;
use springBootDB;

-- user_member
create table user_member(
  no int auto_increment primary key,
  id varchar(20) not null,
  pw varchar(100) not null,
  mail varchar(50) not null,
  phone varchar(50) not null,
  reg_date datetime default now(),
  mod_date datetime default now()
);

-- board
--create table board(
--num int auto_increment primary key,
--writer varchar(20),
--subject varchar(30),
--writerPw varchar(20),
--reg_date datetime default now(),
--readcount int default 0,
--content varchar(1000)
--);

create table board(
num int auto_increment primary key,
writer varchar(20),
subject varchar(30),
writerPw varchar(20),
reg_date datetime default now(),
readcount int default 0,
content varchar(1000),
id varchar(20)
);

ALTER TABLE board ADD image VARCHAR(255);

INSERT INTO board (writer, subject, writerPw, reg_date, readcount, content, id) VALUES
('kim',   '스프링 공부중입니다', '1234', NOW(), 3, '스프링부트 너무 재밌어요', 'kim1111'),
('오티',  '겨울이야',           '1234', NOW(), 5, '날씨가 너무 춥네요', 'kjb1030'),
('관리자','공지사항입니다',      '1234', NOW(), 10, '필독 공지입니다', 'admin1234'),
('young', 'MyBatis 질문',        '1234', NOW(), 2, 'mapper가 헷갈려요', 'young111'),
('kim',   '게시판 테스트',       '1234', NOW(), 1, '더미 데이터입니다', 'kim2222'),
('yoon',  '자바 어렵다',         '1234', NOW(), 7, '그래도 해야죠', 'y5555'),
('kim',   '스프링 MVC',          '1234', NOW(), 4, '구조 이해중', 'kim1111'),
('오티',  '눈 오는 날',          '1234', NOW(), 6, '눈이 많이 옵니다', 'kjb1030'),
('관리자','서버 점검 안내',      '1234', NOW(), 12,'오늘 밤 점검', 'admin1234'),
('young', '타임리프 질문',       '1234', NOW(), 2, 'th:each 어렵네요', 'young111'),
('kim',   '게시판 CRUD',         '1234', NOW(), 3, 'CRUD 연습중', 'kim2222'),
('yoon',  'DB 연결 성공',        '1234', NOW(), 8, '드디어 성공', 'y5555'),
('kim',   '세션이 뭐예요?',      '1234', NOW(), 5, '로그인 질문', 'kim1111'),
('오티',  '주말 계획',           '1234', NOW(), 1, '집에서 코딩', 'kjb1030'),
('관리자','공지사항2',           '1234', NOW(), 9, '두번째 공지', 'admin1234'),
('young', '에러 해결',           '1234', NOW(), 4, 'Null 에러 해결함', 'young111'),
('kim',   '페이징 구현',         '1234', NOW(), 6, 'limit 사용', 'kim2222'),
('yoon',  'CSS 수정',            '1234', NOW(), 2, '테이블 정렬', 'y5555'),
('kim',   '로그인 기능',         '1234', NOW(), 7, '세션 사용', 'kim1111'),
('관리자','수업 자료 업로드',    '1234', NOW(), 15,'PPT 확인하세요', 'admin1234');

create table carProduct(
no int auto_increment primary key, -- 자동차 식별자
carName varchar(20), -- 자동차 이름
price int, -- 자동차 가격
company varchar(50), -- 자동차 회사
img varchar(50), -- 자동차 이미지
info varchar(500) -- 자동차 설명
);

INSERT INTO carProduct (carName, price, company, img, info) VALUES
('Veyron', 2000000000, 'Bugatti', '1.jpg', '고성능 슈퍼카입니다.'),
('NSX', 180000000, 'Acura', '2.jpg', '하이브리드 스포츠카입니다.'),
('Fortwo', 25000000, 'Smart', '3.jpg', '초소형 도심용 차량입니다.'),
('Pony', 15000000, 'Hyundai', '4.jpg', '현대자동차의 첫 모델입니다.'),
('Concept X', 500000000, 'Concept', '5.jpg', '미래형 콘셉트카입니다.'),
('Mini', 42000000, 'MINI', '6.jpg', '경쾌한 주행의 소형차입니다.'),
('Sonata', 35000000, 'Hyundai', '7.jpg', '편안한 중형 세단입니다.'),
('Avante', 28000000, 'Hyundai', '8.jpg', '인기 준중형 세단입니다.'),
('Ioniq5', 55000000, 'Hyundai', '9.jpg', '전기차 전용 모델입니다.'),
('Concept EV', 600000000, 'Future', '10.jpg', '미래형 전기차입니다.'),
('A4', 60000000, 'Audi', '11.jpg', '프리미엄 중형 세단입니다.'),
('K3', 26000000, 'Kia', '12.jpg', '가성비 좋은 세단입니다.'),
('Corvette', 150000000, 'Chevrolet', '13.jpg', '미국 스포츠카입니다.'),
('Mustang', 120000000, 'Ford', '14.jpg', '머슬카의 상징입니다.'),
('Seltos', 32000000, 'Kia', '15.jpg', '소형 SUV 모델입니다.'),
('Grandeur', 45000000, 'Hyundai', '16.jpg', '준대형 세단입니다.'),
('GHybrid', 50000000, 'Hyundai', '17.jpg', '하이브리드 세단입니다.'),
('G80', 90000000, 'Genesis', '18.jpg', '프리미엄 세단입니다.'),
('Muscle', 80000000, 'Classic', '19.jpg', '클래식 머슬카입니다.'),
('Coupe', 70000000, 'Retro', '20.jpg', '레트로 감성 차량입니다.');