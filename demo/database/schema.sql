BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS messages;
DROP SEQUENCE IF EXISTS seq_message_id;
DROP TABLE IF EXISTS followers;
DROP SEQUENCE IF EXISTS seq_follower_id;

CREATE SEQUENCE seq_user_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE users (
    user_id int DEFAULT nextval ('seq_user_id'::regclass) NOT NULL,
    username varchar(100) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id) 
);

INSERT INTO users (username) VALUES ('Alice');
INSERT INTO users (username) VALUES ('Bob');
INSERT INTO users (username) VALUES ('Charlie');

CREATE SEQUENCE seq_message_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE messages (
    message_id int DEFAULT nextval ('seq_message_id'::regclass) NOT NULL,
    message_text varchar(256) NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT PK_message PRIMARY KEY (message_id),
    CONSTRAINT FK_message_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE SEQUENCE seq_follower_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE followers (
    follower_id int DEFAULT nextval ('seq_follower_id'::regclass) NOT NULL,
    follower_user_id int NOT NULL,
    followed_user_id int NOT NULL,
    CONSTRAINT PK_followers PRIMARY KEY (follower_id),
    CONSTRAINT FK_follower_user_id FOREIGN KEY (follower_user_id) REFERENCES users(user_id),
    CONSTRAINT FK_followed_user_id FOREIGN KEY (followed_user_id) REFERENCES users(user_id)
);

COMMIT;