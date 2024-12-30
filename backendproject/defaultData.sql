
CREATE TABLE BANK (
                      bank_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      bank_name VARCHAR(50) NOT NULL
);

CREATE TABLE CUSTOMERS (
                           seq INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           user_id VARCHAR(20) UNIQUE,
                           user_password VARCHAR(20) NOT NULL,
                           user_name VARCHAR(20) NOT NULL,
                           bank_id INT,
                           FOREIGN KEY (bank_id) REFERENCES BANK(bank_id)
);

CREATE TABLE ACCOUNT (
                         seq INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         customer_id INT,
                         bank_number VARCHAR(20) NOT NULL,
                         balance INT DEFAULT 0,
                         FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(seq)
);

INSERT INTO bank(bank_name) VALUES("농협 은행");
INSERT INTO bank(bank_name) VALUES("신한 은행");
INSERT INTO bank(bank_name) VALUES("부산 은행");
INSERT INTO bank(bank_name) VALUES("하나 은행");

INSERT INTO CUSTOMERS(user_id, user_password, user_name, bank_id) values ("jerry6475", "asd", "김건호", 1);