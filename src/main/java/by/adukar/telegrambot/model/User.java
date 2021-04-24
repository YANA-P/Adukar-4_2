package by.adukar.telegrambot.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private boolean isBlocked;
    private boolean isAdmin;
    private boolean isTeacher;
    private boolean isStudent;
}
