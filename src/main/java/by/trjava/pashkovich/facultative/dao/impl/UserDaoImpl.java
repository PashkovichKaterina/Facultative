package by.trjava.pashkovich.facultative.dao.impl;

import by.trjava.pashkovich.facultative.constants.SqlQuery;
import by.trjava.pashkovich.facultative.constants.Variable;
import by.trjava.pashkovich.facultative.dao.UserDAO;
import by.trjava.pashkovich.facultative.dao.exception.DAOException;
import by.trjava.pashkovich.facultative.dao.pool.impl.BaseConnectionPool;
import by.trjava.pashkovich.facultative.entity.User;
import by.trjava.pashkovich.facultative.entity.characteristics.UserRole;
import by.trjava.pashkovich.facultative.entity.exception.InstallerException;
import by.trjava.pashkovich.facultative.entity.factory.UserFactory;
import by.trjava.pashkovich.facultative.entity.installation.UserInstaller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDAO {

    @Override
    public void insertUser(String login, String email, String password, UserRole role) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER)) {
            statement.setString(1, login);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setInt(4, role.getRoleId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void insertUserDetails(int userId, String surname, String name, String patronymic, String phone,
                                  String date, String address, String position) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER_DETAILS)) {
            statement.setInt(1, userId);
            statement.setString(2, surname);
            statement.setString(3, name);
            statement.setString(4, patronymic);
            statement.setString(5, phone);
            statement.setString(6, address);
            statement.setString(7, date);
            statement.setString(8, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void updateUserDetails(int userId, String surname, String name, String patronymic, String phone,
                                  String date, String address, String position) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_DETAILS);
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setString(3, patronymic);
            statement.setString(4, phone);
            statement.setString(5, date);
            statement.setString(6, address);
            statement.setObject(7, position);
            statement.setInt(8, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_LOGIN, login);
    }

    public User getUserByEmail(String email) throws DAOException {
        return getUserByStringParameter(SqlQuery.GET_USER_BY_EMAIL, email);
    }

    private User getUserByStringParameter(String query, String parameter) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, parameter);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserRole role = UserRole.getRole(resultSet.getInt(Variable.ROLE_ID));
                    user = UserFactory.createUser(role);
                    UserInstaller.install(user, resultSet);
                }
            }
        } catch (SQLException | InstallerException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return user;
    }

    @Override
    public User getUserById(int id) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserRole role = UserRole.getRole(resultSet.getInt(Variable.ROLE_ID));
                    user = UserFactory.createUser(role);
                    UserInstaller.install(user, resultSet);
                }
            }
        } catch (SQLException | InstallerException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return user;
    }

    @Override
    public String getPasswordByLogin(String login) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        String password = null;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.GET_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    password = resultSet.getString(Variable.PASSWORD);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return password;
    }


    @Override
    public boolean isContainsUserDetailsByLogin(String login) throws DAOException {
        Connection connection = BaseConnectionPool.getInstance().getConnection();
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(SqlQuery.IS_CONTAINS_USER_DETAILS_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        BaseConnectionPool.getInstance().releaseConnection(connection);
        return result;
    }
}
