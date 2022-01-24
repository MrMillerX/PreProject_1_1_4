package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание таблицы User(ов)
        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        // Очистка таблицы User(ов)
        // Удаление таблицы
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ryan", "Gosling", (byte) 41);
        System.out.println("User с именем – Ryan Gosling добавлен в базу данных");
        userService.saveUser("Adam", "Driver", (byte) 38);
        System.out.println("User с именем – Adam Driver добавлен в базу данных");
        userService.saveUser("Cristin", "Milioti", (byte) 36);
        System.out.println("User с именем – Cristin Milioti добавлен в базу данных");
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
