-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.17 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- bookhub 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `bookhub` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookhub`;

-- 테이블 bookhub.book_list 구조 내보내기
CREATE TABLE IF NOT EXISTS `book_list` (
  `book_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '책등록순서',
  `book_isbn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '책ISBN',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '회원ID',
  `book_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '책이름',
  `book_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '책커버이미지',
  `book_author` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '책저자',
  `book_content` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '책설명',
  `book_end_page` int(11) DEFAULT NULL COMMENT '책페이지수',
  `book_writedate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
  PRIMARY KEY (`book_num`),
  UNIQUE KEY `book_isbn` (`book_isbn`),
  KEY `FK_book_list_user` (`user_id`),
  CONSTRAINT `FK_book_list_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 bookhub.book_list:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `book_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_list` ENABLE KEYS */;

-- 테이블 bookhub.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `review_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '리뷰등록순서',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '회원ID',
  `book_num` int(11) NOT NULL COMMENT '책등록순서',
  `review_content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '리뷰내용',
  `review_writedate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰등록날짜',
  PRIMARY KEY (`review_num`),
  KEY `fk_comment_user1_idx` (`user_id`),
  KEY `fk_comment_book_list1_idx` (`book_num`),
  CONSTRAINT `fk_comment_book_list1` FOREIGN KEY (`book_num`) REFERENCES `book_list` (`book_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 bookhub.review:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;

-- 테이블 bookhub.review_like 구조 내보내기
CREATE TABLE IF NOT EXISTS `review_like` (
  `review_like_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '좋아요등록순서',
  `review_num` int(11) NOT NULL COMMENT '리뷰등록순서',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '회원ID',
  `review_like_writedate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '좋아요등록날짜',
  `review_like_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '좋아요철회여부',
  PRIMARY KEY (`review_like_num`),
  KEY `fk_comment_like_user1_idx` (`user_id`),
  KEY `review_num` (`review_num`),
  CONSTRAINT `fk_comment_like_review1` FOREIGN KEY (`review_num`) REFERENCES `review` (`review_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_like_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 bookhub.review_like:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `review_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `review_like` ENABLE KEYS */;

-- 테이블 bookhub.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `user_no` int(10) NOT NULL AUTO_INCREMENT COMMENT '등록순서',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '회원ID',
  `user_pw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '비밀번호',
  `user_nick` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '닉네임',
  `user_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '이메일',
  `user_tel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '연락처',
  `user_private` tinyint(1) unsigned zerofill DEFAULT '1' COMMENT '1이면 캘린더공개',
  `user_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '프로필사진',
  `user_regdate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
  `user_role` enum('UN_USER','USER','ADMIN') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'UN_USER' COMMENT '회원권한',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_no` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 bookhub.user:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 테이블 bookhub.user_book 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_book` (
  `user_book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '독서활동ID',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '회원ID',
  `book_num` int(11) NOT NULL COMMENT '책등록순서',
  `book_start_page` int(11) DEFAULT NULL COMMENT '책시작페이지',
  `book_now_page` int(11) DEFAULT NULL COMMENT '책현재페이지',
  `book_end_page` int(11) DEFAULT NULL COMMENT '책마지막페이지',
  PRIMARY KEY (`user_book_id`),
  KEY `FK_user_book_user` (`user_id`),
  KEY `FK_user_book_book_list` (`book_num`),
  CONSTRAINT `FK_user_book_book_list` FOREIGN KEY (`book_num`) REFERENCES `book_list` (`book_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_book_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 bookhub.user_book:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_book` ENABLE KEYS */;

-- 테이블 bookhub.user_calender 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_calender` (
  `cal_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '캘린더번호',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '회원ID',
  `book_num` int(11) NOT NULL COMMENT '책등록순서',
  `cal_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '캘린더제목',
  `cal_content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '캘린더설명',
  `cal_year` int(11) DEFAULT NULL COMMENT '캘린더년도',
  `cal_month` int(11) DEFAULT NULL COMMENT '캘린더월',
  `cal_day` int(11) DEFAULT NULL COMMENT '캘린더일',
  `cal_combo` int(11) DEFAULT NULL COMMENT '콤보',
  `cal_writedata` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '캘린더등록일자',
  PRIMARY KEY (`cal_num`) USING BTREE,
  KEY `fk_user_calender_book_list1_idx` (`book_num`),
  KEY `fk_user_calender_user1` (`user_id`),
  CONSTRAINT `fk_user_calender_book_list1` FOREIGN KEY (`book_num`) REFERENCES `book_list` (`book_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_calender_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 bookhub.user_calender:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_calender` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_calender` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
