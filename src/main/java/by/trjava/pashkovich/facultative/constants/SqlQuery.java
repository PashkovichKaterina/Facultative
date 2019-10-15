package by.trjava.pashkovich.facultative.constants;

public class SqlQuery {
    //ApplyDAO
    public static final String INSERT_APPLY = "insert into requests values (?, ?, ?)";
    public static final String UPDATE_APPLY_STATUS = "update requests set status_id = ? where user_id = ? and course_id = ?";
    public static final String DELETE_APPLY = "delete from requests where user_id = ? and course_id = ?";
    public static final String IS_CONTAINS_APPLY = "select * from requests where user_id = ? and course_id = ?";
    public static final String GET_APPLY_BY_STUDENT = "select courses.course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher, number_of_classes, description, requests.status_id, availability from requests inner join courses on requests.course_id = courses.course_id inner join categories on categories.category_id = courses.category_id inner join user_details on courses.teacher_id = user_details.user_id where requests.user_id = ?";

    //ArchiveDAO
    public static final String INSERT_ARCHIVE = "insert into archives(student_id, course_id, average_mark, start_date, end_date, review) values (?, ?, ?, ?, ?, ?)";
    public static final String IS_CONTAINS_ARCHIVE = "select * from archives where student_id = ? and course_id = ?";
    public static final String GET_ARCHIVE_COURSE_BY_STUDENT = "select start_date, end_date, average_mark, courses.course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher, number_of_classes, description, availability from courses inner join archives on courses.course_id = archives.course_id inner join categories on categories.category_id = courses.category_id inner join user_details on user_details.user_id = courses.teacher_id where student_id = ?";

    //ClassDAO
    public static final String INSERT_CLASS = "insert into classes(course_id, date, time) values(?, ?, ?)";
    public static final String IS_CONTAINS_CLASS = "select * from classes where course_id = ? and date = ?";
    public static final String DELETE_CLASS_BY_COURSE = "delete from classes where course_id = ?";
    public static final String GET_CLASSES_COUNT_BY_COURSE = "select count(*) as count from classes where course_id = ?";
    public static final String GET_CLASS_BY_COURSE = "select date, time from classes where course_id = ?";

    //CourseDAO
    public static final String GET_ALL_COURSE = "select course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher, number_of_classes, description, availability from courses inner join users on users.user_id = courses.teacher_id inner join user_details on users.user_id = user_details.user_id inner join categories on courses.category_id = categories.category_id";
    public static final String GET_COURSE_BY_CATEGORY = "select course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher, number_of_classes, description, availability from courses inner join users on users.user_id = courses.teacher_id inner join user_details on users.user_id = user_details.user_id inner join categories on courses.category_id = categories.category_id where categories.title = ?";
    public static final String GET_COURSE_BY_ID = "select course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher, number_of_classes, description, availability from courses inner join users on users.user_id = courses.teacher_id inner join user_details on users.user_id = user_details.user_id inner join categories on courses.category_id = categories.category_id where courses.course_id = ?";
    public static final String GET_COURSE_BY_PARTIAL_MATCH_TITLE = "select course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher, number_of_classes, description, availability from courses inner join users on users.user_id = courses.teacher_id inner join user_details on users.user_id = user_details.user_id inner join categories on courses.category_id = categories.category_id where courses.title like ?";
    public static final String GET_COURSE_BY_TEACHER = "select course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher, number_of_classes, description, availability from courses inner join users on users.user_id = courses.teacher_id inner join user_details on users.user_id = user_details.user_id inner join categories on courses.category_id = categories.category_id where courses.teacher_id = ?";
    public static final String GET_ALL_CATEGORY = "select * from categories";
    public static final String GET_CATEGORY_BY_ID = "select title as category_title from categories where category_id = ?";
    public static final String GET_CATEGORY_BY_TITLE = "select category_id from categories where title = ?";
    public static final String GET_COURSE_REQUIREMENT = "select skills.title as skill, levels.title as level from requirements inner join levels on levels.level_id = requirements.level_id inner join skills on skills.skill_id = requirements.skill_id where course_id = ?";
    public static final String GET_COURSE_TIMETABLE = "select title as day, time from timetables inner join days on days.day_id = timetables.day_of_week where course_id = ?";
    public static final String GET_STUDENT_CURRENT_COURSE = "select (select date from classes where course_id = works.course_id order by date desc limit 1) as start_date, courses.course_id, courses.title, categories.title as category_title, concat(surname, ' ', name, ' ', patronymic) as teacher,number_of_classes, description, availability, AVG(mark) as mark from marks inner join works on works.work_id = marks.work_id inner join courses on courses.course_id = works.course_id inner join categories on categories.category_id = courses.category_id inner join user_details on user_details.user_id = courses.teacher_id where student_id = ? group by courses.title";
    public static final String GET_ALL_STUDENT_BY_COURSE = "select users.user_id, login, email, password, surname, name, patronymic, date_of_birth, address, phone from requests inner join statuses on requests.status_id = statuses.status_id inner join users on users.user_id = requests.user_id inner join user_details on user_details.user_id = requests.user_id where requests.status_id = 3 and course_id = ?";

    //MarkDAO
    public static final String INSERT_MARK = "insert into marks(student_id, work_id, mark) values (?, ?, ?)";
    public static final String UPDATE_MARK = "update marks set mark = ? where student_id = ? and work_id = ?";
    public static final String DELETE_MARK_BY_COURSE = "delete from marks where course_id = ?";
    public static final String GET_STUDENT_AVERAGE_MARK_BY_COURSE = "select avg(mark) as mark from marks inner join works on works.work_id = marks.work_id where student_id = ? and course_id = ?";
    public static final String GET_STUDENT_ALL_MARK_BY_COURSE = "select title, mark from marks inner join works on works.work_id = marks.work_id where student_id = ? and course_id = ?";

    //UserDAO
    public static final String INSERT_USER = "insert into users(login, email, password, role_id) values (?, ?, ?, ?)";
    public static final String INSERT_USER_DETAILS = "insert into user_details(user_id, surname, name, patronymic, phone, address, date_of_birth, position) values(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER_DETAILS = "update user_details set surname=?, name=?, patronymic=?, phone=?, date_of_birth=?, address=?, position=? where user_id = ?";
    public static final String GET_USER_BY_LOGIN = "select users.user_id, login, email, password, surname, name, patronymic, date_of_birth, address, phone, positions.title as position, role_id from users left join user_details on user_details.user_id = users.user_id left join positions on user_details.position = positions.position_id where login = ?";
    public static final String GET_USER_BY_EMAIL = "select users.user_id, login, email, password, surname, name, patronymic, date_of_birth, address, phone, positions.title as position, role_id from users left join user_details on user_details.user_id = users.user_id left join positions on user_details.position = positions.position_id where email = ?";
    public static final String GET_USER_BY_ID = "select users.user_id, login, email, password, surname, name, patronymic, date_of_birth, address, phone, positions.title as position, role_id from users left join user_details on user_details.user_id = users.user_id left join positions on user_details.position = positions.position_id where users.user_id = ?";
    public static final String GET_PASSWORD_BY_LOGIN = "select password from users where login = ?";
    public static final String IS_CONTAINS_USER_DETAILS_BY_LOGIN = "select login from users inner join user_details on users.user_id = user_details.user_id where login = ?";

    //WorkDAO
    public static final String INSERT_WORK = "insert into works(course_id, title) values (?, ?)";
    public static final String DELETE_WORK_BY_COURSE = "delete from works where course_id = ?";
    public static final String GET_WORK_TITLE_BY_COURSE = "select title from works where course_id = ?";
    public static final String GET_WORK_ID = "select work_id from works where course_id = ? and title = ?";
}
