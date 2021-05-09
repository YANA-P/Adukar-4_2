package by.adukar.telegrambot.buttons.inline;

import lombok.SneakyThrows;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineButtons {

    @SneakyThrows
    public InlineKeyboardMarkup keyboardMarkupForSelectStudentOrTeacher(String text) {
        List<List<InlineKeyboardButton>> listKeyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttonsList = new ArrayList<>();
        InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
        keyboardButton.setCallbackData(text);
        keyboardButton.setText(text);
        buttonsList.add(keyboardButton);
        listKeyboard.add(buttonsList);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listKeyboard);
        return inlineKeyboardMarkup;
    }


    @SneakyThrows
    public InlineKeyboardMarkup keyboardMarkupForGoods(List<String>  stringList) {
        List<List<InlineKeyboardButton>> listKeyboard = new ArrayList<>();


        for (int i = 0; i < stringList.size(); i++) {
            List<InlineKeyboardButton> buttonsList = new ArrayList<>();
            InlineKeyboardButton keyboardButton = new InlineKeyboardButton();

            keyboardButton.setCallbackData(stringList.get(i));
            keyboardButton.setText(stringList.get(i));

            buttonsList.add(keyboardButton);
            listKeyboard.add(buttonsList);
        }


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listKeyboard);
        return inlineKeyboardMarkup;
    }
}
