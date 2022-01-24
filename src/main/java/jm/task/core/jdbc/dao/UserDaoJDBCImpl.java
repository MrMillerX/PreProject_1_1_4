package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement state = Util.getNewConnection().createStatement()) {
            state.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT(19) NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR(45) NOT NULL," +
                    "age TINYINT(3) NOT NULL," +
                    "PRIMARY KEY (id));");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement state = Util.getNewConnection().createStatement()) {
            state.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement state = Util.getNewConnection()
                .prepareStatement("INSERT INTO users(name, lastname, age) VALUES(?, ?, ?)")) {
            state.setString(1, name);
            state.setString(2, lastName);
            state.setByte(3, age);
            state.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement state = Util.getNewConnection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            state.setLong(1, id);
            state.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement state = Util.getNewConnection().createStatement()) {
            List<User> userList = new ArrayList<>();
            ResultSet resultUsers = state.executeQuery("SELECT * FROM users");
            while (resultUsers.next()) {
                User bufUser = new User();
                bufUser.setId(resultUsers.getLong("id"));
                bufUser.setName(resultUsers.getString("name"));
                bufUser.setLastName(resultUsers.getString("lastname"));
                bufUser.setAge(resultUsers.getByte("age"));
                userList.add(bufUser);
            }
            return userList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement state = Util.getNewConnection().createStatement()) {
            state.executeUpdate("DELETE FROM users");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
