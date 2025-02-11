DROP TABLE IF EXISTS board;

CREATE TABLE board (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL, -- 암호는 해싱하여 저장하는 것이 좋습니다
                       content TEXT NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 등록일
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일
);

INSERT INTO board (name, title, password, content) VALUES
                                                       ('김민수', '첫 번째 글입니다!', 'password123', '이것은 첫 번째 게시글의 내용입니다.'),
                                                       ('이지은', '봄철 원예 팁', 'password123', '봄철 정원 가꾸기에 유용한 팁을 공유합니다.'),
                                                       ('박영희', '올해의 여행지 추천 10곳', 'password123', '올해 방문하기 좋은 여행지 10곳을 소개합니다.'),
                                                       ('최준호', 'SQL 이해하기', 'password123', 'SQL과 그 기능들에 대해 깊이 있게 알아봅시다.'),
                                                       ('황소연', '최고의 코딩 습관', 'password123', '모든 개발자가 따라야 할 코딩 습관에 대해 알아봅시다.'),
                                                       ('백은영', '사진 촬영 기초', 'password123', '초보자를 위한 사진 촬영 가이드입니다.'),
                                                       ('윤서준', '기술의 미래', 'password123', '기술과 혁신의 미래에 대한 예측을 해봅니다.'),
                                                       ('한지민', '예산 내에서 건강하게 먹기', 'password123', '예산을 깨지 않고 건강하게 먹는 방법을 공유합니다.'),
                                                       ('정태웅', '초보자를 위한 운동 루틴', 'password123', '운동을 막 시작한 사람들을 위한 효과적인 운동 루틴을 소개합니다.'),
                                                       ('김서영', '지역 뉴스 업데이트', 'password123', '최신 지역 뉴스를 업데이트합니다.'),
                                                       ('남주혁', '새로운 영화 개봉', 'password123', '이번 주말 개봉하는 새로운 영화들을 확인해보세요.');

DROP TABLE IF EXISTS boarduser;

CREATE TABLE boarduser(
                          seq INT AUTO_INCREMENT PRIMARY KEY,
                          user_id VARCHAR(50) NOT NULL,
                          user_password VARCHAR(50) NOT NULL,
                          user_name VARCHAR(100) NOT NULL,
                          age INT NOT NULL,
                          address VARCHAR(100)
);

INSERT INTO boarduser (user_id, user_password,user_name, age, address) VALUES
                                                                           ('aaa', '1234', 'kim', 20, '서울'),
                                                                           ('bbb', '1234', 'kang', 21, '수원'),
                                                                           ('ccc', '1234', 'hong', 22, '강남'),
                                                                           ('ddd', '1234', 'park', 23, '부산'),
                                                                           ('eee', '1234', 'lee', 24, '창원'),
                                                                           ('fff', '1234', 'seok', 25, '평택');