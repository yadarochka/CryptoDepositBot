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

    public static SendMessage createInlineKeyboard(String chatId, Keyboard menuItem){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(menuItem.name);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(menuItem.keyboard);

        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }

    public static SendMessage createInlineKeyboard(Keyboard menuItem){
        SendMessage message = new SendMessage();
        message.setText(menuItem.name);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(menuItem.keyboard);

        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }

    public static EditMessageText editInlineKeyboard(Message message, Menu.Keyboard menuItem) {
        EditMessageText editMessageText = new EditMessageText();

        editMessageText.setText(menuItem.name);
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setChatId(message.getChatId());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(menuItem.keyboard);

        editMessageText.setReplyMarkup(inlineKeyboardMarkup);

        return editMessageText;
    }
}
