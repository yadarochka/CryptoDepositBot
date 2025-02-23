package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MenuKeyboard {

    private final SendMessage message;

    public MenuKeyboard(String chatId){
        this.message = new SendMessage();
        this.message.setChatId(chatId);
    }

    public SendMessage sendButtons(boolean isAdmin) {
        this.message.setText("Приумножай свой капитал вместе со мной!\nНажимай кнопки ниже \uD83D\uDC47");
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(List.of(createBtn("\uD83D\uDD25 Пополнить баланс", "add_deposit")));
        keyboard.add(List.of(createBtn("\uD83E\uDD35 Профиль", "show_profile"), createBtn("ℹ Помощь", "get_help")));
        keyboard.add(List.of(createBtn("\uD83D\uDCB5 Вывод средств", "cash_out")));
        if (isAdmin){
            keyboard.add(List.of(createBtn("\uD83D\uDC51 Панель администратора", "show_admin_panel")));
        }
        inlineKeyboardMarkup.setKeyboard(keyboard);
        this.message.setReplyMarkup(inlineKeyboardMarkup);
        return this.message;
    }

    private static InlineKeyboardButton createBtn(String name, String data) {
        var inline = new InlineKeyboardButton();
        inline.setText(name);
        inline.setCallbackData(data);
        return inline;
    }
}
