package org.cryptodepositbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class CryptoDepositBot extends TelegramLongPollingBot {

    private static CryptoDepositBot botInstance;

    public CryptoDepositBot() {
        super(Config.get("BOT_TOKEN"));
        botInstance = this;
    }

    @Override
    public String getBotUsername() {
        return "CryproDepositBot";
    }

    @Override
    public void onUpdateReceived(Update update) {
        UpdateHandler.handle(update);
    }

    public static void send(SendMessage message) {
        try {
            System.out.println(message.getText()+ " отправлено в чат " + message.getChatId());
            botInstance.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Что-то пошло не так...");
            e.printStackTrace();
        }
    }

    public static void send(EditMessageText message) {
        try {
            System.out.println(message.getText()+ " отправлено в чат " + message.getChatId());
            botInstance.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Что-то пошло не так...");
            e.printStackTrace();
        }
    }

    public static void send(DeleteMessage message) {
        try {
            System.out.println(message.toString() + " удалено в чате " + message.getChatId());
            botInstance.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Что-то пошло не так...");
            e.printStackTrace();
        }
    }

    public static void stopCallback(String callbackQueryId) {
        try {
            AnswerCallbackQuery answer = new AnswerCallbackQuery();
            answer.setCallbackQueryId(callbackQueryId);
            answer.setShowAlert(false);
            botInstance.execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("Что-то пошло не так...");
            e.printStackTrace();
        }
    }

}