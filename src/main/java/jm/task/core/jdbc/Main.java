package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        List<User> firstUsersList = new ArrayList<>();
        firstUsersList.add(new User("Ryan", "Gosling", (byte) 41));
        firstUsersList.add(new User("Adam", "Driver", (byte) 38));
        firstUsersList.add(new User("Cristin", "Milioti", (byte) 36));
        for (User u : firstUsersList) {
            userService.saveUser(u.getName(), u.getLastName(), u.getAge());
            System.out.printf("User с именем – %s %s добавлен в базу данных.\n", u.getName(), u.getLastName());
        }
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
