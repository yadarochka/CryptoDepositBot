package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


public class UpdateHandler {


    public static void handle(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageHandle(update);
            return;
        } else if (update.hasCallbackQuery()) {
            callbackHandle(update);
            return;
        }
        System.out.println("Пользователь ввёл некорректную команду");
    }

    private static void messageHandle(Update update) {
        UserModel userModel = UserModelInit(update.getMessage());
        String text = update.getMessage().getText().trim();

        CommandController.execute(text, userModel);
    }

    private static void callbackHandle(Update update) {
        String callData = update.getCallbackQuery().getData().trim();
        Message message = (Message) update.getCallbackQuery().getMessage();

        CallbackController.navigate(callData, message);
        CryptoDepositBot.stopCallback(update.getCallbackQuery().getId());
    }

    private static UserModel UserModelInit(Message message) {
        Long telegramId = message.getFrom().getId();
        String username = message.getFrom().getUserName();
        String firstName = message.getFrom().getFirstName();
        String chatId = message.getChatId().toString();

        return new UserModel(telegramId, username, chatId, firstName);
    }

}