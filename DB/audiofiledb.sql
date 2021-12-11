-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema audiofiledb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `audiofiledb` ;

-- -----------------------------------------------------
-- Schema audiofiledb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `audiofiledb` DEFAULT CHARACTER SET utf8 ;
USE `audiofiledb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `create_date` DATETIME NULL,
  `image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `album` ;

CREATE TABLE IF NOT EXISTS `album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `description` TEXT NULL,
  `release_date` DATETIME NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_album_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  CONSTRAINT `fk_album_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `song`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `song` ;

CREATE TABLE IF NOT EXISTS `song` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lyrics` TEXT NULL,
  `duration_seconds` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_song_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_song_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_album` ;

CREATE TABLE IF NOT EXISTS `favorite_album` (
  `user_id` INT NOT NULL,
  `album_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `album_id`),
  INDEX `fk_user_has_album_album1_idx` (`album_id` ASC),
  INDEX `fk_user_has_album_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `album_id_UNIQUE` (`album_id` ASC),
  CONSTRAINT `fk_user_has_album_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_album_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_song`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_song` ;

CREATE TABLE IF NOT EXISTS `favorite_song` (
  `user_id` INT NOT NULL,
  `song_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `song_id`),
  INDEX `fk_user_has_song_song1_idx` (`song_id` ASC),
  INDEX `fk_user_has_song_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  UNIQUE INDEX `song_id_UNIQUE` (`song_id` ASC),
  CONSTRAINT `fk_user_has_song_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_song_song1`
    FOREIGN KEY (`song_id`)
    REFERENCES `song` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `genre` ;

CREATE TABLE IF NOT EXISTS `genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`name` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `album_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `album_genre` ;

CREATE TABLE IF NOT EXISTS `album_genre` (
  `album_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  PRIMARY KEY (`album_id`, `genre_id`),
  INDEX `fk_album_has_genre_genre1_idx` (`genre_id` ASC),
  INDEX `fk_album_has_genre_album1_idx` (`album_id` ASC),
  UNIQUE INDEX `album_id_UNIQUE` (`album_id` ASC),
  UNIQUE INDEX `genre_id_UNIQUE` (`genre_id` ASC),
  CONSTRAINT `fk_album_has_genre_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_has_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `artist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artist` ;

CREATE TABLE IF NOT EXISTS `artist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_artist_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_artist_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `song_artist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `song_artist` ;

CREATE TABLE IF NOT EXISTS `song_artist` (
  `artist_id` INT NOT NULL,
  `song_id` INT NOT NULL,
  PRIMARY KEY (`artist_id`, `song_id`),
  INDEX `fk_artist_has_song_song1_idx` (`song_id` ASC),
  INDEX `fk_artist_has_song_artist1_idx` (`artist_id` ASC),
  UNIQUE INDEX `artist_id_UNIQUE` (`artist_id` ASC),
  UNIQUE INDEX `song_id_UNIQUE` (`song_id` ASC),
  CONSTRAINT `fk_artist_has_song_artist1`
    FOREIGN KEY (`artist_id`)
    REFERENCES `artist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_artist_has_song_song1`
    FOREIGN KEY (`song_id`)
    REFERENCES `song` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `album_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `album_rating` ;

CREATE TABLE IF NOT EXISTS `album_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `album_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `description` TEXT NULL,
  `rating_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_album_rating_album1_idx` (`album_id` ASC),
  INDEX `fk_album_rating_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `album_id_UNIQUE` (`album_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_album_rating_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `song_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `song_rating` ;

CREATE TABLE IF NOT EXISTS `song_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `song_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `description` TEXT NULL,
  `rating_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_song_rating_song1_idx` (`song_id` ASC),
  INDEX `fk_song_rating_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `song_id_UNIQUE` (`song_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_song_rating_song1`
    FOREIGN KEY (`song_id`)
    REFERENCES `song` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_song_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `album_song`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `album_song` ;

CREATE TABLE IF NOT EXISTS `album_song` (
  `album_id` INT NOT NULL,
  `song_id` INT NOT NULL,
  PRIMARY KEY (`album_id`, `song_id`),
  INDEX `fk_song_has_album_album1_idx` (`album_id` ASC),
  INDEX `fk_song_has_album_song1_idx` (`song_id` ASC),
  UNIQUE INDEX `album_id_UNIQUE` (`album_id` ASC),
  UNIQUE INDEX `song_id_UNIQUE` (`song_id` ASC),
  CONSTRAINT `fk_song_has_album_song1`
    FOREIGN KEY (`song_id`)
    REFERENCES `song` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_song_has_album_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `album_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `album_comment` ;

CREATE TABLE IF NOT EXISTS `album_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `album_id` INT NOT NULL,
  `comment` TEXT NULL,
  `comment_date` DATETIME NULL,
  `in_replay_to` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_album_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_album_comment_album1_idx` (`album_id` ASC),
  INDEX `fk_album_comment_album_comment1_idx` (`in_replay_to` ASC),
  CONSTRAINT `fk_album_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_comment_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_comment_album_comment1`
    FOREIGN KEY (`in_replay_to`)
    REFERENCES `album_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_friend` ;

CREATE TABLE IF NOT EXISTS `user_friend` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_has_user_user2_idx` (`friend_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS audiofile@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'audiofile'@'localhost' IDENTIFIED BY 'audiofile';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'audiofile'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `create_date`, `image_url`, `enabled`, `role`) VALUES (1, 'admin', 'admin', NULL, NULL, 'admin@gmail.com', NULL, NULL, NULL, NULL);

COMMIT;

