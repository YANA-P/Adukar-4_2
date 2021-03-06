package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.inline.InlineButtons;
import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.Commands;
import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.consts.Photos;
import by.adukar.telegrambot.consts.Text;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import by.adukar.telegrambot.sql.Database;
import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;


public class Bot {

    ReplyButtons replyButtons = new ReplyButtons();
    InlineButtons inlineButtons = new InlineButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();

    Database database = new Database();
    List<Long> admins = List.of(9936207642L);

    static int startCount = 0;

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update) {
        if (update.hasCallbackQuery()) {
            Long chatIdFromCallBack = Long.valueOf(update.getCallbackQuery().getFrom().getId());
            List<String> goodsList = database.getListOfGoodsName();
            String data = update.getCallbackQuery().getData();
            if (goodsList.contains(data)) {
                int indexOfGoods = 0;
                indexOfGoods = goodsList.indexOf(data);
                sendMsgWithPhoto(database.getCurrentValue("name", indexOfGoods), database.getCurrentValue("url", indexOfGoods), chatIdFromCallBack);
                sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "???????????????? ?? ???????????????", chatIdFromCallBack);
            }
        }
        else {
            if (admins.contains(update.getMessage().getFrom().getId().longValue())) {
                Long chatIdAdmin = update.getMessage().getChatId();
                if(startCount == 0) {
                    sendMsg("you admin", update.getMessage().getFrom().getId().longValue());
                    sendMsgWithButtons("??????????????/??????????????????????????????????????", replyButtons.keyboardMarkupFor2( "Delete", "DeleteUser"), update.getMessage().getFrom().getId().longValue());
                    startCount++;
                }
                if (update.getMessage().getText().equals("Delete"))

                {
                    sendMsg("?????????? ?????????? ???????????? ???????????????\n ???????????? ??????????: delete_???????????????????????????? ", chatIdAdmin );
                }
                if (update.getMessage().getText().startsWith("delete_")){
                    String name = update.getMessage().getText().substring(7);
                    database.deleteGoods(name);
                    sendMsg("?????????????? ??????????????", chatIdAdmin);

                }
                if (update.getMessage().getText().equals("DeleteUser"))
                {
                    sendMsg("???????????? ???????????????????????? ???????????? ???????????????\n ???????????? ??????????: deleteuser_?????????????????????????????? ", chatIdAdmin );
                }
                if (update.getMessage().getText().startsWith("deleteuser_")){
                    String name = update.getMessage().getText().substring(7);
                    database.deleteGoods(name);
                    sendMsg("?????????????? ????????????", chatIdAdmin);
                }

                }
            }


            Long chatId = update.getMessage().getChatId();
            switch (update.getMessage().getText()) {
                case Commands.START: {
                    //database.insertUser(chatId, update.getMessage().getFrom().getFirstName(), update.getMessage().getFrom().getLastName(), "User");
                    sendMsgWithButtons("?????????? ???????????????????? ?? ?????? ??????????????!", replyButtons.keyboardMarkup("????????"), update.getMessage().getFrom().getId().longValue());
                    break;
                }
                case Commands.MENU: {
                    sendMsgWithButtons(inlineButtons.keyboardMarkupForGoods(database.getListOfGoodsName()), "????????????", chatId);
                    break;
                }
                case Commands.BUTTONS: {
                    sendMsgWithButtons("???????????????? ??????????:", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), chatId);
                    break;
                }
                case Commands.USERS: {
                    sendMsg(userService.getAllUser(), chatId);
                    break;
                }
                case Commands.LOCATION: {
                    sendLocation(chatId);
                    break;
                }
                default: {
                    sendMsg("???????????????????????? +375(29)423-98-44", chatId);
                    sendContact(chatId);
                    break;
                }
            }
        }


    private void sendMsgWithPhoto(String message, String url, long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
            sendPhoto(url, chatId);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    public synchronized void sendMsg(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    public synchronized void sendContact(Long chatId) {
        SendContact sendContact = new SendContact();
        sendContact.setPhoneNumber("+375296985989");
        sendContact.setFirstName("Yana");
        sendContact.setLastName("Pisarenko");
        sendContact.setChatId(chatId);
        try {
            execute(sendContact);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

  /*  public synchronized void sendDocument(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument("http://www.africau.edu/images/default/sample.pdf");
        sendDocument.setCaption("?????????? ?? ??????????????????");
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }*/

    public synchronized void sendLocation(Long chatId) {
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(Float.valueOf("-33.830693"));
        sendLocation.setLongitude(Float.valueOf("151.219"));

        try {
            execute(sendLocation);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    public synchronized void sendPhoto(String pathToPhoto, Long chatId) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setPhoto(pathToPhoto);
        try {
            sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public synchronized void sendMsgWithButtons(InlineKeyboardMarkup inlineKeyboardMarkup, String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    public synchronized void sendMsgWithButtons(String message, ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    public synchronized void sendButtons(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return "Zorders_bot";
    }

    @Override
    public String getBotToken() {
        return "1754781115:AAEvl_YdgXaG36Ep0tqVEqurGks_1VvM_Do";
    }

}


