package com.ofd.ofd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ofd.ofd.exception.AlreadyExistsUserException;
import com.ofd.ofd.exception.NoSuchUserException;
import com.ofd.ofd.exception.SmthGoneWrongException;
import com.ofd.ofd.model.NewUser;
import com.ofd.ofd.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DAOImpl implements DAO {
    private final String URL = "jdbc:h2:mem:testdb";
    private final String USER = "sa";
    private final String PASSWORD = "";
    private Connection connection;

    @Autowired
    public DAOImpl() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            this.connection.setAutoCommit(false);
        } catch (SQLException ex) {
            if (this.connection != null) {
                this.connection.close();
            }
            throw new SmthGoneWrongException(ex.getMessage());
        }
    }

    @Override
    public boolean checkExistingUser(NewUser newUser) {
        try {
            try {
                PreparedStatement statement = connection
                        .prepareStatement("SELECT login, password, balance FROM USER WHERE login=?");
                statement.setString(1, newUser.getLogin());
                ResultSet result = statement.executeQuery();
                connection.commit();
                if (!result.next()) {
                    result.close();
                    return false;
                }
            } catch (SQLException ex) {
                connection.rollback();
                throw new SmthGoneWrongException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new SmthGoneWrongException(ex.getMessage());
        }
        return true;
    }

    @Override
    public User addNewUser(NewUser newUser) throws AlreadyExistsUserException {
        User user;
        try {
            try (PreparedStatement statement = connection
                .prepareStatement("insert into USER(login, password) values(?, ?)")) {
                statement.setString(1, newUser.getLogin());
                statement.setString(2, newUser.getPassword());
                statement.executeUpdate();
                user = findUserByLogin(newUser.getLogin());
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SmthGoneWrongException(e.getMessage());
            }
        } catch(SQLException ex) {
            throw new SmthGoneWrongException(ex.getMessage());
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws NoSuchUserException {
        User user = null;
        try {
            try (PreparedStatement statement = connection
                    .prepareStatement("SELECT id, login, password, balance FROM USER WHERE login=?")) {
                statement.setString(1, login);
                ResultSet result = statement.executeQuery();
            
                while (result.next()) {
                    String password = result.getString("password");
                    String balance = result.getString("balance");
                    String id = result.getString("id");
                    user = new User(id, login, password, Integer.parseInt(balance));
                }
                connection.commit();
                result.close();
            } catch (SQLException ex) {
                connection.rollback();
                throw new SmthGoneWrongException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new SmthGoneWrongException(ex.getMessage());
        }
        if (user == null) {
            throw new NoSuchUserException();
        }
        return user;
    }

    @Override
    public User findUserById(Integer id) throws NoSuchUserException {
        User user = null;
        try {
            try (PreparedStatement statement = connection
                    .prepareStatement("SELECT id, login, password, balance FROM USER WHERE id=?")) {
                statement.setString(1, Integer.toString(id));
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    String login = result.getString("login");
                    String password = result.getString("password");
                    String userId = result.getString("id");
                    int balance = Integer.parseInt(result.getString("balance"));
                    user = new User(userId, login, password, balance);
                }
                connection.commit();
                result.close();
            } catch (SQLException ex) {
                connection.rollback();
                throw new SmthGoneWrongException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new SmthGoneWrongException(ex.getMessage());
        }
        if (user == null) {
            throw new NoSuchUserException();
        }
        return user;
    }
}
