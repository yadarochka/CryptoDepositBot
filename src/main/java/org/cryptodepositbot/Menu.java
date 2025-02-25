package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Menu {
    public static InlineKeyboardButton createBtn(String name, String data) {
        var inline = new InlineKeyboardButton();
        inline.setText(name);
        inline.setCallbackData(data);
        return inline;
    }

    public static class Keyboard {
        public String name;
        public List<List<InlineKeyboardButton>> keyboard;

        public Keyboard(String name, List<List<InlineKeyboardButton>> keyboard) {
            this.name = name;
            this.keyboard = keyboard;
        }
    }

    public static void createInlineKeyboard(String chatId, Menu.Keyboard menuItem){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(menuItem.name);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(menuItem.keyboard);

        message.setReplyMarkup(inlineKeyboardMarkup);

        CryptoDepositBot.send(message);
    }

    public static void editInlineKeyboard(Message message, Menu.Keyboard menuItem) {
        EditMessageText editMessageReplyMarkup = new EditMessageText();

        editMessageReplyMarkup.setText(menuItem.name);
        editMessageReplyMarkup.setMessageId(message.getMessageId());
        editMessageReplyMarkup.setChatId(message.getChatId());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(menuItem.keyboard);

        editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

        CryptoDepositBot.send(editMessageReplyMarkup);
    }
}
