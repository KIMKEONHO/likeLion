SET FOREIGN_KEY_CHECKS = 0;

-- DROP TABLE 순서 상관없이 다 삭제
DROP TABLE IF EXISTS schedule_participants;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS meeting_participants;
DROP TABLE IF EXISTS meetings;
DROP TABLE IF EXISTS users;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE
);


CREATE TABLE meetings (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          title VARCHAR(100) NOT NULL,
                          description TEXT,
                          creator_id BIGINT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE meeting_participants (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      meeting_id BIGINT NOT NULL,
                                      user_id BIGINT NOT NULL,
                                      joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (meeting_id) REFERENCES meetings(id) ON DELETE CASCADE,
                                      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                      UNIQUE (meeting_id, user_id)
);

CREATE TABLE schedules (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           meeting_id BIGINT NOT NULL,
                           title VARCHAR(100) NOT NULL,
                           description TEXT,
                           created_by BIGINT NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (meeting_id) REFERENCES meetings(id) ON DELETE CASCADE,
                           FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE schedule_participants (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       schedule_id BIGINT NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE CASCADE,
                                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                       UNIQUE (schedule_id, user_id)
);


INSERT INTO users (username, password, email) VALUES
                                                  ('user1', '$2a$10$EixZg3Wq9Jj1L8vF2ZZjM.J7yq8P3gD2T8Kj0D1Pz8oFbe8J1Yd9K', 'user1@example.com'), -- 비밀번호: password1
                                                  ('user2', '$2a$10$R0I8ZBlD8d0T0aF2ZZjM.J7yq8P3gD2T8Kj0D1Pz8oFbe8J1Yd9K', 'user2@example.com'), -- 비밀번호: password2
                                                  ('user3', '$2a$10$J9F8KBlD8d0T0aF2ZZjM.J7yq8P3gD2T8Kj0D1Pz8oFbe8J1Yd9K', 'user3@example.com'); -- 비밀번호: password3

INSERT INTO meetings (title, description, creator_id) VALUES
                                                          ('Weekly Team Meeting', 'Discuss project updates and tasks.', 1),
                                                          ('Monthly Review', 'Review monthly performance and metrics.', 2),
                                                          ('Project Kickoff', 'Kickoff meeting for the new project.', 1);

INSERT INTO meeting_participants (meeting_id, user_id) VALUES
                                                           (1, 1),
                                                           (1, 2),
                                                           (2, 1),
                                                           (2, 3),
                                                           (3, 2),
                                                           (3, 3);

INSERT INTO schedules (meeting_id, title, description, created_by) VALUES
                                                                       (1, 'Action Items', 'List of action items from the meeting.', 1),
                                                                       (2, 'Performance Review', 'Detailed review of performance metrics.', 2),
                                                                       (3, 'Project Timeline', 'Overview of the project timeline and milestones.', 1);

INSERT INTO schedule_participants (schedule_id, user_id) VALUES
                                                             (1, 1),
                                                             (1, 2),
                                                             (2, 1),
                                                             (2, 3),
                                                             (3, 2),
                                                             (3, 3);