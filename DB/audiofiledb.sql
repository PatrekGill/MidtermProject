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
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
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
  `create_date` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_artist_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_artist_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `create_date` DATETIME NULL,
  `artist_id` INT NOT NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_album_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  INDEX `fk_album_artist1_idx` (`artist_id` ASC),
  CONSTRAINT `fk_album_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_artist1`
    FOREIGN KEY (`artist_id`)
    REFERENCES `artist` (`id`)
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
  `create_date` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`),
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
  UNIQUE INDEX `type_UNIQUE` (`name` ASC))
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
-- Table `song_artist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `song_artist` ;

CREATE TABLE IF NOT EXISTS `song_artist` (
  `artist_id` INT NOT NULL,
  `song_id` INT NOT NULL,
  PRIMARY KEY (`artist_id`, `song_id`),
  INDEX `fk_artist_has_song_song1_idx` (`song_id` ASC),
  INDEX `fk_artist_has_song_artist1_idx` (`artist_id` ASC),
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
  `rating` INT NOT NULL,
  `album_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `description` TEXT NULL,
  `rating_date` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_album_rating_album1_idx` (`album_id` ASC),
  INDEX `fk_album_rating_user1_idx` (`user_id` ASC),
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
  `rating` INT NOT NULL,
  `song_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `description` TEXT NULL,
  `rating_date` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_song_rating_song1_idx` (`song_id` ASC),
  INDEX `fk_song_rating_user1_idx` (`user_id` ASC),
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
  `in_reply_to` INT NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_album_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_album_comment_album1_idx` (`album_id` ASC),
  INDEX `fk_album_comment_album_comment1_idx` (`in_reply_to` ASC),
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
    FOREIGN KEY (`in_reply_to`)
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
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `create_date`, `image_url`, `enabled`, `role`) VALUES (1, 'admin', 'admin', 'Kings', 'Jam', 'admin@gmail.com', '2021-09-21', 'Look at this awesome pose!', 1, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `create_date`, `image_url`, `enabled`, `role`) VALUES (2, 'king', 'king2021', 'king', 'jinx', 'king2021@gmail.com', '2021-12-14', 'why not', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `artist`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `artist` (`id`, `name`, `user_id`, `image_url`, `description`, `create_date`, `update_time`) VALUES (1, 'jimmy buffett', 1, 'Musician_Jimmy_Buffet_performs_for_members_of_Joint_Task_Force_Haiti_behind_the_U.S._Embassy_in_Port-au-Prince,_Haiti,_March_3,_2010_100303-N-HX866-001.jpg', 'James William Buffett (born December 25, 1946) is an American singer-songwriter,[10] musician, author, actor, and businessman. He is best known for his music, which often portrays an \"island escapism\" lifestyle. Together with his Coral Reefer Band, Buffett has recorded hit songs including \"Margaritaville\" (ranked 234th on the Recording Industry Association of America\'s list of \"Songs of the Century\") and \"Come Monday\". He has a devoted base of fans known as \"Parrotheads\".\n\nAside from his career in music, Buffett is also a bestselling author and was involved in two restaurant chains named after two of his best-known songs; he currently owns the Margaritaville Cafe restaurant chain and co-developed the now defunct Cheeseburger in Paradise restaurant chain. Buffett is one of the world\'s richest musicians, with a net worth of $900 million.', '2019-08-11', NULL);
INSERT INTO `artist` (`id`, `name`, `user_id`, `image_url`, `description`, `create_date`, `update_time`) VALUES (2, 'Mac DeMarco', 1, 'Mac_DeMarco_2019_(48210650642)_(cropped).jpg', 'McBriare Samuel Lanyon \"Mac\" DeMarco (born Vernor Winfield McBriare Smith IV; April 30, 1990) is a Canadian singer-songwriter, multi-instrumentalist and producer.[3] DeMarco has released six full-length studio albums, his debut Rock and Roll Night Club (2012), 2 (2012), Salad Days (2014), Another One (2015), This Old Dog (2017), and Here Comes the Cowboy (2019). His style of music has been described as \"blue wave\" and \"slacker rock\" or, by DeMarco himself, \"jizz jazz\".', '2019-08-12', NULL);
INSERT INTO `artist` (`id`, `name`, `user_id`, `image_url`, `description`, `create_date`, `update_time`) VALUES (3, 'Adele', 2, 'https://upload.wikimedia.org/wikipedia/commons/5/52/Adele_for_Vogue_in_2021.png', 'Adele Laurie Blue Adkins MBE (/əˈdɛl/; born 5 May 1988) is an English singer and songwriter. She is one of the world\'s best-selling music artists, with sales of over 120 million records. After graduating in arts from the BRIT School in 2006, Adele signed a record deal with XL Recordings. Her debut album, 19, was released in 2008 and spawned the UK top-five singles \"Chasing Pavements\" and \"Make You Feel My Love\". The album was certified 8× platinum in the UK and triple platinum in the US. Adele was honored with the Brit Award for Rising Star as well as the Grammy Award for Best New Artist.', '2021-12-14', NULL);
INSERT INTO `artist` (`id`, `name`, `user_id`, `image_url`, `description`, `create_date`, `update_time`) VALUES (4, 'Miles Davis', 2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Miles_Davis_by_Palumbo_cropped.jpg/440px-Miles_Davis_by_Palumbo_cropped.jpg', 'Miles Dewey Davis III (May 26, 1926 – September 28, 1991) was an American trumpeter, bandleader, and composer. He is among the most influential and acclaimed figures in the history of jazz and 20th-century music. Davis adopted a variety of musical directions in a five-decade career that kept him at the forefront of many major stylistic developments in jazz.', '2021-12-14', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `album`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `album` (`id`, `title`, `description`, `release_date`, `image_url`, `user_id`, `create_date`, `artist_id`, `update_time`) VALUES (1, 'A1A', 'A1A is the fifth studio album by American popular music singer-songwriter Jimmy Buffett and the third major label album in Buffett\'s Don Gant-produced \"Key West phase.\" It was initially released in December 1974 as Dunhill DS-50183 and later re-released on Dunhill\'s successor labels ABC and MCA.\n\nThe album is named for Florida State Road A1A that runs mostly along the Atlantic Ocean and is the main road through most oceanfront towns. It is also referenced in the song \"Trying To Reason With Hurricane Season\". The album\'s original back cover is a photograph of a section of A1A.', '1974-12-01', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Musician_Jimmy_Buffet_performs_for_members_of_Joint_Task_Force_Haiti_behind_the_U.S._Embassy_in_Port-au-Prince%2C_Haiti%2C_March_3%2C_2010_100303-N-HX866-001.jpg/800px-Musician_Jimmy_Buffet_performs_for_members_of_Joint_Task_Force_Haiti_behind_the_U.S._Embassy_in_Port-au-Prince%2C_Haiti%2C_March_3%2C_2010_100303-N-HX866-001.jpg', 1, '2019-08-30', 1, NULL);
INSERT INTO `album` (`id`, `title`, `description`, `release_date`, `image_url`, `user_id`, `create_date`, `artist_id`, `update_time`) VALUES (2, '2', '2 is the debut full-length studio album by Canadian musician Mac DeMarco. It was recorded in June 2012, and released in October 2012 on the Captured Tracks label.', '2012-10-16', 'https://upload.wikimedia.org/wikipedia/en/1/15/Macdemarco2cover.png', 1, '2019-08-28', 2, NULL);
INSERT INTO `album` (`id`, `title`, `description`, `release_date`, `image_url`, `user_id`, `create_date`, `artist_id`, `update_time`) VALUES (3, '21', '21 is the second studio album by English singer-songwriter Adele. It was released on 24 January 2011[1] in Europe by XL Recordings and on 22 February 2011 in North America by Columbia Records. The album was named after the age of the singer during its production. 21 shares the Motown/soul influences of her 2008 debut album 19, but also draws influence from the American country and Southern blues music that Adele started listening to while on the North American leg of her tour An Evening with Adele. Composed in the aftermath of the singer\'s separation from her then partner, the album exemplifies the near dormant tradition of the confessional singer-songwriter, exploring themes of heartbreak, healing and forgiveness.', '2011-01-24', 'https://upload.wikimedia.org/wikipedia/en/1/1b/Adele_-_21.png', 2, '2021-12-14', 3, NULL);
INSERT INTO `album` (`id`, `title`, `description`, `release_date`, `image_url`, `user_id`, `create_date`, `artist_id`, `update_time`) VALUES (4, 'Kind of Blue', 'Kind of Blue is a studio album by American jazz trumpeter and composer Miles Davis. It was recorded on March 2 and April 22, 1959, at Columbia\'s 30th Street Studio in New York City, and released on August 17 of that year by Columbia Records. For the recording, Davis led a sextet featuring saxophonists John Coltrane and Julian \"Cannonball\" Adderley, pianist Bill Evans, bassist Paul Chambers, and drummer Jimmy Cobb, with new band pianist Wynton Kelly appearing on one track – \"Freddie Freeloader\" – in place of Evans.', '1959-08-17', 'https://upload.wikimedia.org/wikipedia/en/9/9c/MilesDavisKindofBlue.jpg', 2, '2021-12-14', 4, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `song`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (1, 'Making Music for Money', 'I woke up this morning\nI was tired as I could be\nI guess I was counting my money\nWhen I should have been counting sheep\nMy agent, he just called me\nAnd then told me what I should be\nHe said that I should make my music for money\nInstead of making my music for me\nAnd I said I know that it may sound funny\nBut money don\'t mean nothin\' to me\nI won\'t make my music for money\nI\'m gonna make my music for me\nNow people only like the love songs\nRock and rollin\' not too long\nHe said, kid you got to be commerical\nIf you wanna turn the people on\nNow turning on the people\nWhat a beautiful place to be\nBut if I spend my time makin\' memorable rhyme\nWho\'s gonna turn on me?\nAnd I said I know that it may sound funny\nBut money don\'t mean nothin\' to me\nI won\'t make my music for money\nI\'m gonna make my music for me\nNow, I went up the country\nAnd I\'ll tell you about the scene\nI found a place with much charm and much grace\nWas untouched by the music machine\nThe people where havin\' a good time\nEverybody singing along\nAnd nobody cared if nobody gave\nOne more penny for singin\' a song\nAnd I said I know that it may sound funny\nBut money don\'t mean nothin\' to me\nI won\'t make my music for money\nI\'m gonna make my music for me\nI said I know that it may sound funny, yeah\nMoney don\'t mean nothin\' to me\nI won\'t make my music for money\nI\'m gonna make my music for, make my music for\nMake my music for me\n', 240, 1, '2019-06-01', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (2, 'Door Number Three', 'Oh I took a wrong turn, it was the right Oh I took a wrong turn, it was the right turn\nMy turn to have me a ball\nBoys at the shop told me just where to stop\nIf I wanted to play for it all\nI didn\'t know I\'d find her on daytime TV\nMy whole world lies waiting behind door number three\n\nI chose my apparel, wore a beer barrel\nAnd they rolled me to the very first row\nI held a big sign that said \"Kiss me I\'m a baker,\nand Monty I sure need the dough!\"\nThen I grabbed that sucker by the throat\nUntil he called on me\nCause my whole world lies waiting behind door number three\n\nAnd I don\'t want what Jay\'s got on his table\nOr the box Carol Merrill points to on the floor\nNo, I\'ll hold out just as long as I am able\nUntil I can unlock that lucky door\nWell, she\'s no big deal to most folks\nBut she\'s everything to me\nCause my whole world lies waiting behind door number three\n\nOh Monty, Monty, Monty, I am walking down your hall\nGot beat, I lost my seat but I\'m not a man to crawl\nNo I didn\'t get rich you son of a bitch\nI\'ll be back just wait and see\nCause my whole world lies waiting behind door number three\nYes my whole world lies waiting behind door number three\nMy turn to have me a ball\nBoys at the shop told me just where to stop\nIf I wanted to play for it all\nI didn\'t know I\'d find her on daytime TV\nMy whole world lies waiting behind door number three\n\nI chose my apparel, wore a beer barrel\nAnd they rolled me to the very first row\nI held a big sign that said \"Kiss me I\'m a baker,\nand Monty I sure need the dough!\"\nThen I grabbed that sucker by the throat\nUntil he called on me\nCause my whole world lies waiting behind door number three\n\nAnd I don\'t want what Jay\'s got on his table\nOr the box Carol Merrill points to on the floor\nNo, I\'ll hold out just as long as I am able\nUntil I can unlock that lucky door\nWell, she\'s no big deal to most folks\nBut she\'s everything to me\nCause my whole world lies waiting behind door number three\n\nOh Monty, Monty, Monty, I am walking down your hall\nGot beat, I lost my seat but I\'m not a man to crawl\nNo I didn\'t get rich you son of a bitch\nI\'ll be back just wait and see\nCause my whole world lies waiting behind door number three\nYes my whole world lies waiting behind door number three', 180, 1, '2019-06-02', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (3, 'Dallas', 'If you ever get the chance to go to Dallas\nTake it from me pass it by\n\'Cause you\'ll only sing the blues down in Dallas\nTake it from me don\'t go and cry\nAnd I\'m leavin\' this town as soon as I can\nGonna stop off for awhile and see my woman\nPeople do you wrong down in Dallas\nI know well they\'ve done it to me\nStealin\' all your bread, they\'re so callous\nI know well just look and see\nOh, and I\'m leavin\' this town as soon as I can\nGonna stop off for awhile and see my woman\nAnd people like me just can\'t be free\nThe promo man won\'t let us be-ee\nIf the people who knew could get away\nI\'m real sure they\'d leave today\nYeah, now come on down and lose your mental balance\nLook at me half crazy now\nOh, talkin\' to chairs is strange and I know it\nLook at me I\'m doin\' it know\nYeah, and I\'m leavin\' this town as soon as I can\nGonna stop off for awhile and see my woman\nYeah, leavin\' this town as soon as I can\nGonna stop off for awhile and see my woman\nWhoa, leavin\' this town as soon as I can\nGonna stop off for awhile and see my woman', 205, 1, '2019-06-03', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (4, 'Presents to Send You', 'Well now I\'m in love with a fast moving angel\nDresses like the city girls do\nWhen we\'re apart there\'s no ache in my heart\nWhen we\'re together we\'re a hell of a crew\nAnd I got presents to send you\nEven got money to lend you\nBut honey I could never ever pretend\nYour not there on my mind\nThere sits a fifth of tequila\nGod I swore I\'d never drink it again\nBut my last little bout\nI had my hair pulled out by a man\nWho wasn\'t really my friend\nAnd I know I\'ll never see him again\nAnd I got presents to send you\nEven got money to lend you\nBut honey I could never ever pretend\nYour not there on my mind\nThought I might sail down to Bridgetown\nSpend some time in the Barbados sun\nBut my plans took a skid when I smoked the whole lid\nAnd wound up where I\'d begun\nYes and\nAnd I got presents to send you\nEven got money to lend you\nBut honey I could never ever pretend\nYour not there on my mind\n', 160, 1, '2019-06-04', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (5, 'Stories We Could Tell', 'Talkin\' to myself againTalkin\' to myself again\nWonderin\' if this travelin\' is good\nIs they\'re something else a\' doin\'\nWe\'d be doin\' if we could\nBut all the stories we could tell\nIf it all blows up and goes to hell\nI wish that we could sit upon the bed in some motel\nListen to the stories we could tell\nStared at that guitar in that museum in Tennessee\nNameplate on the glass brought back twenty melodies\nScars upon the face told about all the times he fell\nSingin\' all the stories he could tell\nAll the stories he could tell\nAnd I\'ll bet you it still rings like a bell\nI wish that we could sit upon the bed in some motel\nAnd listen to the stories it could tell\nSo if you\'re on the road trackin\' down your every night\nPlayin\' for a livin\' beneath brightly colored lights\nIf you ever wonder why you ride the carousel\nYou do it for the stories you can tell\nAll the stories we could tell\nAnd if it all blows up and goes to hell\nI wish that we could sit upon the bed in some motel\nJust listen to the stories we could\nYes, I wish that we could sit upon a bed in some motel\nAnd listen to the stories it could tellWonderin\' if this travelin\' is good\nIs they\'re something else a\' doin\'\nWe\'d be doin\' if we could\nBut all the stories we could tell\nIf it all blows up and goes to hell\nI wish that we could sit upon the bed in some motel\nListen to the stories we could tell\nStared at that guitar in that museum in Tennessee\nNameplate on the glass brought back twenty melodies\nScars upon the face told about all the times he fell\nSingin\' all the stories he could tell\nAll the stories he could tell\nAnd I\'ll bet you it still rings like a bell\nI wish that we could sit upon the bed in some motel\nAnd listen to the stories it could tell\nSo if you\'re on the road trackin\' down your every night\nPlayin\' for a livin\' beneath brightly colored lights\nIf you ever wonder why you ride the carousel\nYou do it for the stories you can tell\nAll the stories we could tell\nAnd if it all blows up and goes to hell\nI wish that we could sit upon the bed in some motel\nJust listen to the stories we could\nYes, I wish that we could sit upon a bed in some motel\nAnd listen to the stories it could tell', 198, 1, '2019-06-05', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (6, 'Life Is Just a Tire Swing', 'I remember the smell of the creosote plant,\nWhen we\'d have to eat on Easter with my\nCrazy old uncle and aunt.\nThey lived in a big house Ante Bellum style,\nAnd the wind would blow across the old bayou,\nAnd I was a tranquil little child.\nLife was just a tire swing.\n\'Jambalaya\' was the only song I could sing.\nBlackberry pickin\', eatin\' fried chicken,\nAnd I never knew a thing about pain.\nLife was just a tire swing.\nIn a few summers my folks packed me off to camp;\nYeah, me and my cousin\' Baxter\nIn our pup tent with a lamp.\nAnd in a few days Baxter went home,\nAnd he left me by myself.\nAnd I knew that I\'d stay, it was better that way,\nAnd I could get along without any help.\nLife was just a tire swing.\nAnd I\'ve never been west of New Orleans\nNor east of Pensacola.\nMy only contact with the outside\nWorld was a n RCA Victrola.\nElvis would sing and then I\'d dream about\nExpensive cars, and who would\'ve figured twenty\nYears later I\'d be rubbing shoulders with the stars.\nLife was just a tire swing.\n\'Jambalaya\' was the only song I could sing.\nChasin\' after sparrows with rubber tip arrows,\nKnowin\' I could never hurt a thing,\nAnd life was just a tire swing.\nThen the other morning on some Illinois road\nI fell asleep at the wheel,\nBut was quickly wakened up by a \'Ma Bell\'\nTelephone pole, and a bunch of Grant Wood\nFaces screaming, \'Is he still alive?\'\nBut through the window could see\nIt hangin\' from a tree, and I knew\nI had survived.\nLife was just a tire swing.\nJambalaya\'s still the best song that I can sing.\nBlackberry pickin\', eatin\' fried chicken,\nAnd I finally learned a lot about pain,\n\'cause life is just a tire swing.\n', 180, 1, '2019-06-06', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (7, 'A Pirate Looks at Forty', 'Mother, mother ocean, I have heard you call\nWanted to sail upon your waters since I was three feet tall\nYou\'ve seen it all, you\'ve seen it all\nWatched the men who rode you switch from sails to steam\nAnd in your belly you hold the treasures few have ever seen\nMost of them dream, most of them dream\nYes I am a pirate, two hundred years too late\nThe cannons don\'t thunder, there\'s nothing to plunder\nI\'m an over-forty victim of fate\nArriving too late, arriving too late\nI\'ve done a bit of smuggling, I\'ve run my share of grass\nI made enough money to buy Miami, but I pissed it away so fast\nNever meant to last, never meant to last\nAnd I have been drunk now for over two weeks\nI passed out and I rallied and I sprung a few leaks\nBut I got stop wishing, got to go fishing, down to rock bottom again\nJust a few friends, just a few friends\nI go for younger women, lived with several a while\nThough I ran them away, they\'d come back one day\nStill could manage to smile\nJust takes a while, just takes a while\nMother, mother ocean, after all the years I\'ve found\nMy occupational hazard being my occupation\'s just not around\nI feel like I\'ve drowned, gonna head uptown\nI feel like I\'ve drowned, gonna head uptown', 237, 1, '2019-06-07', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (8, 'Migration', 'Whoa, lookin\' back at my background\nTryin\' to figure out how I ever got here\nSome things are still a mystery to me\nWhile others are much too clear\nI\'m just livin\' in the sunshine\nStay contented most of the time\nYeah listenin\' to Murphy, Walker, and Willis\nSing me their Texas rhymes\nMost of the people who retire in Florida\nAre wrinkled and they lean on a crutch\nAnd mobile homes are smotherin\' my Keys\nI hate those bastards so much\nI wish a summer squall would blow them all\nThe way up to fantasy land\nYeah they\'re ugly and square, they don\'t belong here\nThey looked a lot better as beer cans\nYeah, and that\'s why it\'s still a mystery to me\nWhy some people live like they do\nSo many nice things happenin\' out there\nThey never even seen the clues\nWhoa but we\'re doin\' fine, we can travel and rhyme\nI know we been doin\' our part\nGot a Caribbean soul I can barely control\nAnd some Texas hidden here in my heart\nWell now I might have joined the Merchant Marine\nIf I hadn\'t learned how to sing\nAnd on top of all that I got married too early\n\'Cost me much more than a ring\nBut now those crazy days are over\nJust gotta learn from the wrong things you done\nI came off the rebound, started lookin\' around\nFigured out it\'s time to have a little fun\nYeah, and that\'s why it\'s still a mystery to me\nWhy some people live like they do\nSo many nice things happenin\' out there\nThey never even seen the clues\nWhoa but we\'re doin\' fine, we can travel and rhyme\nI know we been doin\' our part\nGot a Caribbean soul I can barely control\nAnd some Texas hidden here in my heart\nWell now if I ever live to be an old man\nI\'m gonna sail down to Martinique\nI\'m gonna buy me a sweat-stained Bogart suit\nAnd an African parakeet\nAnd then I\'ll sit him on my shoulder\nAnd open up my trusty old mind\nI gonna teach him how to cuss, teach him how to fuss\nAnd pull the cork out of a bottle of wine\nYeah, and that\'s why it\'s still a mystery to me\nWhy some people live like they do\nSo many nice things happenin\' out there\nThey never even seen the clues\nYeah but we\'re doin\' fine, we can travel and rhyme\nI know we been doin\' our part\nGot a Caribbean soul I can barely control\nAnd some Texas hidden here in my heart\nYeah, got a Caribbean soul I can barely control\nAnd some Texas hidden here in my heart', 253, 1, '2019-06-08', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (9, 'Trying to Reason with Hurricane Season', 'Squalls out on the gulf stream\nBig storm commin\' soon\nPassed out in my hammock\nGod, I slept till way past noon\nStood up and tried to focus\nI hoped I wouldn\'t have to move far\nSure could use a bloody mary\nSo I stumbled over to Loui\'s back yard\nAnd now I must confess\nI could use some rest\nI can\'t run at this pace very long\nYes it\'s quite insane\nThink I hurt my brain\nBut it cleans me out, then I can go on\nThere\'s somthing \'bout this Sunday\nIt\'s the most peculiar gray\nStrollin\' down the avenue that\'s known as A1A\nFeelin tired, then I got inspired\nI knew it wouldn\'t last long\nSo all alone I walked back home\nSat on the beach and there I made up this song\nAnd now I must confess\nI could use some rest\nI can\'t run at this pace very long\nYes it\'s quite insane\nThink I hurt my brain\nBut it cleans me out, then I can go on\nWell the wind is blowin\' harder now\nFifty knots or there abouts\nThere\'s white caps on the ocean\nAnd I\'m watchin\' for water spouts\nIt\'s time to close the shutters\nIt\'s time to go inside\nIn a week I\'ll be in gay par-ey\nHell that\'s a mighty long airplane ride\nAnd now I must confess\nI could use some rest\nI can\'t run at this pace very long\nYes it\'s quite insane\nThink I hurt my brain\nBut it cleans me out, then I can go on\nYes it cleans me out, then I can go on\n', 261, 1, '2019-06-09', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (10, 'Nautical Wheelers', 'Nautical wheelers who call themselves sailors\nPlay fiddle tunes under the stars.\nPetticoats rustle, working shoes scuffle,\nHustle on down to the bars.\nWhere the jukebox is blastin\'\nAnd the liquor is flowing\nAn occasional bottle of wine.\nThat\'s cause everyone here is just more than\nContented to be living and dying in three quarter time.\nAnd It\'s dance with me, dance with me\nNautical wheelers.\nTake me to stars that you know.\nCome on and dance with me,\nNautical wheelers\nI want so badly to go.\nWell the left foot it\'ll follow where the\nRight foot has traveled down to the\nSidewalks unglued.\nAnd into the street of my city so neat,\nWhere nobody cares what you do.\nAnd Sonja\'s just grinnin\'\nAnd Phil is ecstatic and\nMason has jumped in the sea.\nWhile I\'m hangin\' on to a line\nFrom my sailboat oh,\nNautical Wheelers save me.\nAnd It\'s dance with me, dance with me\nNautical wheelers.\nTake me to stars that you know.\nCome on and dance with me,\nNautical wheelers\nI want so badly to go.\nWell the sunrise\'ll bring on the\nSleep that\'s escaped us and\nEveryone\'s off to their bed.\nThere\'ll be huggin\' and sqeezin\',\nA little pleasin\' and teasin\'\nAnd rubbin\' of each others\' head.\nSo won\'t you dream on comrades\nSeems nothing affects you,\nNothing, no reason nor rhyme.\nThat\'s cause everyone here is just more than\nContented to be living and dying in three quarter time.', 215, 1, '2019-06-10', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (11, 'Tin Cup Chalice', 'I want to go back to the island\nWhere the shrimp boats tie up to the pilin\'\nGive me oysters and beer for dinner every day of the year\nAnd I\'ll feel fine, I\'ll feel fine\n\'Cause I want to be there\nWant to go back down and lie beside the sea there\nWith a tin cup for a chalice\nFill it up with good red wine\nAnd I\'m-a chewin\' on a honeysuckle vine\nYeah, now the sun goes slidin\' \'cross the water\nSailboats, they go searchin\' for the breeze\nSalt air it ain\'t thin\nIt can stick right to your skin\nAnd make you feel fine\nIt makes you feel fine\n\'Cause I want to be there\nWanna go back down and get high by the sea there\nWith a tin cup for a chalice\nFill it up with good red wine\nAnd I\'m-a chewin\' on a honeysuckle vine\nYes, and now you heard my strange proposal\nGet that Packard up and let\'s move\nI wana be there before the day\nTries to steal away and leave us behind\nI\'ve made up my mind\nAnd I wanna be there\nI want to go back down and lie beside the sea there\nWith a tin cup for a chalice\nFill it up with good red wine\nAnd I\'m-a chewin\' on a honeysuckle vine\nYeah, with a tin cup for a chalice\nFill it up with good red wine\nAnd I\'m-a chewin\' on a honeysuckle vine', 218, 1, '2019-06-11', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (12, 'Cooking Up Something Good', 'Mommy\'s in the kitchen\nCooking up something good\nAnd daddy\'s on the sofa\nPride of the neighborhood\nMy brother\'s in the ballet\nIt seems he\'s got it set\nAnd I\'ll be up at midnight\nWith my cigarette\nOh, when life moves this slowly\nOh, just try and let it go\nOh, when life moves this slowly\nOh, just try and let it go\nDaddy\'s in the basement\nCooking up something fine\nWhile Rick\'s out on the pavement\nFlipping it for dimes\nIf there\'s anything redeeming\nI haven\'t seen it yet\nAnd I\'m still up at midnight\nChewing Nicorette\nOh, when life moves this slowly\nOh, just try and let it go\nOh, when life moves this slowly\nOh, just try and let it go\n', 161, 1, '2019-06-12', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (13, 'Dreaming', 'Someday I\'ll find her\nAnd I\'m still reminded\nMaybe she\'s best in dreams\nShe\'s still the best I\'ve seen\nDreamin\'\nDreamin\'\nDreamin\'\nBaby, I\'m dreamin\'\nOut on her windowsill\nBaby remember\nMaybe I\'m out of luck\nMaybe it\'s running still\nDreamin\'\nDreamin\'\nDreamin\'\nBaby, I\'m dreamin\'', 147, 1, '2019-06-13', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (14, 'Freaking Out the Neighborhood', 'Sorry, mama\nThere are times I get carried away\nBut please don\'t worry\nNext time I\'m home, I\'ll still be the same\nAnd I know it\'s no fun\nWhen your first son\nGets up to no good\nStarts freaking out the neighborhood\nReally, I\'m fine\nNever been better, got no job on the line\nSincerely, don\'t worry\nSame old boy that you hoped you would find\nAnd I know it\'s no fun\nWhen your first son\nGets up to no good\nStarts freaking out the neighborhood', 173, 1, '2019-06-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (15, 'Annie', 'Annie knows when it\'s me\nKnows how I\'m feeling and gives me her healing\nOh Annie, sit down beside me\nLet me confide in you, you know when I\'m blue\nYou know when I\'m lonely and, honey, I\'m lonely\nI\'m going down\nGoing down\nGoing down\nGoing down\nAnnie, daddy won\'t let me\nDaddy won\'t let me go and that\'s why I feel so low\nOh Annie, sit down beside me\nLet me confide in you, you know when I\'m blue\nYou know when I\'m lonely and, honey, I\'m lonely\nI\'m going down\nGoing down\nGoing down\nGoing down', 190, 1, '2019-06-15', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (16, 'Ode to Viceroy', 'ViceroyViceroy\nEarly in the morning\nJust trying to let the sun in\nAnd open up my eyes\nViceroy\nAs it\'s getting later\nHeading for the corner\nAlready running dry\nAnd oh, don\'t let me see you crying\n\'Cause oh, honey, I\'ll smoke you \'til I\'m dying\nViceroy\nDon\'t take me for a fool now\nI\'m only trying to calm down\nJust trying to keep it cool\nViceroy\nAs it\'s getting later\nHeading for the corner\nI\'m leaving it to you\nAnd oh, don\'t let me see you crying\n\'Cause oh, honey, I\'ll smoke you \'til I\'m dying\nEarly in the morning\nJust trying to let the sun in\nAnd open up my eyes\nViceroy\nAs it\'s getting later\nHeading for the corner\nAlready running dry\nAnd oh, don\'t let me see you crying\n\'Cause oh, honey, I\'ll smoke you \'til I\'m dying\nViceroy\nDon\'t take me for a fool now\nI\'m only trying to calm down\nJust trying to keep it cool\nViceroy\nAs it\'s getting later\nHeading for the corner\nI\'m leaving it to you\nAnd oh, don\'t let me see you crying\n\'Cause oh, honey, I\'ll smoke you \'til I\'m dying', 233, 1, '2019-06-16', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (17, 'Robson Girl', 'Maybe when we\'re older\nWe can try this over\nLoving on the sidelines\nAnother one of my kind\nRobson girl, sit down by your daddy\nRobson girl, sit down by your daddy\nNo one else\'s shoulder\nI felt could be colder\nLoving on the sidelines\nJust one straight on my mind\nRobson girl, sit down by your daddy\nRobson girl, sit down by your daddy\nRobson girl, sit down by your daddy\nRobson girl, sit down by your daddy', 176, 1, '2019-06-17', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (18, 'The Stars Keep On Calling My Name', 'Honey, the stars keep on calling my name\nBut don\'t worry, I\'ve told you again and again\nWhen I\'m down, you\'re always the first one to know\nSkipping town, I\'ll take you wherever I go\nAnd I just wanna go\nI just wanna go\nHoney, this town\'s really taking it\'s toll\nBut don\'t worry, I\'m all through with feeling this old\nWhen I\'m down, you\'re always the first on my mind\nSkipping town, let\'s get out and see what we find\n\'Cause I just wanna go\nI just wanna go\nI just wanna go\nI just wanna go', 142, 1, '2019-06-18', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (19, 'My Kind of Woman', 'Oh, baby\nOh, man\nYou\'re makin\' me crazy\nReally drivin\' me mad\nThat\'s alright with me\nIt\'s really no fuss\nAs long as you\'re next to me\nJust the two of us\nYou\'re my, my, my, my kind of woman\nMy, oh my, what a girl\nYou\'re my, my, my, my kind of woman\nAnd I\'m down on my hands and knees\nBeggin\' you please, baby\nShow me your world\nOh, brother\nSweetheart\nI\'m feelin\' so tired\nReally fallin\' apart\nAnd it just don\'t make sense to me\nI really don\'t know\nWhy you stick right next to me\nWherever I go\nYou\'re my, my, my, my kind of woman\nMy, oh my, what a girl\nYou\'re my, my, my, my kind of woman\nAnd I\'m down on my hands and knees\nBeggin\' you please, baby\nShow me your world\n', 190, 1, '2019-06-19', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (20, 'Boe Zaah', NULL, 101, 1, '2019-06-20', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (21, 'Sherrill', 'Getting hard to remember\nGetting hard to recall\nGetting laid off and pushed out\nAnd it ain\'t my baby\'s fault\nSo if you go, don\'t cry\nI\'ll be right there at your side\nSherrill, Sherrill\nGetting laid on the lifeline\nGetting tired, time for bed\nAnd there\'s no use getting worked up\nI\'ll remember all you said\nAnd if you go, don\'t cry\nI\'ll be right there at your side\nSherrill, Sherrill, Sherrill, Sherrill', 149, 1, '2019-06-22', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (22, 'Still Together', 'In time she\'ll see that her and me\nWere meant to be together\nAnd time will pass, it may go fast\nBut we\'ll still be together\nAnd where I go she\'s at my side\nHalf of my life, together\nIt\'s easy love, fits like a glove\nFrom up above, together\nTogether\nTogether\nI\'ve had my share, it\'s just not fair\nThat we should be together\nBut if it\'s fine that I\'ve done my time\nLet\'s walk the line together\nAnd when she\'s low, I\'ll always know\nWe\'ll always go together\nIt\'s easy love, fits like a glove\nFrom up above, together\nTogether\nTogether\nTogether\nTogether\nKiki?\nKiki?\nKiki, hey, time for bed\nYou\'re sleeping\nLet\'s go\nReady?\nI have a bruise there\nOh, you have a little indentation\nFrom the glasses that you fell asleep with on your face\nI love you', 219, 1, '2019-06-21', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (23, 'Rolling in the Deep', 'There\'s a fire starting in my heart\nReaching a fever pitch and it\'s bringing me out the dark\nFinally, I can see you crystal clear\nGo ahead and sell me out and I\'ll lay your ship bare\nSee how I\'ll leave with every piece of you\nDon\'t underestimate the things that I will do\nThere\'s a fire starting in my heart\nReaching a fever pitch and it\'s bring me out the dark\nThe scars of your love remind me of us\nThey keep me thinking that we almost had it all\nThe scars of your love, they leave me breathless\nI can\'t help feeling\nWe could have had it all\n(You\'re gonna wish you never had met me)\nRolling in the deep\n(Tears are gonna fall, rolling in the deep)\nYou had my heart inside of your hands\n(You\'re gonna wish you never had met me)\nAnd you played it to the beat\n(Tears are gonna fall, rolling in the deep)\nBaby, I have no story to be told\nBut I\'ve heard one on you and I\'m gonna make your head burn\nThink of me in the depths of your despair\nMake a home down there as mine sure won\'t be shared\nThe scars of your love remind me of us\n(Tears are gonna fall, rolling in the deep)\nThey keep me thinking that we almost had it all\n(You\'re gonna wish you never had met me)\nThe scars of your love, they leave me breathless\n(Tears are gonna fall, rolling in the deep)\nI can\'t help feeling\nWe could have had it all\n(You\'re gonna wish you never had met me)\nRolling in the deep\n(Tears are gonna fall, rolling in the deep)\nYou had my heart inside of your hands\n(You\'re gonna wish you never had met me)\nAnd you played it to the beat\n(Tears are gonna fall, rolling in the deep)\nCould have had it all\nRolling in the deep\nYou had my heart inside of your hands\nBut you played it with a beating\nThrow your soul through every open door\nCount your blessings to find what you look for\nTurn my sorrow into treasured gold\nYou\'ll pay me back in kind and reap just what you\'ve sown\nWe could have had it all\n(Tears are gonna fall, rolling in the deep)\nWe could have had it all\n(You\'re gonna wish you never had met me)\nIt all, it all, it all\n(Tears are gonna fall, rolling in the deep)\nWe could have had it all\n(You\'re gonna wish you never had met me)\nRolling in the deep\n(Tears are gonna fall, rolling in the deep)\nYou had my heart inside of your hands\n(You\'re gonna wish you never had met me)\nAnd you played it to the beat\n(Tears are gonna fall, rolling in the deep)\nWe could have had it all\n(You\'re gonna wish you never had met me)\nRolling in the deep\n(Tears are gonna fall, rolling in the deep)\nYou had my heart inside of your hands\n(You\'re gonna wish you never had met me)\nBut you played it\nYou played it\nYou played it\nYou played it to the beat', 229, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (24, 'Rumour Has It', 'She, she ain\'t real\nShe ain\'t gon\' be able to love you like I will\nShe is a stranger\nYou and I have history\nOr don\'t you remember\nSure, she\'s got it all\nBut, baby, is that really what you want\nBless your soul, you got your head in the clouds\nShe made a fool out of you\nAnd, boy, she\'s bringing you down\nShe made your heart melt\nBut you\'re cold to the core\nNow rumor has it she ain\'t got your love anymore\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nShe is half your age\nBut I\'m guessing that\'s the reason that you stayed\nI heard you\'ve been missing me\nYou\'ve been telling people things you shouldn\'t be\nLike when we creep out and she ain\'t around\nHaven\'t you heard the rumors\nBless your soul, you got your head in the clouds\nYou made a fool out of me\nAnd, boy, you\'re bringing me down\nYou made my heart melt, yet I\'m cold to the core\nBut rumor has it I\'m the one you\'re leaving her for\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nAll of these words whispered in my ear\nTell a story that I cannot bear to hear\nJust \'cause I said it, it don\'t mean that I meant it\nPeople say crazy things\nJust \'cause I said it, don\'t mean that I meant it\nJust \'cause you heard it\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor-rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it (rumor)\nRumor has it\nBut rumor has it he\'s the one I\'m leaving you for', 223, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (25, 'Turning Tables', 'Close enough to start a war\nAll that I have is on the floor\nGod only knows what we\'re fighting for\nAll that I say, you always say more\nI can\'t keep up with your turning tables\nUnder your thumb I can\'t breathe\nSo, I won\'t let you close enough to hurt me\nNo, I won\'t rescue you to just desert me\nI can\'t give you what you think you gave me\nIt\'s time to say goodbye to turning tables\nTo turning tables\nUnder haunted skies I see you, ooh-ooh\nWhere love is lost your ghost is found\nI braved a hundred storms to leave you\nAs hard as you try, no I will never be knocked down\nI can\'t keep up with your turning tables\nUnder your thumb I can\'t breathe\nSo, I won\'t let you close enough to hurt me\nNo, I won\'t rescue you to just desert me\nI can\'t give you what you think you gave me\nIt\'s time to say goodbye to turning tables\nTurning tables\nNext time I\'ll be braver\nI\'ll be my own saviour\nWhen the thunder calls for me\nNext time I\'ll be braver\nI\'ll be my own saviour\nStanding on my own two feet\nI won\'t let you close enough to hurt me\nNo, I won\'t rescue you to just desert me\nI can\'t give you what you think you gave me\nIt\'s time to say goodbye to turning tables\nTo turning tables\nTurning tables, yeah\nOh, no, no', 250, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (26, 'Don\'t You Remember', 'When will I see you again?\nYou left with no goodbye, not a single word was said\nNo final kiss to seal any sins\nI had no idea of the state we were in\nI know I have a fickle heart and a bitterness\nAnd a wandering eye, and a heaviness in my head\nBut don\'t you remember?\nDon\'t you remember?\nThe reason you loved me before\nBaby, please remember me once more\nWhen was the last time you thought of me?\nOr have you completely erased me from your memory?\nI often think about where I went wrong\nThe more I do, the less I know\nBut I know I have a fickle heart and a bitterness\nAnd a wandering eye, and heaviness in my head\nBut don\'t you remember?\nDon\'t you remember?\nThe reason you loved me before\nBaby, please remember me once more\nGave you the space so you could breathe\nI kept my distance so you would be free\nAnd hope that you find the missing piece\nTo bring you back to me\nWhy don\'t you remember?\nDon\'t you remember?\nThe reason you loved me before\nBaby, please remember me once more\nWhen will I see you again?', 241, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (27, 'Set Fire to the Rain', 'I let it fall, my heart\nAnd as it fell, you rose to claim it\nIt was dark and I was over\nUntil you kissed my lips and you saved me\nMy hands, they were strong\nBut my knees were far too weak\nTo stand in your arms\nWithout falling to your feet\nBut there\'s a side to you\nThat I never knew, never knew\nAll the things you\'d say\nThey were never true, never true\nAnd the games you\'d play\nYou would always win, always win\nBut I set fire to the rain\nWatched it pour as I touched your face\nWell, it burned while I cried\n\'Cause I heard it screaming out your name\nYour name\nWhen laying with you\nI could stay there\nClose my eyes, feel you here forever\nYou and me together, nothing is better\n\'Cause there\'s a side to you\nThat I never knew, never knew\nAll the things you\'d say\nThey were never true, never true\nAnd the games you\'d play\nYou would always win, always win\nBut I set fire to the rain\nWatched it pour as I touched your face\nWell, it burned while I cried\n\'Cause I heard it screaming out your name\nYour name\nI set fire to the rain\nAnd I threw us into the flames\nWhen we fell, something died\n\'Cause I knew that that was the last time\nThe last time\nSometimes I wake up by the door\nThat heart you caught must be waiting for you\nEven now when we\'re already over\nI can\'t help myself from looking for you\nI set fire to the rain\nWatched it pour as I touch your face\nWell, it burned while I cried\n\'Cause I heard it screaming out your name\nYour name\nI set fire to the rain\nAnd I threw us into the flames\nWhen we fell, something died\n\'Cause I knew that that was the last time\nThe last time, oh\nOh, no\nLet it burn, oh-oh-oh\nLet it burn\nLet it burn', 243, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (28, 'He Won\'t Go', 'Some say, I\'ll be better without you\nBut they don\'t know you like I do\nOr at least the sides I thought I knew\nI can\'t bear this time\nIt drags on, as I lose my mind\nReminded by things I find\nLike notes and clothes you left behind\nWake me up, wake me up when all is done\nI won\'t rise until this battle\'s won\nMy dignity\'s become undone\nBut I won\'t go\nI can\'t do it on my own\nIf this ain\'t love, then what is?\nI\'m willing to take the risk\nI won\'t go\nI can\'t do it on my own\nIf this ain\'t love, then what is?\nI\'m willing to take the risk\nSo petrified, I\'m so scared to step into this ride\nWhat if I lose my heart and fail the climb?\nI won\'t forgive me if I give up trying\nI heard his voice today\nI didn\'t know a single word he said\nNot one resemblance to the man I met\nJust a vague and broken boy instead\nBut I won\'t go\nI can\'t do it on my own\nIf this ain\'t love, then what is?\nI\'m willing to take the risk\nI won\'t go\nI can\'t do it on my own\nIf this ain\'t love, then what is?\nI-I\'m willing to take the risk\nThere will be times, we\'ll try and give it up\nBursting at the seams, no doubt\nWe almost fall apart then burn the pieces\nTo watch them turn to dust\nBut nothing will ever taint us\nI won\'t go\nI can\'t do it on my own\nIf this ain\'t love, then what is?\nI\'m willing to take the risk\nI won\'t go\nI can\'t do it on my own\nIf this ain\'t love, then what is?\nI-I\'m willing to take the risk\nWill he, will he still remember me?\nWill he still love me even when he\'s free\nOr will he go back to the place where he\nWill choose the poison over me?\nWhen we spoke yesterday\nHe said to hold my breath and sit and wait\n\"I\'ll be home so soon, I won\'t be late\"\nHe won\'t go\nHe can\'t do it on his own\nIf this ain\'t love, then what is?\nHe\'s willing to take the risk\nSo I won\'t go\nHe can\'t do it on his own\nIf this ain\'t love, then what is?\nI\'m willing to take the risk\n\'Cause he won\'t go\nHe can\'t do it on his own\nIf this ain\'t love, then what is?\nWe\'re willing to take the risk\nI won\'t go\nI can\'t do it on my own\nIf this ain\'t love, then what is?\nI\'m willing to take the risk\n', 267, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (29, 'Take It All', 'Didn\'t I give it all?\nTried my best\nGave you everything I had, everything and no less\nDidn\'t I do it right? Did I let you down?\nMaybe you got too used to having me \'round\nStill how can you walk away\nFrom all my tears\nIt\'s gonna be an empty road without me right here\nBut go on and take it, take it all with you\nDon\'t look back at this crumbling fool\nJust take it all with my love, take it all with my love\nMaybe I should leave, to help you see\nNothing is better than this and this is everything we need\nSo is it over? Is this really it, you\'re giving up so easily\nI thought you loved me more than this\nBut go on, go on, take it, take it all with you\nDon\'t look back at this crumbling fool\nJust take it all with my love, take it all with my love\nI will change if I must\nSlow it down and bring it home, I will adjust\nOh if only, if only you knew\nEverything I do, is for you\nBut go on and take it, take it all with you\nDon\'t look back at this crumbling fool\nJust take it, take it all with you\nDon\'t look back at this crumbling fool\nJust take it all, with my love\nTake it all with my love\nTake it all with my love', 228, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (30, 'I\'ll Be Waiting', 'Hold me closer one more time\nSay that you love me in your last goodbye\nPlease forgive me for my sins\nYes, I swam dirty waters\nBut you pushed me in\nI\'ve seen your face under every sky\nOver every border and on every line\nYou know my heart more than I do\nWe were the greatest, me and you\nBut we had time against us\nAnd miles between us\nThe heavens cried\nI know I left you speechless\nBut now the sky has cleared and it\'s blue\nAnd I see my future in you\nI\'ll be waiting for you when you\'re ready to love me again\nI\'ll put my hands up\nI\'ll do everything different\nI\'ll be better to you\nI\'ll be waiting for you when you\'re ready to love me again\nI\'ll put my hands up\nI\'ll be somebody different\nI\'ll be better to you\nLet me stay here for just one more night\nBuild your world around me\nAnd pull me to the light\nSo I can tell you that I was wrong\nI was a child then but now I\'m willing to learn\nBut we had time against us\nAnd miles between us\nThe heavens cried\nI know I left you speechless\nBut now the sky has cleared and it\'s blue\nAnd I see my future in you\nI\'ll be waiting for you when you\'re ready to love me again\nI\'ll put my hands up\nI\'ll do everything different\nI\'ll be better to you\nI\'ll be waiting for you when you\'re ready to love me again\nI\'ll put my hands up\nI\'ll be somebody different\nI\'ll be better to you\nTime against us (time against us)\nMiles between us (miles between us)\nHeavens cried\nI know I left you speechless\nTime against us (time against us)\nMiles between us (miles between us)\nHeavens cried\nI know I left you speechless\nI know I left you speechless\nI\'ll be waiting\nI\'ll be waiting for you when you\'re ready to love me again\nI\'ll put my hands up\nI\'ll do everything different\nI\'ll be better to you\nI\'ll be waiting for you when you\'re ready to love me again\nI\'ll put my hands up\nI\'ll be somebody different\nI\'ll be better to you', 241, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (31, 'One and Only', 'You\'ve been on my mind\nI grow fonder every day\nLose myself in time\nJust thinking of your face\nGod only knows why it\'s taken me\nSo long to let my doubts go\nYou\'re the only one that I want\nI don\'t know why I\'m scared\nI\'ve been here before\nEvery feeling, every word\nI\'ve imagined it all\nYou\'ll never know if you never try\nTo forget your past and simply be mine\nI dare you to let me be your, your one and only\nI promise I\'m worthy\nTo hold in your arms\nSo come on and give me the chance\nTo prove I am the one who can walk that mile\nUntil the end starts\nIf I\'ve been on your mind\nYou hang on every word I say\nLose yourself in time\nAt the mention of my name\nWill I ever know how it feels to hold you close\nAnd have you tell me\nWhichever road I choose, you\'ll go?\nI don\'t know why I\'m scared\n\'Cause I\'ve been here before\nEvery feeling, every word\nI\'ve imagined it all\nYou\'ll never know if you never try\nTo forget your past and simply be mine\nI dare you to let me be your, your one and only\nI promise I\'m worthy, mm\nTo hold in your arms\nSo come on and give me the chance\nTo prove I am the one who can walk that mile\nUntil the end starts\nI know it ain\'t easy giving up your heart\nI know it ain\'t easy giving up your heart\nNobody\'s perfect\n(I know it ain\'t easy giving up your heart)\nTrust me I\'ve learned it\nNobody\'s perfect\n(I know it ain\'t easy giving up your heart)\nTrust me I\'ve learned it\nNobody\'s perfect\n(I know it ain\'t easy giving up your heart)\nTrust me I\'ve learned it\nNobody\'s perfect\n(I know it ain\'t easy giving up your heart)\nTrust me I\'ve learned it\nSo I dare you to let me be your, your one and only\nI promise I\'m worthy\nTo hold in your arms\nSo come on and give me the chance\nTo prove that I am the, one who can walk that mile\nUntil the end starts\nCome on and give me the chance\nTo prove that I am the one who can, walk that mile\nUntil the end starts', 348, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (32, 'Someone like You', 'I heard that you\'re settled down\nThat you found a girl and you\'re married now\nI heard that your dreams came true\nGuess she gave you things, I didn\'t give to you\nOld friend, why are you so shy?\nAin\'t like you to hold back or hide from the light\nI hate to turn up out of the blue, uninvited\nBut I couldn\'t stay away, I couldn\'t fight it\nI had hoped you\'d see my face\nAnd that you\'d be reminded that for me, it isn\'t over\nNever mind, I\'ll find someone like you\nI wish nothing but the best for you, too\n\"Don\'t forget me, \" I beg\nI remember you said\n\"Sometimes it lasts in love, but sometimes it hurts instead\"\n\"Sometimes it lasts in love, but sometimes it hurts instead\"\nYou know how the time flies\nOnly yesterday was the time of our lives\nWe were born and raised in a summer haze\nBound by the surprise of our glory days\nI hate to turn up out of the blue, uninvited\nBut I couldn\'t stay away, I couldn\'t fight it\nI had hoped you\'d see my face\nAnd that you\'d be reminded that for me, it isn\'t over\nNever mind, I\'ll find someone like you\nI wish nothing but the best for you, too\n\"Don\'t forget me, \" I begged\nI remember you said\n\"Sometimes it lasts in love, but sometimes it hurts instead\"\nNothing compares, no worries or cares\nRegrets and mistakes, they\'re memories made\nWho would have known how bittersweet this would taste?\nNever mind, I\'ll find someone like you\nI wish nothing but the best for you\n\"Don\'t forget me, \" I beg\nI remember you said\n\"Sometimes it lasts in love, but sometimes it hurts instead\"\nNever mind, I\'ll find someone like you\nI wish nothing but the best for you, too\n\"Don\'t forget me, \" I begged\nI remember you said\n\"Sometimes it lasts in love, but sometimes it hurts instead\"\n\"Sometimes it lasts in love, but sometimes it hurts instead\"\n', 285, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (33, 'So What', NULL, 562, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (34, 'Freddie Freeloader', 'The machines on\nHere we go\nCOC 62-2-90, no title, take one\nHey Wyn, that\'s a Cannonball\nYou play again, and then we\'ll come in and end it\nIt\'s too fast\nMiles, where are you gonna work now?\nRight here\nOk, \'cause if you move back we don\'t get you\nYou, you were right when you played before\nWhen I play though, I\'m gonna raise my horn a little bit\nOkay, okay\nCan I move this down a little bit?\nIt\'s against policy to move a microphone, hahaha (so stay main)\nHere we go, ready?\nNumber two\nWait one minute\nWait one minute\nAlright?\nYeah\nLet\'s hear a little bit of it', 574, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (35, 'Blue in Green', NULL, 327, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (36, 'All Blues', 'The sea, the sky, the you and I\nThe sea, the sky, for you and I\nI\'ll know we\'re all blues\nAll Shades, all hues, all blues\nSome blues are sad\nBut some are glad,\nDark-sad or bright-gald\nThey\'re all blues\nAll shades, all hues, all blues\nThe color of colors\nThe blues are more than a color\nThey\'re a moan of pain\nA Taste of strife\nAnd a sad refrain\nA game which lif is pplayin\'\nBlues can be the livin\' dues\nWe\'re all a-payin\'\nYeah, Oh Lord\nIn a rainbow\nA summer day that\'s fair\nA parayer is prayed\nA lament that\'s made\nBREAK\nSome shade of blues is there;\nBlue heaven\'s hue,\nThey\'re all blues!\nINSTRUMENTAL\nTalkin\' \'bout the sea and the sky\nAndi\'m talkin\' \'bout you and I\nThe sea, the sky\nFor you and I\nAnd I know we\'re all blues\nAll shades,\nAll hues,\nAll blues\nSea, sky, you and I\nSee the sky, you and I\nAll Blues\nAll shades, all hues, all blues', 693, 2, '2021-12-14', NULL);
INSERT INTO `song` (`id`, `name`, `lyrics`, `duration_seconds`, `user_id`, `create_date`, `update_time`) VALUES (37, 'Flamenco Sketches', NULL, 566, 2, '2021-12-14', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_album`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `favorite_album` (`user_id`, `album_id`) VALUES (1, 1);
INSERT INTO `favorite_album` (`user_id`, `album_id`) VALUES (2, 2);
INSERT INTO `favorite_album` (`user_id`, `album_id`) VALUES (2, 3);
INSERT INTO `favorite_album` (`user_id`, `album_id`) VALUES (2, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_song`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `favorite_song` (`user_id`, `song_id`) VALUES (1, 2);
INSERT INTO `favorite_song` (`user_id`, `song_id`) VALUES (2, 3);
INSERT INTO `favorite_song` (`user_id`, `song_id`) VALUES (2, 11);
INSERT INTO `favorite_song` (`user_id`, `song_id`) VALUES (2, 32);
INSERT INTO `favorite_song` (`user_id`, `song_id`) VALUES (1, 37);

COMMIT;


-- -----------------------------------------------------
-- Data for table `genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `genre` (`id`, `name`) VALUES (1, 'country');
INSERT INTO `genre` (`id`, `name`) VALUES (2, 'indie rock');
INSERT INTO `genre` (`id`, `name`) VALUES (3, 'Electronic dance music (EDM)');
INSERT INTO `genre` (`id`, `name`) VALUES (4, 'Hip-hop');
INSERT INTO `genre` (`id`, `name`) VALUES (5, 'Jazz');
INSERT INTO `genre` (`id`, `name`) VALUES (6, 'K-pop');
INSERT INTO `genre` (`id`, `name`) VALUES (7, 'Metal');
INSERT INTO `genre` (`id`, `name`) VALUES (8, 'Oldies');
INSERT INTO `genre` (`id`, `name`) VALUES (9, 'Pop');
INSERT INTO `genre` (`id`, `name`) VALUES (10, 'Rap');
INSERT INTO `genre` (`id`, `name`) VALUES (11, 'Rhythm & blues (R&B)');
INSERT INTO `genre` (`id`, `name`) VALUES (12, 'Rock');
INSERT INTO `genre` (`id`, `name`) VALUES (13, 'Post rock');

COMMIT;


-- -----------------------------------------------------
-- Data for table `album_genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `album_genre` (`album_id`, `genre_id`) VALUES (1, 1);
INSERT INTO `album_genre` (`album_id`, `genre_id`) VALUES (2, 2);
INSERT INTO `album_genre` (`album_id`, `genre_id`) VALUES (3, 9);
INSERT INTO `album_genre` (`album_id`, `genre_id`) VALUES (4, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `song_artist`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 1);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 2);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 3);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 4);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 5);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 6);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 7);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 8);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 9);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 10);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (1, 11);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 12);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 13);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 14);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 15);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 16);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 17);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 18);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 19);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 20);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 21);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (2, 22);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 23);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 24);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 25);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 26);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 27);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 28);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 29);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 30);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 31);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (3, 32);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (4, 33);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (4, 34);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (4, 35);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (4, 36);
INSERT INTO `song_artist` (`artist_id`, `song_id`) VALUES (4, 37);

COMMIT;


-- -----------------------------------------------------
-- Data for table `album_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `album_rating` (`id`, `rating`, `album_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (1, 3, 1, 1, 'nice album', '2018-01-02', NULL);
INSERT INTO `album_rating` (`id`, `rating`, `album_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (2, 4, 2, 2, 'what an awesome album', '2021-12-14', NULL);
INSERT INTO `album_rating` (`id`, `rating`, `album_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (3, 4, 3, 2, 'really a good one', '2021-12-14', NULL);
INSERT INTO `album_rating` (`id`, `rating`, `album_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (4, 5, 4, 2, 'the greatest and most atmospheric one', '2021-12-14', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `song_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `song_rating` (`id`, `rating`, `song_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (1, 3, 2, 1, 'nice one', '2018-02-03', NULL);
INSERT INTO `song_rating` (`id`, `rating`, `song_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (2, 4, 5, 2, 'it is amazing', '2021-12-14', NULL);
INSERT INTO `song_rating` (`id`, `rating`, `song_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (3, 2, 1, 2, 'its ok', '2019-11-14', NULL);
INSERT INTO `song_rating` (`id`, `rating`, `song_id`, `user_id`, `description`, `rating_date`, `update_time`) VALUES (4, 5, 32, 2, 'i love it so much', '2021-12-14', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `album_song`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 1);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 2);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 3);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 4);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 5);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 6);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 7);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 8);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 9);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 10);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (1, 11);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 12);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 13);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 14);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 15);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 16);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 17);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 18);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 19);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 20);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 21);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (2, 22);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 23);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 24);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 25);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 26);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 27);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 28);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 29);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 30);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 31);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (3, 32);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (4, 33);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (4, 34);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (4, 35);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (4, 36);
INSERT INTO `album_song` (`album_id`, `song_id`) VALUES (4, 37);

COMMIT;


-- -----------------------------------------------------
-- Data for table `album_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `album_comment` (`id`, `user_id`, `album_id`, `comment`, `comment_date`, `in_reply_to`, `update_time`) VALUES (1, 1, 1, 'I love it', '2018-01-01', NULL, NULL);
INSERT INTO `album_comment` (`id`, `user_id`, `album_id`, `comment`, `comment_date`, `in_reply_to`, `update_time`) VALUES (2, 2, 2, 'enjoy a lot, i will recommend to my friends', '2021-12-14', NULL, NULL);
INSERT INTO `album_comment` (`id`, `user_id`, `album_id`, `comment`, `comment_date`, `in_reply_to`, `update_time`) VALUES (3, 2, 3, 'what a good album', '2021-12-14', NULL, NULL);
INSERT INTO `album_comment` (`id`, `user_id`, `album_id`, `comment`, `comment_date`, `in_reply_to`, `update_time`) VALUES (4, 2, 4, 'the most popular jazz in the world', '2021-12-14', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_friend`
-- -----------------------------------------------------
START TRANSACTION;
USE `audiofiledb`;
INSERT INTO `user_friend` (`user_id`, `friend_id`) VALUES (1, 1);
INSERT INTO `user_friend` (`user_id`, `friend_id`) VALUES (2, 2);

COMMIT;

