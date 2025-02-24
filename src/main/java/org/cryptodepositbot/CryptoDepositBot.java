package org.cryptodepositbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
            System.out.println("Что-то отправлено в чат " + message.getChatId());
            botInstance.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Что-то пошло не так...");
            e.printStackTrace();
        }
    }
}