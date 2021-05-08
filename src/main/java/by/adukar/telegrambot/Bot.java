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

import java.util.List;


public class Bot extends TelegramLongPollingBot {

    ReplyButtons replyButtons = new ReplyButtons();
    InlineButtons inlineButtons = new InlineButtons();

    UserService userService = new UserService();
    TextService textService = new TextService();
    List<Long> admins = List.of(993627642L);

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        sendAnswerFromBot(update);
    }

    @SneakyThrows
    public void sendAnswerFromBot(Update update){
      if(admins.contains(update.getMessage().getFrom().getId().longValue())){
          sendMsg("you admin",update.getMessage().getFrom().getId().longValue() );
          sendMsgWithButtons("Удалить/добавить товар", replyButtons.keyboardMarkup("Add", "Delete"), update.getMessage().getFrom().getId().longValue());
      }else {
          if (update.hasCallbackQuery()) {
              switch (update.getCallbackQuery().getData()) {
                  case "Apple": {
                      sendMsgWithPhoto("Яблоко, 1.50 руб", "https://prostye-retsepty.com/wp-content/uploads/2015/06/zelenie-yabloki-polza-i-vred.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      System.out.println(update.getCallbackQuery().getFrom().getId());
                      break;

                  }
                  case "Xypma": {
                      sendMsgWithPhoto("Хурма, 2 руб", "https://n1s1.hsmedia.ru/2d/64/78/2d64788f2207598ec3b16dc17275b7ef/620x462_1_09e70a0c17cdfd73c13733a726a6c54b@1000x745_0xac120003_17559636201562655554.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());

                      break;

                  }
                  case "Strawberry": {
                      sendMsgWithPhoto("Клубника, 2руб", "https://n1s1.hsmedia.ru/42/1d/e2/421de250b5e2aca7ffb4191aa0681f26/620x462_1_480b3b8b4f78aa27dd1fb42e7f44583e@1000x745_0xac120003_1375478751562646998.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Cherry": {
                      sendMsgWithPhoto("Вишня, 2 руб", "https://proprikol.ru/wp-content/uploads/2020/04/vishnya-krasivye-kartinki-1-650x434.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Smorodina": {
                      sendMsgWithPhoto("Смородина, 2 руб", "https://fimgs.net/himg/o.94270.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Malina": {
                      sendMsgWithPhoto("Малина, 2 руб", "https://newsgomel.by/upload/sotbit.htmleditoraddition/7bc/7bc0fe9cabe58c7e8a8f4fbe6b67a02c.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Grysha": {
                      sendMsgWithPhoto("Груша, 1.70 руб", " https://cdnimg.rg.ru/img/content/132/30/03/1_d_850.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Vinograd": {
                      sendMsgWithPhoto("Виноград, 2 руб", "https://grapes.hozvo.ru/storage/photos/shares/2018/51/5c1b3eef4516c.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Limon": {
                      sendMsgWithPhoto("Лимон, 2 руб", "https://admin.cgon.ru/storage/upload/medialibrary/5142399ee29ccce4f7ab87b3f1959643.png", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Orange": {
                      sendMsgWithPhoto("Апельсин, 2 руб", "https://m.dom-eda.com/uploads/images/catalog/item/dfc9a3e974/3cbf3bd41c_500.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Kokoc": {
                      sendMsgWithPhoto("Кокос, 1.60 руб", "https://cvetydoma.ru/wp-content/uploads/2019/01/CHto-takoe-kokos-i-kak-on-vyglyadit-768x537.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Mandarin": {
                      sendMsgWithPhoto(" Мандарин, 2 руб", "https://s0.rbk.ru/v6_top_pics/resized/1440xH/media/img/1/98/756083939799981.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Red smorodina": {
                      sendMsgWithPhoto("Красная смородина, 2 руб", "https://remontanta.ru/images/Berries_img/Currant/varietes/saharnaya/krasnaya-smorodina-saharnaya.png", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Banana": {
                      sendMsgWithPhoto("Банан, 2 руб", "https://resizer.mail.ru/p/05612166-aed9-57d7-b591-1ca8f0bc2fca/AAAcNS3vXMYYHhohl75SE8SPldEd4_OGdumwHv8JPTHwY_I_U4cVEk5Irc2lEN9jZUgxE2YPxl5dPHG88-11VlrW9hA.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  case "Ananas": {
                      sendMsgWithPhoto("Ананас, 2 руб", "https://shkolazhizni.ru/img/content/i114/114701_or.jpg", update.getCallbackQuery().getFrom().getId().longValue());
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher("Buy"), "Добавить в корзину?", update.getCallbackQuery().getFrom().getId().longValue());
                      break;
                  }
                  default:
                      sendContact(update.getCallbackQuery().getFrom().getId().longValue());
                      break;
              }
          }
          Long chatId = update.getMessage().getChatId();
          switch (update.getMessage().getText()) {
              case Commands.START: {
                  userService.addUserToList(userService.createUserFromUpdate(update));
                  sendMsg(textService.getPropValues(Paths.HELLO_STRING_PATH, Text.SAY_HELLO_PROPERTY), chatId);
                  sendPhoto(textService.getPropValues(Paths.PHOTOS_URLS_PATH, Photos.HELLO_PHOTO_PATH), chatId);
                  break;
              }
              case Commands.MENU: {
                  for (int i = 1; i <= 15; i++) {
                      sendMsgWithButtons(inlineButtons.keyboardMarkupForSelectStudentOrTeacher(textService.getPropValues(Paths.PHOTOS_URLS_PATH, "text.fruit." + i)), " товар", chatId);
                  }
                  break;
              }
              case Commands.BUTTONS: {
                  sendMsgWithButtons("Сделайте выбор:", replyButtons.keyboardMarkupForSelectStudentOrTeacher(), chatId);
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
                  sendMsg("Write admin to get help @yqpuss", chatId);
                  sendContact(chatId);
                  break;
              }
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


