DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `member_status`;
DROP TABLE IF EXISTS `member_authority`;

CREATE TABLE `member_status`
(
    `member_status_id` INT AUTO_INCREMENT,
    `status`           VARCHAR(20) NULL,
    primary key (
                 `member_status_id`
        )
);

CREATE TABLE `member_authority`
(
    `authority_id` INT AUTO_INCREMENT,
    `status`       VARCHAR(20) NOT NULL,
    primary key (
                 `authority_id`
        )
);

CREATE TABLE `member`
(
    `member_id`        VARCHAR(30) primary key,
    `member_status_id` INT DEFAULT 1,
    `authority_id`     INT DEFAULT 2,
    `password`         VARCHAR(300) NOT NULL,
    `email`            VARCHAR(30)  NOT NULL,
    `name`             VARCHAR(20)  NOT NULL,
	CONSTRAINT `FK_member_status_TO_member_1`
    FOREIGN KEY (`member_status_id`) REFERENCES `member_status` (`member_status_id`),
    CONSTRAINT `FK_member_authority_TO_member_1`
    FOREIGN KEY (`authority_id`) REFERENCES `member_authority` (`authority_id`)
);

-- member_status 테이블 데이터 추가

INSERT INTO member_status
VALUES (1, '가입');
INSERT INTO member_status
VALUES (2, '탈퇴');
INSERT INTO member_status
VALUES (3, '휴면');

-- member_authority 테이블 데이터 추가

INSERT INTO member_authority
VALUES (1, 'ADMIN');
INSERT INTO member_authority
VALUES (2, 'MEMBER');