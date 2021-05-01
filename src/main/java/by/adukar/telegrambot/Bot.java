package by.adukar.telegrambot;

import by.adukar.telegrambot.buttons.inline.InlineButtons;
import by.adukar.telegrambot.buttons.reply.ReplyButtons;
import by.adukar.telegrambot.consts.Commands;
import by.adukar.telegrambot.consts.Paths;
import by.adukar.telegrambot.consts.Photos;
import by.adukar.telegrambot.consts.Text;
import by.adukar.telegrambot.enums.Color;
import by.adukar.telegrambot.service.TextService;
import by.adukar.telegrambot.service.UserService;
import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    ReplyButtons replyButtons = new ReplyButtons();
    InlineButtons inlineButtons = new InlineButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update){
        if(update.hasCallbackQuery()) {
            switch (update.getCallbackQuery().getData()) {
                case "Apple": {
                    sendMsgWithPhoto("Яблоко, 1.50 руб","https://www.google.com/url?sa=i&url=https%3A%2F%2Fprostye-retsepty.com%2Fzelenyie-yabloki-polza-ili-vred%2F&psig=AOvVaw33X6tqUAkwR-a87VPvNjZX&ust=1619945639429000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCMiNn8GXqPACFQAAAAAdAAAAABAD",  update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Xypma":
                {
                    sendMsgWithPhoto("Хурма, 2 руб", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.marieclaire.ru%2Ffood%2Ffoto-gid-po-hurme-kakaya-vyajet-kakaya-net-polza-i-vred-i-daje-3-retsepta-%2F&psig=AOvVaw3bayMdALkHdGSC27APUqau&ust=1619949793978000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPD4ub6dqPACFQAAAAAdAAAAABAE", update.getCallbackQuery().getFrom().getId().longValue());
                    break;

                }
                case "Strawberry": {
                    sendMsgWithPhoto("Клубника, 2руб", "", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Cherry": {
                    sendMsg("Вишня, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Smorodina": {
                    sendMsg("Смородина, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Malina": {
                    sendMsg("Малина, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Grysha": {
                    sendMsg("Груша, 1.70 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Vinograd": {
                    sendMsg("Виноград, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Limon": {
                    sendMsg("Лимон, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Orange": {
                    sendMsg("Апельсин, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Kokoc": {
                    sendMsg("Кокос, 1.60 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Mandarin": {
                    sendMsg(" Мандарин, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Red smorodina": {
                    sendMsg("Красная смородина, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Banana": {
                    sendMsg("Банан, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                case "Ananas": {
                    sendMsg("Ананас, 2 руб", update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                }
                default:
                    sendContact( update.getCallbackQuery().getFrom().getId().longValue());
                    break;
            }
        }
        Long chatId = update.getMessage().getChatId();
        switch (update.getMessage().getText()){
            case Commands.START:{
                userService.addUserToList(userService.createUserFromUpdate(update));
                sendMsg(textService.getPropValues(Paths.HELLO_STRING_PATH, Text.SAY_HELLO_PROPERTY),chatId);
                sendPhoto(textService.getPropValues(Paths.PHOTOS_URLS_PATH, Photos.HELLO_PHOTO_PATH), chatId);
                break;
            }
            case Commands.COLORS: {
                for (int i = 1; i <= 15; i++) {
                    sendMsgWithButtons( inlineButtons.keyboardMarkupForSelectStudentOrTeacher(textService.getPropValues(Paths.PHOTOS_URLS_PATH, "text.fruit." + i))," товар",chatId);
                }
                break;
            }
            case Commands.BUTTONS:{
                sendMsgWithButtons("Сделайте выбор:", replyButtons.keyboardMarkupForSelectStudentOrTeacher(),chatId);
                break;
            }
            case Commands.USERS:{
                sendMsg(userService.getAllUser(), chatId);
                break;
            }
            case Commands.LOCATION:{
                sendLocation(chatId);
                break;
            }
            default:{
                sendMsg("Write admin to get help @yqpuss", chatId);
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
            System.out.println( "Exception: " + e.toString());
        }
    }

    public synchronized void sendMsg(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

    public synchronized void sendContact(Long chatId) {
        SendContact sendContact = new SendContact();
        sendContact.setPhoneNumber("+375447357152");
        sendContact.setFirstName("Anton");
        sendContact.setLastName("Kupreichik");
        sendContact.setChatId(chatId);
        try {
            execute(sendContact);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }

  /*  public synchronized void sendDocument(Long chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument("http://www.africau.edu/images/default/sample.pdf");
        sendDocument.setCaption("Текст к документу");
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
        }
    }*/

    public synchronized void sendLocation(Long chatId){
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(Float.valueOf("-33.830693"));
        sendLocation.setLongitude(Float.valueOf("151.219"));

        try {
            execute(sendLocation);
        } catch (TelegramApiException e) {
            System.out.println( "Exception: " + e.toString());
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
            System.out.println( "Exception: " + e.toString());
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
            System.out.println( "Exception: " + e.toString());
        }
    }


    @Override
    public String getBotUsername() {
        return "Zorders_bot";
    }

    @Override
    public String getBotToken() {
        return "1754781115:AAHF3WLgmlvP38dLV09X0KA6honHtX9_O9o";
    }

    }


