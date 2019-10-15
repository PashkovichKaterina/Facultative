package by.trjava.pashkovich.facultative.dao;

import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristics.UserRole;


public interface UserDAO {

    void insertUser(String login, String email, String password, UserRole role) throws DAOException;


    void insertUserDetails(int userId, String surname, String name, String patronymic, String phone,
                           String date, String address, String position) throws DAOException;

    void updateUserDetails(int userId, String surname, String name, String patronymic, String phone,
                           String date, String address, String position) throws DAOException;

    User getUserById(int id) throws DAOException;

    User getUserByLogin(String login) throws DAOException;

    User getUserByEmail(String email) throws DAOException;

    String getPasswordByLogin(String login) throws DAOException;

    boolean isContainsUserDetailsByLogin(String login) throws DAOException;
}
