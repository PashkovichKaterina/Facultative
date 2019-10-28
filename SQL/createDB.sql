
-- -----------------------------------------------------
-- Schema facultative
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `facultative` ;
CREATE SCHEMA IF NOT EXISTS `facultative` DEFAULT CHARACTER SET utf8 ;
USE `facultative` ;

-- -----------------------------------------------------
-- Table `facultative`.`roles`
-- The table containes the roles that are defined in the application.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`users`
-- The table contains basic user information.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `email` VARCHAR(254) NOT NULL,
  `password` VARCHAR(160) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_Roles_Users_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_Roles_Users`
    FOREIGN KEY (`role_id`)
    REFERENCES `facultative`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`categories`
-- The table containes all defined categories for courses.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `title_ru` VARCHAR(20) NOT NULL,
  `title_en` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`courses`
-- The table contains information about courses.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`courses` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `title_ru` VARCHAR(40) NOT NULL,
  `title_en` VARCHAR(40) NOT NULL,
  `teacher_id` INT NOT NULL,
  `number_of_classes` INT NOT NULL,
  `category_id` INT NOT NULL,
  `availability` BIT NOT NULL,
  `description_ru` VARCHAR(500) NOT NULL,
  `description_en` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `fk_Categories_Courses_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_Users_Courses_idx` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `fk_Categories_Courses`
    FOREIGN KEY (`category_id`)
    REFERENCES `facultative`.`categories` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Courses`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `facultative`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`statuses`
-- The table contains the statuses that student applications can accept.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`statuses` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `title_ru` VARCHAR(20) NOT NULL,
  `title_en` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`applications`
-- The table contains all students applications.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`applications` (
  `user_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  INDEX `fk_Users_Requsets_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Courses_Requests_idx` (`course_id` ASC) VISIBLE,
  INDEX `fk_Status_Requests_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_Users_Requsets`
    FOREIGN KEY (`user_id`)
    REFERENCES `facultative`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Courses_Requests`
    FOREIGN KEY (`course_id`)
    REFERENCES `facultative`.`courses` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Status_Requests`
    FOREIGN KEY (`status_id`)
    REFERENCES `facultative`.`statuses` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`classes`
-- The table contains all classes for each course.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`classes` (
  `class_id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  PRIMARY KEY (`class_id`),
  INDEX `fk_Courses_Classes_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_Courses_Classes`
    FOREIGN KEY (`course_id`)
    REFERENCES `facultative`.`courses` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`works`
-- The table contains all works for each course.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`works` (
  `work_id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`work_id`),
  INDEX `fk_Courses_Works_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_Courses_Works`
    FOREIGN KEY (`course_id`)
    REFERENCES `facultative`.`courses` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`marks`
-- The table contains all marks for each work.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`marks` (
  `student_id` INT NOT NULL,
  `work_id` INT NOT NULL,
  `mark` INT NOT NULL,
  INDEX `fk_Users_Marks_idx` (`student_id` ASC) VISIBLE,
  INDEX `fk_Works_Marks_idx` (`work_id` ASC) VISIBLE,
  CONSTRAINT `fk_Users_Marks`
    FOREIGN KEY (`student_id`)
    REFERENCES `facultative`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Works_Marks`
    FOREIGN KEY (`work_id`)
    REFERENCES `facultative`.`works` (`work_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`archives`
-- The table contains archive of student courses.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`archives` (
  `student_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `average_mark` INT NOT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `review` VARCHAR(100) NOT NULL,
  INDEX `fk_Users_ListenerArchive_idx` (`student_id` ASC) VISIBLE,
  INDEX `fk_Courses_ListenerArchive_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_Users_ListenerArchive`
    FOREIGN KEY (`student_id`)
    REFERENCES `facultative`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Courses_ListenerArchive`
    FOREIGN KEY (`course_id`)
    REFERENCES `facultative`.`courses` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`positions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`positions` (
  `position_id` INT NOT NULL AUTO_INCREMENT,
  `title_ru` VARCHAR(30) NOT NULL,
  `title_en` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`position_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`user_details`
-- The table contains personal user information.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`user_details` (
  `user_id` INT NOT NULL,
  `surname` VARCHAR(20) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `patronymic` VARCHAR(20) NOT NULL,
  `date_of_birth` DATE NULL,
  `address` VARCHAR(30) NULL,
  `phone` VARCHAR(20) NOT NULL,
  `position` INT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_Positions_UserDetails_idx` (`position` ASC) VISIBLE,
  CONSTRAINT `fk_Users_UserDetails`
    FOREIGN KEY (`user_id`)
    REFERENCES `facultative`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Positions_UserDetails`
    FOREIGN KEY (`position`)
    REFERENCES `facultative`.`positions` (`position_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`skills`
-- The table all defined skills for requirement.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`skills` (
  `skill_id` INT NOT NULL AUTO_INCREMENT,
  `title_ru` VARCHAR(20) NOT NULL,
  `title_en` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`skill_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`levels`
-- The table all defined skill levels.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`levels` (
  `level_id` INT NOT NULL AUTO_INCREMENT,
  `title_ru` VARCHAR(30) NOT NULL,
  `title_en` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`level_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`requirements`
-- The table containes all requirement for each course.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`requirements` (
  `course_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  `level_id` INT NOT NULL,
  INDEX `fk_Skills_Requirements_idx` (`skill_id` ASC) VISIBLE,
  INDEX `fk_Level_Requirements_idx` (`level_id` ASC) VISIBLE,
  INDEX `fk_Courses_Requirements_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_Skills_Requirements`
    FOREIGN KEY (`skill_id`)
    REFERENCES `facultative`.`skills` (`skill_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Level_Requirements`
    FOREIGN KEY (`level_id`)
    REFERENCES `facultative`.`levels` (`level_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Courses_Requirements`
    FOREIGN KEY (`course_id`)
    REFERENCES `facultative`.`courses` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`days`
-- The table containes all days on which classes can be held.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`days` (
  `day_id` INT NOT NULL AUTO_INCREMENT,
  `title_ru` VARCHAR(15) NOT NULL,
  `title_en` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`day_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facultative`.`timetables`
-- The table containes timetable for each course.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facultative`.`timetables` (
  `course_id` INT NOT NULL,
  `day_of_week` INT NOT NULL,
  `time` TIME NOT NULL,
  INDEX `fk_Courses_Timetable_idx` (`course_id` ASC) VISIBLE,
  INDEX `fk_Days_Timatable_idx` (`day_of_week` ASC) VISIBLE,
  CONSTRAINT `fk_Courses_Timetable`
    FOREIGN KEY (`course_id`)
    REFERENCES `facultative`.`courses` (`course_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Days_Timatable`
    FOREIGN KEY (`day_of_week`)
    REFERENCES `facultative`.`days` (`day_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;