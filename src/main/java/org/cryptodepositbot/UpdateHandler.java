package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateHandler {

    public static void handle(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageHandle(update);
            return;
        }
        else if (update.hasCallbackQuery()) {
            callbackHandle(update);
            return;
        }
        System.out.println("Пользователь ввёл некорректную команду");
    }

    private static void messageHandle(Update update){
        UserModel userModel = UserModelInit(update.getMessage());
        String message = update.getMessage().getText().trim();

        switch (message) {
            case "/start":
                System.out.println("Сommand: /start\tUser: " + userModel.getUsername());
                UserController.start(userModel);
                UserController.showMenu(userModel);
                break;
            case "/show_menu":
                System.out.println("Сommand: /show_menu\tUser: " + userModel.getUsername());
                UserController.showMenu(userModel);
                break;
            default:
                System.out.println("Message: " + message + "\tUser: " + userModel.getUsername());
                //UserController.defaulfCommand();
                break;
        }
    }

    private static void callbackHandle(Update update){
        UserModel userModel = UserModelInit(update.getCallbackQuery());
        String callData = update.getCallbackQuery().getData().trim();

        switch (callData) {
            case "show_admin_panel":
                System.out.println("Callback: /show_admin_panel\tUser: " + userModel.getUsername());
                AdminController.showAllUsers(userModel.getTelegramId(), userModel.getChatId());
                break;
            case "cash_out":
                break;
            default:
                System.out.println("Callback: неизвестный\tUser: " + userModel.getUsername());
                break;
        }
    }

    private static UserModel UserModelInit(Message message){
        Long telegramId = message.getFrom().getId();
        String username = message.getFrom().getUserName();
        String firstName = message.getFrom().getFirstName();
        String chatId = message.getChatId().toString();

        return new UserModel(telegramId, username, chatId, firstName);
    }
    private static UserModel UserModelInit(CallbackQuery callback){
        Long telegramId = callback.getFrom().getId();
        String username = callback.getFrom().getUserName();
        String firstName = callback.getFrom().getFirstName();
        String chatId = callback.getMessage().getChatId().toString();
        System.out.println("chatId = " + chatId);

        return new UserModel(telegramId, username, chatId, firstName);
    }
}