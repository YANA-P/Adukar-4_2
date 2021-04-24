package by.adukar.telegrambot.service;

import by.adukar.telegrambot.model.User;
import org.telegram.telegrambots.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static List<User> userList = new ArrayList<>();

    public void addUserToList(User user) {
        userList.add(user);
        System.out.println("Пользователь успешно добавлен");
    }

    public User createUserFromUpdate(Update update) {
        return User.builder()
                .chatId(update.getMessage().getChatId())
                .firstName(update.getMessage().getFrom().getFirstName())
                .lastName(update.getMessage().getFrom().getLastName())
                .userName(update.getMessage().getFrom().getUserName())
                .isAdmin(false)
                .isStudent(true)
                .isTeacher(false)
                .isBlocked(false)
                .build();
    }

    public String getAllUser() {
        return userList.toString();
    }
}
